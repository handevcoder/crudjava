package com.example.crud.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ERole name;

    private Role() {}

    public Role(Long id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public Role(ERole role) {
        this.name = role;
    }

}
