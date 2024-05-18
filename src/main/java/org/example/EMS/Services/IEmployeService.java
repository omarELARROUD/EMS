package org.example.EMS.Services;
import org.example.EMS.Entities.Departement;
import org.example.EMS.Entities.Employe;
import java.util.List;

public interface IEmployeService {
    Employe addEmploye(Employe employe);
    Employe updateEmploye(String id, Employe employe);
    void deleteEmploye(String id);
    Employe getEmployeById(String id);
    List<Employe> getAllEmployes();
    List<Employe> getEmployeesByDepartement(Departement departement);
    long getTotalEmployees();

    void reassignEmployee(String employeeId, String newDepartmentId);

    float calculateTotalSalaryMass();

    float calculateAverageAge();

}

