package juniper.local.juniper.service.impl;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.dto.LoginDto;
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

    @Override
    public String memberLogin(LoginDto loginDto) {
        PasswordAuthAuthenticationToken passToken = passwordAuth(loginDto.getAccount(), loginDto.getPassword());
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
        return token.getToken();
    }
}
