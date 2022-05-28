package juniper.local.juniper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String account;
    private String password;
}
