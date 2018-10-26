package ru.bcs.demo.web;

import lombok.Data;
import ru.bcs.demo.domain.model.enums.DepartamentGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeCreateRequest {

    @NotEmpty
    private String name;
    @NotNull
    private DepartamentGroup departament;
}
