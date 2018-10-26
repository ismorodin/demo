package ru.bcs.demo.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.bcs.demo.domain.model.enums.DepartamentGroup;

@Data
@AllArgsConstructor
public class EmployeeCreatedResponse {

    private String id;
    private String name;
    private DepartamentGroup departament;
}
