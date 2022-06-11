package juniper.local.juniper.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@IdClass(LoginHistory.LoginHistoryId.class)
public class LoginHistory {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
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

    public static class LoginHistoryId implements Serializable {
        public Long member;
        public Date loginDt;

        public LoginHistoryId() {

        }
    }

}
