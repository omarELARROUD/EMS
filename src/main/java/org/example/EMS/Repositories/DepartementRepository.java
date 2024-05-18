package org.example.EMS.Repositories;

import org.example.EMS.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, String> {
}

