package com.blog.serachengine.infrastructure.h2

import org.h2.tools.Server
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.sql.SQLException

@Component
class H2ConsoleCustom {
    private var webServer: Server? = null
    private final val h2ConsolePort = 8081

    @EventListener(ContextRefreshedEvent::class)
    @Throws(
        SQLException::class
    )
    fun start() {
        webServer = Server.createWebServer(
            "-webPort", h2ConsolePort.toString(),
            "-tcpAllowOthers"
        ).start()
    }

    @EventListener(ContextClosedEvent::class)
    fun stop() {
        webServer!!.stop()
    }
}