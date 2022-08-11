package juniper.local.juniper.service;

import juniper.local.juniper.domain.Schedule;
import juniper.local.juniper.dto.ScheduleSaveDto;

public interface ScheduleService {
    Schedule scheduleSave(ScheduleSaveDto dto);
}
