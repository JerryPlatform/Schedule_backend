package juniper.local.juniper.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writer;

    private String content;

    private Feel feel;

    @CreationTimestamp
    private Date regDt;

    public enum Feel implements Meta {
        BAD("나쁨"),
        SLIGHTLYBAD("약간 나쁨"),
        NORMAL("보통"),
        SLIGHTLYGOOD("약간 좋음"),
        GOOD("좋음"),
        ;

        private String desc;

        Feel(String desc) { this.desc = desc; }
        @Override
        public String getDescription() {
            return this.desc;
        }
    }

}
