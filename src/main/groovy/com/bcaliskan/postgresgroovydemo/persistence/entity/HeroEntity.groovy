package com.bcaliskan.postgresgroovydemo.persistence.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*


@Entity
class HeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String name

    @OneToMany( // tell persistence provider 'abilities' is one-to-many relation with AbilityEntity
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, // cascade all operation to 'abilities' values
            orphanRemoval = true, // auto delete AbilityEntity entity that has no HeroEntity
            mappedBy = 'hero' // mark for bi-directional relation
    )
    List<AbilityEntity> abilities

    @ManyToMany(mappedBy = 'heroes')
    @JsonIgnore
    Set<DisasterEntity> disasters

}