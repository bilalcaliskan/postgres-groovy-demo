package com.bcaliskan.postgresgroovydemo.controller

import com.bcaliskan.postgresgroovydemo.model.Response
import com.bcaliskan.postgresgroovydemo.persistence.entity.Hero
import com.bcaliskan.postgresgroovydemo.persistence.service.HeroService
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("hero-controller")
class HeroController {

    @Autowired
    HeroService heroService


    @GetMapping('')
    List findAll() {
        return heroService.findAll()
    }

    @GetMapping('{id}')
    Hero findOne(@PathVariable long id) {
        return heroService.findByIdOrReturnNull(id)
    }

    @PostMapping('')
    String create(@RequestBody Hero hero) {
        return JsonOutput.toJson(new Response("createHero", heroService.save(hero)))
    }

    @PutMapping('')
    String update(@RequestBody Hero hero) {
        return JsonOutput.toJson(new Response("updateHero", heroService.update(hero)))
    }

    @DeleteMapping('{id}')
    String deleteById(@PathVariable long id) {
        return JsonOutput.toJson(new Response("deleteHero", heroService.deleteById(id)))
    }

}
