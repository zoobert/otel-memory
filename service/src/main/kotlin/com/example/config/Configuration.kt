package com.example.config

import com.example.service.IdGenerator
import io.micrometer.observation.ObservationRegistry
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Bean
    fun idGenerator(): IdGenerator = IdGenerator.instance

    @Bean
    fun observationRegistryConfigurer(observationRegistry: ObservationRegistry): ApplicationRunner = ApplicationRunner {
        ObservationThreadLocalAccessor.getInstance().setObservationRegistry(observationRegistry)
    }
}
