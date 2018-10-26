package ru.bcs.demo.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import ru.bcs.demo.domain.model.enums.DepartamentGroup;

@Setter
@Getter
public class Employee {

    @Id
    private ObjectId id;
    private String name;
    private DepartamentGroup departament;

    public Employee() {
    }

    public Employee(ObjectId id, String name, DepartamentGroup departament) {
        this.id = id;
        this.name = name;
        this.departament = departament;
    }

    public String getId() {
        return id.toHexString();
    }

}