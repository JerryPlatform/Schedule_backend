package juniper.local.juniper.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class LoginHistory {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDt;

    private LoginType loginType;

    public static enum LoginType implements Meta {
        ACCOUNT("계정");

        private String desc;

        LoginType(String desc) {
            this.desc = desc;
        }

        public String getDescription() {
            return this.desc;
        }
    }

    public LoginHistory() {
        this.loginType = LoginType.ACCOUNT;
    }

    public LoginHistory(Member member, Date loginDt) {
        this();
        this.member = member;
        this.loginDt = loginDt;
    }

}
