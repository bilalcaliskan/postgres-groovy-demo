package com.bcaliskan.postgresgroovydemo.persistence.repository

import com.bcaliskan.postgresgroovydemo.persistence.entity.Disaster
import org.springframework.data.jpa.repository.JpaRepository


// JpaRepository extends PagingAndSortingRepository
interface DisasterRepository extends JpaRepository<Disaster, Long> {

}
