package ru.bcs.demo.service.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.bcs.demo.domain.model.Employee;
import ru.bcs.demo.domain.repository.EmployeeRepository;
import ru.bcs.demo.service.WorkplaceService;

@Service
public class WorkplaceServiceImpl implements WorkplaceService {

    private final EmployeeRepository repository;

    public WorkplaceServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(ObjectId.get());
        return repository.save(employee);
    }

}