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
