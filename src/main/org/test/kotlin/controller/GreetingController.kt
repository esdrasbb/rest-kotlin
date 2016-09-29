package org.test.kotlin.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

data class Greeting(val id: Long, val content: String)

@RestController
@RequestMapping("/greeting")
class GreetingController {

    val greetings = mutableListOf<Greeting>()

    @RequestMapping("/", method = arrayOf(RequestMethod.GET))
    fun list(): List<Greeting> {
        return greetings
    }

    @RequestMapping("/{id}", method = arrayOf(RequestMethod.GET))
    fun find(@PathVariable id: Long): Greeting? {
        return greetings.firstOrNull { g -> g.id == id }
    }

    @RequestMapping("/{id}", method = arrayOf(RequestMethod.POST))
    fun add(@PathVariable id: Long): Greeting {
        greetings.add(Greeting(id, "Hello, " + id))
        return greetings.last()
    }

    @RequestMapping("/{id}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable id: Long): Greeting? {
        val greeting = greetings.firstOrNull { g -> g.id == id }
        greetings.remove(greeting)
        return greeting
    }
}