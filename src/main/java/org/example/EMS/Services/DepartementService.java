package org.example.EMS.Services;

import org.example.EMS.Entities.Departement;
import org.example.EMS.Entities.Employe;
import org.example.EMS.Repositories.DepartementRepository;
import org.example.EMS.Repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartementService implements IDepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Departement addDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public Departement updateDepartement(String id, Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public void deleteDepartement(String id) {
        List<Employe> employees = employeRepository.findByDepartementId(id);
        for (Employe employe : employees) {
            employe.setMondepartement(null);
            employeRepository.save(employe);
        }
        departementRepository.deleteById(id);
    }


    @Override
    public Departement getDepartementById(String id) {
        return departementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement getDepartmentWithMostEmployees() {
        // Implement logic to find the department with the most employees
        List<Departement> departements = departementRepository.findAll();
        Departement departmentWithMostEmployees = null;
        int maxEmployeeCount = 0;
        for (Departement departement : departements) {
            List<Employe> employees = employeRepository.findByDepartementId(departement.getIdDept());
            int employeeCount = employees.size();
            if (employeeCount > maxEmployeeCount) {
                maxEmployeeCount = employeeCount;
                departmentWithMostEmployees = departement;
            }
        }
        return departmentWithMostEmployees;
    }

    @Override
    public Map<Departement, Integer> getEmployeesByDepartment() {
        List<Departement> departements = departementRepository.findAll();
        Map<Departement, Integer> employeesByDepartment = new HashMap<>();
        for (Departement departement : departements) {
            List<Employe> employees = employeRepository.findByDepartementId(departement.getIdDept());
            employeesByDepartment.put(departement, employees.size());
        }
        return employeesByDepartment;
    }

    @Override
    public Map<Departement, Float> getSalarySumByDepartment() {
        List<Departement> departments = getAllDepartements();
        Map<Departement, Float> salarySumByDepartment = new HashMap<>();

        for (Departement department : departments) {
            Float sum = employeRepository.calculateTotalSalaryByDepartment(department);
            if (sum != null) {
                salarySumByDepartment.put(department, sum);
            }
        }

        return salarySumByDepartment;
    }
}
