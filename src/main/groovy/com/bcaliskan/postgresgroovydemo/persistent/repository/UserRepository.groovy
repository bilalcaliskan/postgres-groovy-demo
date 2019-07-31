package com.bcaliskan.postgresgroovydemo.persistent.repository

import com.bcaliskan.postgresgroovydemo.persistent.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(@Param("userName") String userName);

}