package com.bcaliskan.postgresgroovydemo.persistent.service

import com.bcaliskan.postgresgroovydemo.model.request.UserEntityRequest
import com.bcaliskan.postgresgroovydemo.persistent.entity.UserEntity
import com.bcaliskan.postgresgroovydemo.persistent.repository.UserRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
@Slf4j
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

    boolean login(UserEntityRequest request) {
        if (isUserExists(request))
            return isPasswordValid(request)
        return false
    }

    boolean isUserExists(UserEntityRequest request) {
        return Objects.nonNull(getUserByName(request.userName))
    }

    boolean isPasswordValid(UserEntityRequest request) {
        final UserEntity userEntity = getUserByName(request.userName)
        return userEntity.userPass == request.userPass
    }

    UserEntity getUserByName(String userName) {
        final Optional<UserEntity> userEntity = userRepository.findByUserName(userName)
        return userEntity.orElseThrow({ -> new Exception("User not found!")})
    }

}
