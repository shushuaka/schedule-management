package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.model.Schedule;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 일정 생성 로직
    public Schedule createSchedule(String task, Long authorId, String password) {
        Schedule schedule = new Schedule();
        schedule.setTask(task);
        schedule.setAuthorId(authorId);  // 작성자 ID 설정
        schedule.setPassword(password);  // 비밀번호 설정
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setUpdatedAt(LocalDateTime.now());

        return scheduleRepository.save(schedule);
    }

    // 단건 일정 조회 로직
    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
    }

    // 일정 수정 로직
    public Schedule updateSchedule(Long id, String task, Long authorId) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // task와 authorId를 업데이트
        schedule.setTask(task);
        schedule.setAuthorId(authorId);
        schedule.setUpdatedAt(LocalDateTime.now());

        // 수정된 내용 저장
        return scheduleRepository.save(schedule);
    }

    // 일정 삭제 로직
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다."));

        // 비밀번호 검증
        if (schedule.getPassword() == null || !schedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 해당 일정 삭제
        scheduleRepository.delete(schedule);
    }

    // 일정 목록 조회 - 페이지네이션 로직
    public Page<Schedule> getSchedules(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);

        // 페이지 범위를 넘어서는 요청 처리
        if (schedules.isEmpty() && pageable.getPageNumber() > 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 페이지는 존재하지 않습니다.");
        }

        return schedules;
    }
}

