package juniper.local.juniper.controller;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.service.MemberService;
import juniper.local.juniper.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;
import java.util.stream.Stream;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/test")
public class StudyController {

    private final MemberService memberService;

    @GetMapping("/method")
    public Object test() {
        memberService.getLoginHistoryAll();
        return "success";
    }


}
