package com.bcaliskan.postgresgroovydemo.persistence.repository

import com.bcaliskan.postgresgroovydemo.persistence.entity.HeroEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
// JpaRepository extends PagingAndSortingRepository
interface HeroRepository extends JpaRepository<HeroEntity, Long> {

}