package com.busanit501.springminitest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long uno;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @Past
    private LocalDate dueDate;
    private boolean finished;
}
