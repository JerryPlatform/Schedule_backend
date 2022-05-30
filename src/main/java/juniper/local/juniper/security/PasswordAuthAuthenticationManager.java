package juniper.local.juniper.security;

import juniper.local.juniper.domain.MemberAuthMgt;
import juniper.local.juniper.repository.MemberAuthMgtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Log
@Component
@RequiredArgsConstructor
public class PasswordAuthAuthenticationManager implements AuthenticationProvider {

    private final MemberAuthMgtRepository memberAuthMgtRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MemberAuthMgt memberAuthMgt = memberAuthMgtRepository.getMemberAuthMgtsByAccount(authentication.getPrincipal().toString());
        if (!authentication.getCredentials().equals(memberAuthMgt.getPassword())) {
            throw new BadCredentialsException("비밀번호 오류입니다.");
        }
        PasswordAuthAuthenticationToken token = new PasswordAuthAuthenticationToken(memberAuthMgt.getAccount(), memberAuthMgt.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(memberAuthMgt.getRole())));
        token.setId(memberAuthMgt.getMember().getId());
        token.setAccount(memberAuthMgt.getAccount());
        token.setRole(memberAuthMgt.getRole());
        token.setName(memberAuthMgt.getMember().getName());
        token.setPhone(memberAuthMgt.getMember().getPhone());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PasswordAuthAuthenticationToken.class);
    }
}
