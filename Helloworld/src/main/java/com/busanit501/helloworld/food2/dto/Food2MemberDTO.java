package com.busanit501.helloworld.food2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food2MemberDTO {
    private String name;
    private String password;
    private String uuid;
}
