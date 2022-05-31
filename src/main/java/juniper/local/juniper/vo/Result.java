package juniper.local.juniper.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Result {
    @Builder.Default
    protected String token = "";
}
