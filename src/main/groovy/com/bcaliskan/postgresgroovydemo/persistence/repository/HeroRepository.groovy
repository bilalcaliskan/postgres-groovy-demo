package com.bcaliskan.postgresgroovydemo.persistence.repository

import com.bcaliskan.postgresgroovydemo.persistence.entity.Hero
import org.springframework.data.jpa.repository.JpaRepository


// JpaRepository extends PagingAndSortingRepository
interface HeroRepository extends JpaRepository<Hero, Long> {

}