package juniper.local.juniper.service;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.dto.LoginDto;

import java.util.List;

public interface MemberService {
    String memberLogin(LoginDto loginDto);
    List<Member> findAllMembers();
}
