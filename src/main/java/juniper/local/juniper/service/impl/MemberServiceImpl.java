package juniper.local.juniper.service.impl;

import juniper.local.juniper.domain.LoginHistory;
import juniper.local.juniper.domain.Member;
import juniper.local.juniper.domain.MemberAuthMgt;
import juniper.local.juniper.dto.AccountingDto;
import juniper.local.juniper.dto.MemberDto;
import juniper.local.juniper.repository.MemberRepository;
import juniper.local.juniper.security.JwtAuthToken;
import juniper.local.juniper.security.JwtAuthTokenProvider;
import juniper.local.juniper.security.PasswordAuthAuthenticationToken;
import juniper.local.juniper.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Value("${juniper.login.retention}")
    private long retentionMinutes;

    private final MemberRepository memberRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtAuthTokenProvider tokenProvider;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Member saveMember(MemberDto memberDto) {
        Member member = new Member();
        MemberAuthMgt memberAuthMgt = new MemberAuthMgt();

        memberAuthMgt.setRole(memberDto.getRole());
        memberAuthMgt.setAccount(memberDto.getAccount());
        memberAuthMgt.setPassword(memberDto.getPassword());
        memberAuthMgt.setMember(member);

        member.setMemberAuthMgt(memberAuthMgt);
        member.setName(memberDto.getName());
        member.setPhone(memberDto.getPhone());

        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public String memberLogin(AccountingDto accountingDto) {
        PasswordAuthAuthenticationToken passToken = passwordAuth(accountingDto.getAccount(), accountingDto.getPassword());
        return createToken(passToken);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public PasswordAuthAuthenticationToken passwordAuth(String account, String password) {
        PasswordAuthAuthenticationToken token = new PasswordAuthAuthenticationToken(account, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (PasswordAuthAuthenticationToken) authentication;
    }

    @Transactional
    public String createToken(PasswordAuthAuthenticationToken passToken) {
        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(retentionMinutes).atZone(ZoneId.systemDefault()).toInstant());
        Map<String, String> claims = new HashMap<>();
        claims.put("id", passToken.getId().toString());
        claims.put("name", passToken.getName());
        claims.put("role", passToken.getRole());
        claims.put("account", passToken.getAccount());
        claims.put("phone", passToken.getPhone());
        JwtAuthToken token = tokenProvider.createAuthToken(
                passToken.getPrincipal().toString(),
                passToken.getAuthorities().iterator().next().getAuthority(),
                claims,
                expiredDate
        );
        Member m = em.getReference(Member.class, passToken.getId());
        em.persist(new LoginHistory(m, new Date()));

        return token.getToken();
    }
}
