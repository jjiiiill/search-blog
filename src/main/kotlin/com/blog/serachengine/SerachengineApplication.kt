package com.blog.serachengine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@ConfigurationPropertiesScan(basePackages = ["com.blog.searchengine"])
@SpringBootApplication
class SerachengineApplication

fun main(args: Array<String>) {
	runApplication<SerachengineApplication>(*args)
}
