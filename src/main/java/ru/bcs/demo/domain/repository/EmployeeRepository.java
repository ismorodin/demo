package ru.bcs.demo.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.bcs.demo.domain.model.Employee;
import ru.bcs.demo.domain.model.enums.DepartamentGroup;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findAllByDepartament(DepartamentGroup departament);
}