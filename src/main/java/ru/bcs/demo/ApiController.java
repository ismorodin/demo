package ru.bcs.demo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bcs.demo.domain.model.Employees;
import ru.bcs.demo.domain.repository.EmployeesRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class ApiController {
    @Autowired
    private EmployeesRepository repository;

    @GetMapping()
    public ResponseEntity<List<Employees>> getAllEmployees() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "departamentName/{departamentName}")
    public Employees getEmployeesByDepartamentName(@PathVariable("departamentName") String departamentName) {
        return repository.findByDepartamentName(departamentName);
    }

    @PostMapping()
    public ResponseEntity<Employees> createEmployee(@Valid @RequestBody Employees emps) {
        emps.set_id(ObjectId.get());
        repository.save(emps);
        return ResponseEntity.ok(emps);
    }

    @PutMapping("/{id}")
    public void modifyEmployeesById(@PathVariable("id") ObjectId id, @Valid @RequestBody Employees emps) {
        emps.set_id(id);
        repository.save(emps);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployees(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}