package com.busanit501.helloworld.food2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor

public class Food2DTO {
    private Long tno;
    private String name;
    private Double price;
    private LocalDate dueDate;
    private boolean finished;
}
