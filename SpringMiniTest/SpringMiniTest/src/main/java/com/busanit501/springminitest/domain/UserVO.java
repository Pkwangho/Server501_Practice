package com.busanit501.springminitest.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Long uno;
    private String name;
    private String password;
    private LocalDate dueDate;
    private boolean finished;
}