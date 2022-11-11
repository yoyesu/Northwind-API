package com.sparta.northwindapi.controller;

import com.sparta.northwindapi.entities.Employee.Employee;
import com.sparta.northwindapi.entities.Employee.Employeeterritory;
import com.sparta.northwindapi.entities.Territory;
import com.sparta.northwindapi.repositories.Employee.EmployeeRepository;
import com.sparta.northwindapi.repositories.Employee.EmployeeterritoryRepository;
import com.sparta.northwindapi.repositories.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ControllerCJ {

    private TerritoryRepository territoryRepo;
    private EmployeeterritoryRepository employeeTerritoryRepo;
    private EmployeeRepository employeeRepo;

    @Autowired
    public ControllerCJ(TerritoryRepository territoryRepo, EmployeeterritoryRepository employeeTerritoryRepo, EmployeeRepository employeeRepo) {
        this.territoryRepo = territoryRepo;
        this.employeeTerritoryRepo = employeeTerritoryRepo;
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/territories/all")
    public List<EntityModel<Territory>> getAllTerritories() {
        List<EntityModel<Territory>> entityModelTerritory = new ArrayList<>();
        List<Territory> territories = territoryRepo.findAll();
        List<Employee> employees = employeeRepo.findAll();
        List<Employeeterritory> employeeTerritories = employeeTerritoryRepo.findAll();

        for (Employeeterritory employeeTerritory : employeeTerritories) {
            Territory territoryAdd;
            Employee employeeAdd;
            for (Territory territorySearch : territories) {
                if (Integer.parseInt(territorySearch.getId()) == Integer.parseInt(employeeTerritory.getId().getTerritoryID())) {
                    territoryAdd = territorySearch;
                    for (Employee employeeSearch : employees) {
                        if (employeeSearch.getId().equals(employeeTerritory.getEmployeeID().getId())) {
                            employeeAdd = employeeSearch;
                            EntityModel<Territory> entityModel=EntityModel.of(territoryAdd);
                            WebMvcLinkBuilder webMvcLinkBuilder=WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getEmployee(employeeAdd.getId()));
                            entityModel.add(webMvcLinkBuilder.withRel("attached-employee"));
                            entityModelTerritory.add(entityModel);
                        }
                    }
                }
            }
        }
        return entityModelTerritory;
    }

    @GetMapping("/employee/{id}") //relative URL
    public Employee getEmployee(@PathVariable Integer id)   {
            return employeeRepo.findById(id).get();
        }

}
