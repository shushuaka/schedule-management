package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.model.Schedule;
import com.sparta.schedulemanagement.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성 API
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleService.createSchedule(
                requestDto.getTask(),
                requestDto.getAuthorId(),
                requestDto.getPassword()  // 비밀번호 추가
        );
        return ResponseEntity.status(201).body(schedule);
    }

    // 단건 일정 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleService.getSchedule(id);
        return ResponseEntity.ok(schedule);
    }

    // 일정 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto) {
        Schedule updatedSchedule = scheduleService.updateSchedule(id, requestDto.getTask(), requestDto.getAuthorId());
        return ResponseEntity.ok(updatedSchedule);
    }

    // 일정 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.deleteSchedule(id, requestDto.getPassword());  // 비밀번호와 함께 삭제 요청
        return ResponseEntity.noContent().build();  // 삭제 성공 시 204 No Content 응답
    }

    // 일정 목록 조회 API - 페이지네이션 처리
    @GetMapping
    public ResponseEntity<Page<Schedule>> getSchedules(
            @RequestParam(defaultValue = "0") int page,  // 기본값: 0페이지
            @RequestParam(defaultValue = "10") int size  // 기본값: 페이지 크기 10
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Schedule> schedules = scheduleService.getSchedules(pageable);
        return ResponseEntity.ok(schedules);
    }
}

