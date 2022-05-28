package juniper.local.juniper.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MemberAuthMgt {
    @Id
    private Long id;

    private String account;

    private String password;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
