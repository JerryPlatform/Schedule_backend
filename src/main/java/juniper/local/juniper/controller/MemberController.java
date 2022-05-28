package juniper.local.juniper.controller;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.dto.LoginDto;
import juniper.local.juniper.repository.MemberRepository;
import juniper.local.juniper.service.MemberService;
import juniper.local.juniper.vo.MemberVo;
import juniper.local.juniper.vo.Response;
import juniper.local.juniper.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final Function<Member, MemberVo> mapMember = member -> MemberVo.builder()
            .id(member.getId())
            .name(member.getName())
            .account(member.getMemberAuthMgt().getAccount())
            .build();

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginDto loginDto) {
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<Response> findAllMembers() {
        return new ResponseEntity<>(Response.builder()
                .response(Result.builder().build())
                .content(memberService.findAllMembers().stream().map(mapMember))
                .build(), HttpStatus.OK);
    }
}
