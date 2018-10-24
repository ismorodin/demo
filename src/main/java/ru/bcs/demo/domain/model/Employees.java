package ru.bcs.demo.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import ru.bcs.demo.domain.Departament;

public class Employees {
    @Id
    private ObjectId _id;
    private String employeeName;
    private Departament departamentName;

    public Employees() {
    }

    public Employees(ObjectId _id, String name, Departament departamentName) {
        this._id = _id;
        this.employeeName = name;
        this.departamentName = departamentName;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Departament getDepartamentName() {
        return departamentName;
    }

    public void setDepartamentName(Departament departamentName) {
        this.departamentName = departamentName;
    }
}