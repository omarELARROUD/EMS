package org.example.EMS.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEmploye")
public class Employe {
    @Id
    private String IdEmp ;
    private String NomEmp ;
    private Float Salaire ;
    private Integer Age ;
    @ManyToOne
    @JoinColumn(name = "RefDept")
    @ToString.Exclude
    private Departement  Mondepartement ;
}