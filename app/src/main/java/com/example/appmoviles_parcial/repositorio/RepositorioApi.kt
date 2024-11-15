package com.example.appmoviles_parcial.repositorio

import android.util.Log
import com.example.appmoviles_parcial.repositorio.modelos.Ciudad
import com.example.appmoviles_parcial.repositorio.modelos.Clima
import com.example.appmoviles_parcial.repositorio.modelos.ListForecast
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class RepositorioApi : Repositorio {

    private val apiKey = "95e93e4f7a36fc511148468d1774792d"

    private val cliente = HttpClient(){
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun traerPokemon(): String {
        val respuesta = cliente.get("https://pokeapi.co/api/v2/pokemon/ditto")

        if (respuesta.status == HttpStatusCode.OK){
            val pokemon = respuesta.body<Pokemon>()
            return pokemon.abilities[0].ability.name
        }else{
            throw Exception()
        }


    }

    override suspend fun buscarCiudad(ciudad: String): List<Ciudad> {
        val respuesta = cliente.get("https://api.openweathermap.org/geo/1.0/direct"){
            parameter("q",ciudad)
            parameter("limit",100)
            parameter("appid",apiKey)
        }

        if (respuesta.status == HttpStatusCode.OK){
            val ciudades = respuesta.body<List<Ciudad>>()
            return ciudades
        }else{
            throw Exception()
        }
    }

    override suspend fun traerClima(lat: Float, lon: Float): Clima {
        TODO("Not yet implemented")
    }

    override suspend fun traerPronostico(nombre: String): List<ListForecast> {
        TODO("Not yet implemented")
    }

}




@Serializable
data class Pokemon(
    val abilities: List<AbilitySlot>,
    val base_experience: Int,
    val cries: Cries,
    val forms: List<Form>
)
@Serializable

data class AbilitySlot(
    val ability: Ability,
    val is_hidden: Boolean,
    val slot: Int
)
@Serializable

data class Ability(
    val name: String,
    val url: String
)
@Serializable

data class Cries(
    val latest: String,
    val legacy: String
)
@Serializable

data class Form(
    val name: String,
    val url: String
)
