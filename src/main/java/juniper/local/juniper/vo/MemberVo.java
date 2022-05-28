package juniper.local.juniper.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberVo {
    private Long id;
    private String name;
    private String account;
}
