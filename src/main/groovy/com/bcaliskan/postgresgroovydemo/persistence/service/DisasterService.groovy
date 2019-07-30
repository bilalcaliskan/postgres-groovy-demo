package com.bcaliskan.postgresgroovydemo.persistence.service


import com.bcaliskan.postgresgroovydemo.persistence.entity.DisasterEntity
import com.bcaliskan.postgresgroovydemo.persistence.repository.DisasterRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException


@Service
@Slf4j
class DisasterService {

    @Autowired // tell Spring to inject value from Spring component
    DisasterRepository disasterRepository

    @Autowired
    HeroService heroService


    List findAll() {
        return disasterRepository.findAll((Sort.by(Sort.Order.desc("time"))))
    }

    DisasterEntity findByIdOrReturnNull(Long id) {
        disasterRepository.findById(id).orElse(null)
    }

    DisasterEntity findByIdOrThrowException(Long id) {
        return disasterRepository.findById(id).orElseThrow({
            new EntityNotFoundException()
        })
    }

    boolean save(DisasterEntity disaster) {
        disaster.isResolved = false
        if (disasterRepository.save(disaster))
            return true
        return false
    }

    boolean update(DisasterEntity disaster) {
        def persisted = findByIdOrThrowException(disaster.id)
        persisted.with {
            title = disaster.title
            location = disaster.location
            time = disaster.time
        }

        log.info("Updating disaster {} with values:\ntitle = {}\nlocation = {}\ntime = {}",
                disaster.title, disaster.location, disaster.time)
        if (disasterRepository.save(disaster)) {
            log.info("Successfully updated {}, returning true...", disaster.title)
            return true
        }

        log.error("An error occured while updating {}, check the logs!", disaster.title)
        return false
    }

    boolean assignHero(long id, long heroId) {
        def disaster = findByIdOrThrowException(id)
        def hero = heroService.findByIdOrThrowException(heroId)

        disaster.heroes.add(hero)
        if (disasterRepository.save(disaster)) {
            log.info("Successfully added hero {} to the disaster {}, returning true...", hero.name,
                    disaster.title)
            return true
        }

        log.error("An error occured while adding hero {} to the disaster {}, check the logs!",
                hero.name, disaster.title)
        return false
    }

    boolean removeHero(long id, long heroId) {
        def disaster = findByIdOrThrowException(id)
        def hero = heroService.findByIdOrThrowException(heroId)

        if (disaster.heroes.remove(hero)) {
            log.info("Successfully removed hero {} from disaster {}, returning true...", hero.name,
                    disaster.title)
            return true
        }

        log.error("An error occured while removing hero {} from disaster {}, check the logs!",
                hero.name, disaster.title)
        return false
    }

    boolean resolve(long id) {
        def disaster = findByIdOrThrowException(id)
        disaster.isResolved = true

        if (disaster.heroes.remove(hero)) {
            log.info("Successfully resolved disaster {}, returning true...", disaster.title)
            return true
        }

        log.error("An error occured while resolving disaster {}, check the logs!", disaster.title)
        return false
    }

    boolean deleteById(long id) {
        def disaster = findByIdOrThrowException(id)
        disasterRepository.delete(disaster)
        log.info("Checking if {} is deleted...", disaster.title)
        if (findByIdOrReturnNull(disaster.id) == null) {
            log.info("Successfully removed disaster {}", disaster.title)
            return true
        }

        log.error("Something went wrong while deleting disaster {}", disaster.title)
        return false
    }

}