package com.busanit501.helloworld.food2.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor
public class Food2VO {
    private Long tno;
    private String name;
    private Double price;
    private LocalDate dueDate;
    private boolean finished;
}
