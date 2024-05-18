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
@RequestMapping("/employes")
public class EmployeController {

    @Autowired
    private IEmployeService employeService;

    @Autowired
    private IDepartementService departementService;
    @GetMapping
    public String listEmployes(@RequestParam(required = false) String searchValue, Model model) {
        List<Employe> employes = employeService.getAllEmployes();
        model.addAttribute("employes", employes);
        List<Departement> allDepartements = departementService.getAllDepartements();
        model.addAttribute("allDepartements", allDepartements);
        model.addAttribute("employe", new Employe());
        model.addAttribute("searchValue", searchValue); // Add the search value as a model attribute
        return "employee";
    }

    @PostMapping("/add")
    public String addEmploye(@ModelAttribute Employe employe, RedirectAttributes redirectAttributes) {
        Employe existingEmploye = employeService.getEmployeById(employe.getIdEmp());
        String errorMessage = "A department with this ID already exists!";
        redirectAttributes.addAttribute("error", errorMessage);
        redirectAttributes.addAttribute("employeeId", employe.getIdEmp());
        redirectAttributes.addAttribute("employeeName", employe.getNomEmp());
        redirectAttributes.addAttribute("employeeAge", employe.getAge());
        redirectAttributes.addAttribute("employeeSalary", employe.getSalaire());
        redirectAttributes.addAttribute("employeeDepartment", employe.getMondepartement());
        if (existingEmploye != null) {
            redirectAttributes.addAttribute("error", errorMessage);
            return "redirect:/employes#add-employee";
        }

        employeService.addEmploye(employe);
        return "redirect:/employes";
    }


    @PostMapping("/edit")
    public String updateEmploye(@ModelAttribute Employe employe) {
        String id = employe.getIdEmp();
        employeService.updateEmploye(id, employe);
        return "redirect:/employes";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmploye(@PathVariable String id) {
        employeService.deleteEmploye(id);
        return "redirect:/employes";
    }
}

