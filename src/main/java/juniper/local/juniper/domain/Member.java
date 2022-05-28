package juniper.local.juniper.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    private Long id;

    private String name;

    @OneToOne(mappedBy = "member", optional = false)
    private MemberAuthMgt memberAuthMgt;
}
