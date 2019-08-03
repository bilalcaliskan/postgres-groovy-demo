package com.bcaliskan.postgresgroovydemo.persistence.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import java.time.LocalDateTime


@Entity
@Table(name = "tbl_users")
class UserEntity extends BaseEntity {

    String uuid

    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 40, message = "userName must be between 5 and 40 characters")
    String userName

    @NotNull
    @Size(min = 5, max = 200, message = "userPass must be between 5 and 200 characters")
    String userPass

    LocalDateTime lastLogin

    Integer userStatus

}
