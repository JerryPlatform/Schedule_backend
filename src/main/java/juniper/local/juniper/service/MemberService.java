package juniper.local.juniper.service;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.dto.AccountingDto;

import java.util.List;

public interface MemberService {
    String memberLogin(AccountingDto accountingDto);
    List<Member> findAllMembers();
}
