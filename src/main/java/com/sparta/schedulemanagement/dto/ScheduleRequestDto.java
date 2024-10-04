package com.sparta.schedulemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String task;
    private Long authorId;  // 작성자 ID를 받도록 변경
    private String password;  // 비밀번호 필드 추가
}
