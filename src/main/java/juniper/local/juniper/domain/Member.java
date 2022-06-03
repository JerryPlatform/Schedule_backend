package juniper.local.juniper.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    @OneToOne(mappedBy = "member", optional = false, cascade = CascadeType.ALL)
    private MemberAuthMgt memberAuthMgt;
}
