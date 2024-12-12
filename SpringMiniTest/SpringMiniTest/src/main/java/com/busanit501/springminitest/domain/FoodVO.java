package com.busanit501.springminitest.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoodVO {
    private Long tno;
    private String name;
    private Double price;
    private LocalDate dueDate;
    private boolean finished;
}