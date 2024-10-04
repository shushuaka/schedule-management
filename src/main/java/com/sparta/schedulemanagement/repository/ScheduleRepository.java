package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    // Pageable을 사용한 일정 목록 조회
    Page<Schedule> findAll(Pageable pageable);
}
