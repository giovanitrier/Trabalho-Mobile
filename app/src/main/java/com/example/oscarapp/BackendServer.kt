package com.example.oscarapp

import android.util.Log
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

object BackendServer {

    private val usuarios = mutableListOf(
        Usuario("admin", "1234"),
        Usuario("user1", "senha1"),
        Usuario("user2", "senha2")
    )

    fun startServer() {
        embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
            install(ContentNegotiation) {
                gson()
            }

            routing {
                // Rota para autenticação
                post("/auth/login") {
                    val credenciais = call.receive<Usuario>()
                    val usuario = usuarios.find { it.login == credenciais.login && it.senha == credenciais.senha }

                    if (usuario != null) {
                        call.respond(mapOf("success" to true, "message" to "Login bem-sucedido!"))
                    } else {
                        call.respond(mapOf("success" to false, "message" to "Credenciais inválidas"))
                    }
                }

                // Rota para listar usuários
                get("/users") {
                    call.respond(usuarios)
                }
            }
        }.start(wait = false)

        Log.d("BackendServer", "Servidor Ktor iniciado na porta 8080")
    }
}

data class Usuario(val login: String, val senha: String)
