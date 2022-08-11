package juniper.local.juniper.util;

import io.jsonwebtoken.Claims;
import juniper.local.juniper.domain.Member;
import juniper.local.juniper.security.JwtAuthToken;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextUtil {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }
    @Builder
    @Getter
    public static class AuthInfo {
        private Long id;
        private String account;
        private String name;
        private String phone;
    }

    public static AuthInfo getCredential() {
        JwtAuthToken token = (JwtAuthToken) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Claims claims = token.getData();
        return AuthInfo.builder()
                .id(Long.parseLong(claims.get("id").toString()))
                .account(claims.get("account").toString())
                .name(claims.get("name").toString())
                .phone(claims.get("phone").toString())
                .build();
    }
}
