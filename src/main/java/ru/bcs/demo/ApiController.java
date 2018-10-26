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
import ru.bcs.demo.web.EmployeeNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class ApiController {

    private final WorkplaceService workspaceService;

    private final EmployeeRepository employeeRepository;

    public ApiController(WorkplaceService workspaceService, EmployeeRepository employeeRepository) {
        this.workspaceService = workspaceService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeRepository.findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @GetMapping(value = "/departments/{departament}/employees")
    public List<Employee> getEmployeesByDepartamentName(@PathVariable DepartamentGroup departament) {
        return employeeRepository.findAllByDepartament(departament);
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeCreatedResponse createEmployee(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest) {
        Employee createdEmployee = workspaceService.createEmployee(employeeRequestToEmployee(employeeCreateRequest));
        return new EmployeeCreatedResponse(createdEmployee.getId(), createdEmployee.getName(), createdEmployee.getDepartament());
    }

    @PutMapping("/employees/{id}")
    public Employee modifyEmployeesById(@PathVariable("id") ObjectId id, @Valid @RequestBody Employee emps) {
        return employeeRepository.save(emps);
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
        employeeRepository.delete(employee);
    }

    private Employee employeeRequestToEmployee(EmployeeCreateRequest employeeCreateRequest) {
        Employee newEmployee = new Employee();
        newEmployee.setDepartament(employeeCreateRequest.getDepartament());
        newEmployee.setName(employeeCreateRequest.getName());
        return newEmployee;
    }

}