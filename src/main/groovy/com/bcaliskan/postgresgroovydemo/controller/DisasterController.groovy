package com.bcaliskan.postgresgroovydemo.controller

import com.bcaliskan.postgresgroovydemo.model.Response
import com.bcaliskan.postgresgroovydemo.persistence.entity.Disaster
import com.bcaliskan.postgresgroovydemo.persistence.service.DisasterService
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
@RequestMapping('disaster-controller')
class DisasterController {

    @Autowired
    DisasterService disasterService


    @GetMapping('disasters')
    List findAll() {
        return disasterService.findAll()
    }

    @GetMapping('disasters/{id}')
    Disaster findById(@PathVariable long id) {
        return disasterService.findByIdOrReturnNull(id)
    }

    @PostMapping('disasters')
    String create(@RequestBody Disaster disaster) {
        return JsonOutput.toJson(new Response("createDisaster", disasterService.save(disaster)))
    }

    @PutMapping('disasters/{id}')
    String update(@RequestBody Disaster disaster) {
        return JsonOutput.toJson(new Response("updateDisaster", disasterService.update(disaster)))
    }

    @DeleteMapping('disasters/{id}')
    String deleteById(@PathVariable long id) {
        return JsonOutput.toJson(new Response("deleteDisaster", disasterService.deleteById(id)))
    }

    @PostMapping('disasters/{id}/hero/{heroId}')
    String assignHero(@PathVariable long id, @PathVariable long heroId) {
        return JsonOutput.toJson(new Response("assignHero", disasterService.assignHero(id, heroId)))
    }

    @DeleteMapping('disasters/{id}/hero/{heroId}')
    String removeHero(@PathVariable long id, @PathVariable long heroId) {
        return JsonOutput.toJson(new Response("removeHero", disasterService.removeHero(id, heroId)))
    }

    @PostMapping('disasters/{id}')
    String resolveDisaster(@PathVariable long id) {
        return JsonOutput.toJson(new Response("resolveDisaster", disasterService.resolve(id)))
    }

}
