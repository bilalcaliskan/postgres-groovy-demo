package com.bcaliskan.postgresgroovydemo.controller

import com.bcaliskan.postgresgroovydemo.model.BaseResponse
import com.bcaliskan.postgresgroovydemo.model.FailureResponse
import com.bcaliskan.postgresgroovydemo.model.SuccessResponse
import com.bcaliskan.postgresgroovydemo.model.UserEntityRequest
import com.bcaliskan.postgresgroovydemo.persistence.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid


@RestController
@RequestMapping("user-controller")
class UserController {

    @Autowired
    UserService userService


    @GetMapping('get-all-users')
    List findAll() {
        return userService.getAllUsers()
    }

    @PostMapping('create-user')
    ResponseEntity<BaseResponse> createUser(@Valid @RequestBody UserEntityRequest request) {
        return userService.createUser(request) ? new ResponseEntity<SuccessResponse>(new SuccessResponse("createUser", true), HttpStatus.OK)
                : new ResponseEntity<FailureResponse>(new FailureResponse("createUser", false, "Unknown error occured!"), HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
