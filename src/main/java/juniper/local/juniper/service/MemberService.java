package juniper.local.juniper.service;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.dto.AccountingDto;
import juniper.local.juniper.dto.MemberDto;

import java.util.List;

public interface MemberService {

    Member saveMember(MemberDto memberDto);
    String memberLogin(AccountingDto accountingDto);

    Member getMember(Long memberId);
    List<Member> findAllMembers();
}
