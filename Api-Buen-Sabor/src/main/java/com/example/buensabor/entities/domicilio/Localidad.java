package com.example.buensabor.entities.domicilio;

import com.example.buensabor.entities.EntityBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "localidad")
public class Localidad extends EntityBean {
    @Column(name = "nombre", length = 50, nullable = false)
    @NotNull
    private  String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "localidad")
    private List<Domicilio> domicilios = new ArrayList<>();
}
