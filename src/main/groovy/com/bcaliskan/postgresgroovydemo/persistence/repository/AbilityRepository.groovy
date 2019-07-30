package com.bcaliskan.postgresgroovydemo.persistence.repository


import com.bcaliskan.postgresgroovydemo.persistence.entity.AbilityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface AbilityRepository extends JpaRepository<AbilityEntity, Long> {

}
