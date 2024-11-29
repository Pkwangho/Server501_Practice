package com.busanit501.helloworld.food2.vo;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Food2MemberVO {
    private Long tno;
    private String name;
    private String password;
    private LocalDate dueDate;
    private boolean finished;
    private String uuid;
}
