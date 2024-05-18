package org.example.EMS.Services;

import org.example.EMS.Entities.Departement;

import java.util.List;
import java.util.Map;

public interface IDepartementService {
    Departement addDepartement(Departement departement);
    Departement updateDepartement(String id, Departement departement);
    void deleteDepartement(String id);
    Departement getDepartementById(String id);
    List<Departement> getAllDepartements();

    Departement getDepartmentWithMostEmployees();

    Map<Departement, Integer> getEmployeesByDepartment();

    Map<Departement, Float> getSalarySumByDepartment();

}
