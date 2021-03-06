package ru.bcs.demo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bcs.demo.domain.model.Employee;
import ru.bcs.demo.domain.model.enums.DepartamentGroup;
import ru.bcs.demo.domain.repository.EmployeeRepository;
import ru.bcs.demo.service.WorkplaceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class ApiController {

    @Autowired
    private WorkplaceService workspaceService;

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping(value = "departament/{departament}")
    public List<Employee> getEmployeesByDepartamentName(@PathVariable("departament") DepartamentGroup departament) {
        return employeeRepository.findAllByDepartamentName(departament);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@Valid @RequestBody Employee emps) {
        return workspaceService.createEmployees(employeeRepository, emps);
    }

    @PutMapping("/{id}")
    public Employee modifyEmployeesById(@PathVariable("id") ObjectId id, @Valid @RequestBody Employee emps) {
        return employeeRepository.save(emps);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeesById(@PathVariable ObjectId id) {
        employeeRepository.delete(employeeRepository.findById(id));
    }

}