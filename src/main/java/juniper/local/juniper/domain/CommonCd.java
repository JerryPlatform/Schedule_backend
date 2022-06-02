package juniper.local.juniper.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class CommonCd {
    @Id
    private Long id;

    private String kind;

    private String codeVal;

    private String description;
}
