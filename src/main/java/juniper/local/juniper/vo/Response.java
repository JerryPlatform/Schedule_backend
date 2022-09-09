package juniper.local.juniper.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response<T> {
    Result response;
    T contents;
}
