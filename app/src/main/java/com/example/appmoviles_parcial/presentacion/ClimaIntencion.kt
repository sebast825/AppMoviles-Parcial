package com.example.appmoviles_parcial.presentacion


sealed class ClimaIntencion {
    data object actualizarClima: ClimaIntencion()
    data class buscarCiudad (val ciudad : String) : ClimaIntencion()
}