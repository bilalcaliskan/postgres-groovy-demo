package com.bcaliskan.postgresgroovydemo.model.request

import javax.persistence.Column
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


class UserEntityRequest {

    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 40, message = "userName must be between 5 and 40 characters")
    String userName

    @NotNull
    @Size(min = 5, max = 200, message = "userPass must be between 5 and 200 characters")
    String userPass

}
