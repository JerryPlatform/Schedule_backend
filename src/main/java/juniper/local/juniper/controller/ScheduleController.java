package juniper.local.juniper.controller;

import juniper.local.juniper.dto.ScheduleSaveDto;
import juniper.local.juniper.service.ScheduleService;
import juniper.local.juniper.vo.Response;
import juniper.local.juniper.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/save")
    public ResponseEntity<Response> scheduleSave(@RequestBody ScheduleSaveDto dto) {
        scheduleService.scheduleSave(dto);
        return new ResponseEntity<>(Response.builder().response(Result.builder().build()).content(null).build(), HttpStatus.OK);
    }
}
