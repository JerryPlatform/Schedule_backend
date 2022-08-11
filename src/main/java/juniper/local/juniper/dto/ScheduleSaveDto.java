package juniper.local.juniper.dto;

import juniper.local.juniper.domain.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleSaveDto {
    private Long id;

    private String content;

    private Schedule.Feel feel;
}
