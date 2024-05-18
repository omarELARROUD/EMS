package org.example.EMS.Controllers;

import org.example.EMS.Entities.Departement;
import org.example.EMS.Entities.Employe;
import org.example.EMS.Services.IDepartementService;
import org.example.EMS.Services.IEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/departements")
public class DepartementController {

    @Autowired
    private IDepartementService departementService;

    @Autowired
    private IEmployeService employeService;

    @GetMapping
    public String listDepartements(@RequestParam(required = false) String searchValue,Model model) {
        List<Departement> departements = departementService.getAllDepartements();
        model.addAttribute("departements", departements);
        model.addAttribute("departement", new Departement());
        model.addAttribute("searchValue", searchValue);
        return "department";
    }

    @PostMapping("/add")
    public String addDepartement(@ModelAttribute Departement departement, RedirectAttributes redirectAttributes) {
        Departement existingDepartement = departementService.getDepartementById(departement.getIdDept());

        if (existingDepartement != null) {
            String errorMessage = "A department with this ID already exists!";
            redirectAttributes.addAttribute("error", errorMessage);
            redirectAttributes.addAttribute("departmentId", departement.getIdDept());
            redirectAttributes.addAttribute("departmentName", departement.getNomDept());
            // Add other attribute values as needed
            return "redirect:/departements#add-department";
        }

        departementService.addDepartement(departement);
        return "redirect:/departements";
    }

    @GetMapping("/employes/{idDept}")
    public String viewEmployeesInDepartment(@PathVariable String idDept, Model model) {
        Departement department = departementService.getDepartementById(idDept);
        List<Employe> employees = employeService.getEmployeesByDepartement(department);
        model.addAttribute("department", department);
        model.addAttribute("employees", employees);
        return "departements/department_employees";
    }

    @PostMapping("/edit")
    public String updateDepartement(@ModelAttribute Departement departement) {
        String id = departement.getIdDept();
        departementService.updateDepartement(id, departement);
        return "redirect:/departements";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartement(@PathVariable String id) {
        departementService.deleteDepartement(id);
        return "redirect:/departements";
    }
}
