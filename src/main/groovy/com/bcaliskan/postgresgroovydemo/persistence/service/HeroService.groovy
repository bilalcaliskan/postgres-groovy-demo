package com.bcaliskan.postgresgroovydemo.persistence.service

import com.bcaliskan.postgresgroovydemo.persistence.entity.HeroEntity
import com.bcaliskan.postgresgroovydemo.persistence.repository.HeroRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException


@Service
@Slf4j
class HeroService {

    @Autowired
    HeroRepository heroRepository;


    List<HeroEntity> findAll() {
        return heroRepository.findAll(Sort.by('name')).asList()
    }

    HeroEntity findByIdOrReturnNull(long id) {
        return heroRepository.findById(id).orElse(null)
    }

    HeroEntity findByIdOrThrowException(long id) {
        return heroRepository.findById(id).orElseThrow({
            new EntityNotFoundException()
        })
    }

    HeroEntity save(HeroEntity hero) {
        // assign hero to every abilities
        hero.abilities.each { ability ->
            log.info("adding {} as ability to the hero {}", ability.name, hero.name)
            ability.hero = hero
        }

        return heroRepository.save(hero)
    }

    HeroEntity update(HeroEntity hero) {
        HeroEntity persisted = findByIdOrThrowException(hero.id)
        persisted.with {
            name = hero.name
        }
        def removeList = []
        // find values to update & delete
        persisted.abilities.each { persistedAbility ->
            log.info("persistedAbility = {}", persistedAbility.name)
            final def tempAbilities = hero.abilities.find { heroAbility ->
                log.info("heroAbility = {}", heroAbility.name)
                heroAbility.id = persistedAbility.id
            }
            if (tempAbilities == null) {
                removeList.add(persistedAbility)
                log.info("Added persistedAbility {} to the removeList", persistedAbility.name)
            } else
                persistedAbility.name = tempAbilities.name
        }

        removeList.each { ability ->
            log.info("Preparing to remove ability {}", ability)
        }
        persisted.abilities.removeAll(removeList)

        // assign persisted entity as the hero
        hero.abilities.each {
            if (it.id == null) {
                it.hero = persisted
                persisted.abilities.add(it)
            }
        }

        return heroRepository.save(persisted)
    }

    boolean deleteById(long id) {
        def hero = findByIdOrThrowException(id)
        heroRepository.delete(hero)
        log.info("Checking if {} is deleted...", hero.name)
        if (findByIdOrReturnNull(hero.id) == null) {
            log.info("Successfully removed hero {}", hero.name)
            return true
        } else {
            log.error("Something went wrong while deleting hero {}", hero.name)
            return false
        }

    }

}
