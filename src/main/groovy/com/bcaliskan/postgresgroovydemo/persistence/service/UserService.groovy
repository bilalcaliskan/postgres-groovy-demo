package com.bcaliskan.postgresgroovydemo.persistence.service

import com.bcaliskan.postgresgroovydemo.model.UserEntityRequest
import com.bcaliskan.postgresgroovydemo.persistence.entity.UserEntity
import com.bcaliskan.postgresgroovydemo.persistence.repository.UserRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Slf4j
@Service
class UserService {

    @Autowired
    UserRepository userRepository


    boolean createUser(UserEntityRequest request) {
        log.info("saving user {} to the database...", request.userName)
        final UUID uuid = UUID.randomUUID()
        final LocalDateTime lastLogin = LocalDateTime.now()
        final Integer userStatus = 1
        final UserEntity entity = new UserEntity()
        entity.with {
            entity.userName = request.userName
            entity.userPass = request.userPass
            entity.uuid = uuid
            entity.lastLogin = lastLogin
            entity.userStatus = userStatus
        }
        return userRepository.save(entity) != null
    }

    List<UserEntity> getAllUsers() {
        return userRepository.findAll().asList()
    }

}
