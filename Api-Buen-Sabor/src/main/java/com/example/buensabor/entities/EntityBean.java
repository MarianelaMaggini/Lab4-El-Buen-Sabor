package com.example.buensabor.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EntityBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
}
