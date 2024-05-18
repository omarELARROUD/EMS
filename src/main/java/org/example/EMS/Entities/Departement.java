package org.example.EMS.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TDepartement")
public class Departement {
    @Id
    @Column(name = "iddept")
    private String IdDept ;
    @Column(name = "nomdept")
    private String NomDept ;
    @OneToMany(mappedBy = "Mondepartement", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Employe> MesEmployes;
}
