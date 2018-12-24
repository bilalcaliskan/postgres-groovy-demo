package com.bcaliskan.postgresgroovydemo.persistence.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Builder
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

import javax.persistence.*


@Entity
class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String name

    @OneToMany( // tell persistence provider 'abilities' is one-to-many relation with Ability
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, // cascade all operation to 'abilities' values
            orphanRemoval = true, // auto delete Ability entity that has no Hero
            mappedBy = 'hero' // mark for bi-directional relation
    )
    List<Ability> abilities

    @ManyToMany(mappedBy = 'heroes')
    @JsonIgnore
    Set<Disaster> disasters

}