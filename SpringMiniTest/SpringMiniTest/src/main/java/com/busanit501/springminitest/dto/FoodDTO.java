package com.busanit501.springminitest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDTO {
    private Long tno;
    @NotEmpty// 빈 문자열, 공백 없이, 값이 존재해야함
    private String name;
    @Future // 미래 날짜만
    private LocalDate dueDate;
    private Double price;
    private boolean finished;
}
