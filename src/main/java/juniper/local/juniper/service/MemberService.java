package juniper.local.juniper.service;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.dto.LoginDto;
import juniper.local.juniper.security.PasswordAuthAuthenticationToken;

import java.util.List;

public interface MemberService {
    void memberLogin(LoginDto loginDto);

    PasswordAuthAuthenticationToken passwordAuth(String account, String password);
    List<Member> findAllMembers();
}
