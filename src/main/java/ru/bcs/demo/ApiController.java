package ru.bcs.demo;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bcs.demo.domain.model.Employee;
import ru.bcs.demo.domain.model.enums.DepartamentGroup;
import ru.bcs.demo.domain.repository.EmployeeRepository;
import ru.bcs.demo.service.WorkplaceService;
import ru.bcs.demo.web.EmployeeCreateRequest;
import ru.bcs.demo.web.EmployeeCreatedResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class ApiController {

    private final WorkplaceService workspaceService;

    private final EmployeeRepository employeeRepository;

    public ApiController(WorkplaceService workspaceService, EmployeeRepository employeeRepository) {
        this.workspaceService = workspaceService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping(value = "departament/{departament}")
    public List<Employee> getEmployeesByDepartamentName(@PathVariable("departament") DepartamentGroup departament) {
        return employeeRepository.findAllByDepartamentName(departament);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeCreatedResponse createEmployee(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest) {
        Employee createdEmployee = workspaceService.createEmployee(employeeRequestToEmployee(employeeCreateRequest));
        return new EmployeeCreatedResponse(createdEmployee.getId(),createdEmployee.getName(), createdEmployee.getDepartament());
    }

    @PutMapping("/{id}")
    public Employee modifyEmployeesById(@PathVariable("id") ObjectId id, @Valid @RequestBody Employee emps) {
        return employeeRepository.save(emps);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeesById(@PathVariable ObjectId id) {
        employeeRepository.delete(employeeRepository.findById(id));
    }

    private Employee employeeRequestToEmployee(EmployeeCreateRequest employeeCreateRequest) {
        Employee newEmployee = new Employee();
        newEmployee.setDepartament(employeeCreateRequest.getDepartament());
        newEmployee.setName(employeeCreateRequest.getName());
        return newEmployee;
    }

}