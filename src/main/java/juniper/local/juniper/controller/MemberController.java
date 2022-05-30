package juniper.local.juniper.controller;

import juniper.local.juniper.domain.Member;
import juniper.local.juniper.dto.LoginDto;
import juniper.local.juniper.security.JwtAuthToken;
import juniper.local.juniper.security.JwtAuthTokenProvider;
import juniper.local.juniper.security.PasswordAuthAuthenticationToken;
import juniper.local.juniper.service.MemberService;
import juniper.local.juniper.vo.MemberVo;
import juniper.local.juniper.vo.Response;
import juniper.local.juniper.vo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    @Value("${hemp.login.retention}")
    private long retentionMinutes;

    private final MemberService memberService;

    private final JwtAuthTokenProvider tokenProvider;

    private final Function<Member, MemberVo> mapMember = member -> MemberVo.builder()
            .id(member.getId())
            .name(member.getName())
            .account(member.getMemberAuthMgt().getAccount())
            .build();

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginDto loginDto) {
        PasswordAuthAuthenticationToken user = memberService.passwordAuth(loginDto.getAccount(), loginDto.getPassword());

        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(retentionMinutes).atZone(ZoneId.systemDefault()).toInstant());
        Map<String, String> claims = new HashMap<>();
        claims.put("id", user.getId().toString());
        claims.put("name", user.getName());
        claims.put("role", user.getRole());
        claims.put("account", user.getAccount());
        claims.put("phone", user.getPhone());

        JwtAuthToken token = tokenProvider.createAuthToken(
                user.getPrincipal().toString(),
                user.getAuthorities().iterator().next().getAuthority(),
                claims,
                expiredDate
        );

        return new ResponseEntity<>(Response.builder()
                .response(Result.builder().token(token.getToken()).build())
                .content(token.getToken())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> findAllMembers() {
        return new ResponseEntity<>(Response.builder()
                .response(Result.builder().build())
                .content(memberService.findAllMembers().stream().map(mapMember))
                .build(), HttpStatus.OK);
    }
}
