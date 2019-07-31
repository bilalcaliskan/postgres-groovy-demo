package com.bcaliskan.postgresgroovydemo.controller

import com.bcaliskan.postgresgroovydemo.model.response.BaseResponse
import com.bcaliskan.postgresgroovydemo.model.response.FailureResponse
import com.bcaliskan.postgresgroovydemo.model.response.SuccessResponse
import com.bcaliskan.postgresgroovydemo.model.request.UserEntityRequest
import com.bcaliskan.postgresgroovydemo.persistent.service.UserService
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
        if (userService.createUser(request)) {
            return new ResponseEntity<>(new SuccessResponse("createUser", true), HttpStatus.OK)
        } else {
            return new ResponseEntity<>(new FailureResponse("createUser", false, "Unknown error occured!"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("login")
    ResponseEntity<BaseResponse> login(@Valid @RequestBody UserEntityRequest request) {
        if (userService.login(request))
            return new ResponseEntity<>(new SuccessResponse("login", true), HttpStatus.OK)
        else
            return new ResponseEntity<>(new FailureResponse("login", false, "Unknown error occured!"), HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
