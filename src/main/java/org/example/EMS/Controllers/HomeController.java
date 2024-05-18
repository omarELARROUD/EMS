package org.example.EMS.Controllers;

import org.example.EMS.Entities.Departement;
import org.example.EMS.Entities.Employe;
import org.example.EMS.Services.IDepartementService;
import org.example.EMS.Services.IEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private IEmployeService employeService;

    @Autowired
    private IDepartementService departementService;

    @GetMapping("/")
    public String home(@RequestParam(required = false) String searchValue,Model model) {
        long totalEmployees = employeService.getTotalEmployees();
        Departement departmentWithMostEmployees = departementService.getDepartmentWithMostEmployees();
        Map<Departement, Integer> employeesByDepartment = departementService.getEmployeesByDepartment();
        List<Employe> employees = employeService.getAllEmployes();
        List<Departement> departments = departementService.getAllDepartements();
        float totalSalaryMass = employeService.calculateTotalSalaryMass();
        Map<Departement, Float> salarySumByDepartment = departementService.getSalarySumByDepartment();
        float averageAge = employeService.calculateAverageAge();
        if(salarySumByDepartment == null){
            salarySumByDepartment = new HashMap<>();
        }
        if (departmentWithMostEmployees == null) {
            departmentWithMostEmployees = new Departement();
        }

        if (employeesByDepartment == null) {
            employeesByDepartment = new HashMap<>();
        }

        if (employees == null) {
            employees = new ArrayList<>();
        }

        if (departments == null) {
            departments = new ArrayList<>();
        }


        model.addAttribute("averageAge", averageAge);
        model.addAttribute("salarySumByDepartment", salarySumByDepartment);
        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("departmentWithMostEmployees", departmentWithMostEmployees);
        model.addAttribute("employeesByDepartment", employeesByDepartment);
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("totalSalaryMass", totalSalaryMass);
        model.addAttribute("searchValue", searchValue);

        return "index";
    }


    @PostMapping("/reassign")
    public String reassignEmploye(@RequestParam("employeId") String employeId, @RequestParam("newDepartmentId") String newDepartmentId) {
        employeService.reassignEmployee(employeId, newDepartmentId);
        return "redirect:/";
    }

}
