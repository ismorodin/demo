package ru.bcs.demo.service;

import ru.bcs.demo.domain.model.Employee;
import ru.bcs.demo.domain.repository.EmployeeRepository;

public interface WorkplaceService {

    Employee createEmployees(EmployeeRepository repository, Employee emps);

}