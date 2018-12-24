package com.bcaliskan.postgresgroovydemo.persistence.repository

import com.bcaliskan.postgresgroovydemo.persistence.entity.Ability
import org.springframework.data.jpa.repository.JpaRepository


interface AbilityRepository extends JpaRepository<Ability, Long> {

}
