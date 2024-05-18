package org.example.EMS.Repositories;

import org.example.EMS.Entities.Departement;
import org.example.EMS.Entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, String> {
    @Query("SELECT e FROM Employe e WHERE e.Mondepartement.IdDept = :idDept")
    List<Employe> findByDepartementId(String idDept);

    @Query("SELECT SUM(e.Salaire) FROM Employe e WHERE e.Mondepartement = :department")
    Float calculateTotalSalaryByDepartment(Departement department);

}
