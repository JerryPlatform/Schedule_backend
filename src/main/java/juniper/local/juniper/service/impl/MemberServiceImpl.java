package juniper.local.juniper.service.impl;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.dto.LoginDto;
import juniper.local.juniper.repository.MemberRepository;
import juniper.local.juniper.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void memberLogin(LoginDto loginDto) {

    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
