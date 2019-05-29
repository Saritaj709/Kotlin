package com.example.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class KotlinMongoApplication

fun main(args: Array<String>) {
	runApplication<KotlinMongoApplication>(*args)
}
