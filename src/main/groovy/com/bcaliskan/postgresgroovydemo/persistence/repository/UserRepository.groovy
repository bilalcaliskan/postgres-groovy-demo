package com.bcaliskan.postgresgroovydemo.persistence.repository

import com.bcaliskan.postgresgroovydemo.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository extends JpaRepository<UserEntity, Long> {

}