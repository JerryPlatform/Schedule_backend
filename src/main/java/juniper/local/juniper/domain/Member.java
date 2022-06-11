package juniper.local.juniper.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    @OneToOne(mappedBy = "member", optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MemberAuthMgt memberAuthMgt;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<LoginHistory> loginHistory;
}
