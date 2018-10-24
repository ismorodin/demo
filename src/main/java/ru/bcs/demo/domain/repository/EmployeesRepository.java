package ru.bcs.demo.domain.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.bcs.demo.domain.model.Employees;

public interface EmployeesRepository extends MongoRepository<Employees, String> {
    Employees findBy_id(ObjectId _id);

    Employees findByDepartamentName(String departamentName);
}