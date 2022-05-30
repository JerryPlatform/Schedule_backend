package juniper.local.juniper.service.impl;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.dto.LoginDto;
import juniper.local.juniper.repository.MemberRepository;
import juniper.local.juniper.security.PasswordAuthAuthenticationToken;
import juniper.local.juniper.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final AuthenticationManager authenticationManager;

    @Override
    public void memberLogin(LoginDto loginDto) {

    }

    @Override
    public PasswordAuthAuthenticationToken passwordAuth(String account, String password) {
        PasswordAuthAuthenticationToken token = new PasswordAuthAuthenticationToken(account, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (PasswordAuthAuthenticationToken) authentication;
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
