package com.bcaliskan.postgresgroovydemo.persistence.repository

import com.bcaliskan.postgresgroovydemo.persistence.entity.Disaster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository


// JpaRepository extends PagingAndSortingRepository
interface DisasterRepository extends JpaRepository<Disaster, Long> {

}
