package org.example.EMS.Services;

import org.example.EMS.Entities.Departement;
import org.example.EMS.Entities.Employe;
import org.example.EMS.Repositories.DepartementRepository;
import org.example.EMS.Repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeService implements IEmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Override
    public Employe addEmploye(Employe employe) {
        if (employe.getMondepartement() != null && employe.getMondepartement().getIdDept() != null) {
            Departement departement = departementRepository.findById(employe.getMondepartement().getIdDept()).orElse(null);
            employe.setMondepartement(departement);
        }
        return employeRepository.save(employe);
    }


    @Override
    public Employe updateEmploye(String id, Employe employe) {
        Employe existingEmploye = employeRepository.findById(id).orElse(null);
        if (existingEmploye != null) {
            existingEmploye.setNomEmp(employe.getNomEmp());
            existingEmploye.setSalaire(employe.getSalaire());
            existingEmploye.setAge(employe.getAge());
            if(employe.getMondepartement() != null && employe.getMondepartement().getIdDept() != null) {
                existingEmploye.setMondepartement(departementRepository.findById(employe.getMondepartement().getIdDept()).orElse(null));
            }
            return employeRepository.save(existingEmploye);
        }
        return null;
    }


    @Override
    public void deleteEmploye(String id) {
        employeRepository.deleteById(id);
    }

    @Override
    public Employe getEmployeById(String id) {
        return employeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public List<Employe> getEmployeesByDepartement(Departement departement) {
        return employeRepository.findByDepartementId(departement.getIdDept());
    }

    @Override
    public long getTotalEmployees() {
        return employeRepository.count();
    }


    @Override
    public void reassignEmployee(String employeeId, String newDepartmentId) {
        Employe employe = employeRepository.findById(employeeId).orElse(null);
        Departement departement = departementRepository.findById(newDepartmentId).orElse(null);
        if (employe != null && departement != null) {
            employe.setMondepartement(departement);
            employeRepository.save(employe);
        }
    }

    @Override
    public float calculateTotalSalaryMass() {
        List<Employe> employees = employeRepository.findAll();
        float totalSalary = 0.0f;
        for (Employe employe : employees) {
            totalSalary += employe.getSalaire();
        }
        return totalSalary;
    }

    @Override
    public float calculateAverageAge() {
        List<Employe> employees = employeRepository.findAll();

        if (employees.isEmpty()) {
            return 0.0f;
        }

        int totalEmployees = employees.size();
        int totalAge = 0;

        for (Employe employee : employees) {
            totalAge += employee.getAge();
        }

        return (float) totalAge / totalEmployees;
    }
}

