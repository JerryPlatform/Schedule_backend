package juniper.local.juniper.service.impl;

import juniper.local.juniper.domain.Schedule;
import juniper.local.juniper.dto.ScheduleSaveDto;
import juniper.local.juniper.repository.MemberRepository;
import juniper.local.juniper.repository.ScheduleRepository;
import juniper.local.juniper.service.ScheduleService;
import juniper.local.juniper.util.ContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule scheduleSave(ScheduleSaveDto dto) {
        Schedule schedule = dto.getId() != null ? scheduleRepository.getReferenceById(dto.getId()) : new Schedule();

        schedule.setContent(dto.getContent());
        schedule.setFeel(dto.getFeel());
        schedule.setWriter(memberRepository.getReferenceById(ContextUtil.getCredential().getId()));

        return scheduleRepository.save(schedule);
    }
}
