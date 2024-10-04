package com.sparta.schedulemanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;  // Spring Data JDBC에서 제공하는 @Id 애노테이션

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {

    @Id  // Spring Data JDBC에서 사용하는 @Id 애노테이션
    private Long id;

    private String task;
    private Long authorId;  // 작성자 ID (외래키)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String password;  // 비밀번호 필드 추가

    // Getter 및 Setter
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
