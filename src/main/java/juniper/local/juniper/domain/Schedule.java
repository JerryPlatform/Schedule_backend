package juniper.local.juniper.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    private Feel feel;

    public enum Feel implements Meta {
        NORMAL("보통"),
        ;

        private String desc;

        Feel(String desc) { this.desc = desc; }
        @Override
        public String getDescription() {
            return this.desc;
        }
    }

}
