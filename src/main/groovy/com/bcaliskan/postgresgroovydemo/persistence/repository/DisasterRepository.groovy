package com.bcaliskan.postgresgroovydemo.persistence.repository

import com.bcaliskan.postgresgroovydemo.persistence.entity.Disaster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
// JpaRepository extends PagingAndSortingRepository
interface DisasterRepository extends JpaRepository<Disaster, Long> {

}
