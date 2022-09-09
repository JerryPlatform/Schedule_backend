package juniper.local.juniper.controller;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.dto.AccountingDto;
import juniper.local.juniper.dto.MemberDto;
import juniper.local.juniper.service.MemberService;
import juniper.local.juniper.vo.MemberVo;
import juniper.local.juniper.vo.Response;
import juniper.local.juniper.vo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberService memberService;

    private final Function<Member, MemberVo> mapMember = member -> MemberVo.builder()
            .id(member.getId())
            .name(member.getName())
            .account(member.getMemberAuthMgt().getAccount())
            .build();

    @PostMapping("/save")
    public ResponseEntity<Response> save(@RequestBody MemberDto memberDto) {

        return new ResponseEntity<>(Response.builder()
                .response(Result.builder().token(null).build())
                .contents(Stream.of(memberService.saveMember(memberDto)).map(mapMember))
                .build(), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody AccountingDto accountingDto) {

        String token = memberService.memberLogin(accountingDto);

        Map<String, Object> result = new HashMap<>();

        result.put("token", token);

        return new ResponseEntity<>(Response.builder()
                .response(Result.builder().token(token).build())
                .contents(result)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> findAllMembers() {
        return new ResponseEntity<>(Response.builder()
                .response(Result.builder().build())
                .contents(memberService.findAllMembers().stream().map(mapMember))
                .build(), HttpStatus.OK);
    }
}
