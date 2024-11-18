package com.example.appmoviles_parcial.presentacion

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appmoviles_parcial.ciudades.CiudadesIntencion
import java.text.SimpleDateFormat

import java.util.Date


@Composable
fun ClimaView (
    modifier: Modifier = Modifier,
    estado: ClimaEstado,
    //Unit es como void en c#
    ciudad: String,
    ejecutar: (ClimaIntencion) -> Unit

    ) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ){
        when(estado){
            is ClimaEstado.Cargando -> CargandoView()
            is ClimaEstado.Error -> ErrorView(estado.mensaje)
            is ClimaEstado.Exitoso -> ExitosoView(estado.data)
            is ClimaEstado.Vacio -> VacioView()

        }
        LaunchedEffect(Unit) {
            ejecutar(ClimaIntencion.buscarCiudad(ciudad))
        }


        Button(
            onClick = { ejecutar(ClimaIntencion.cambiarCiudad) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Volver",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )

        }
    }
}


@Composable
fun VacioView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No hay nada que mostrar",

            )
    }
}

@Composable
fun CargandoView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cargando...",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ErrorView(mensaje: String){
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = mensaje,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Composable
fun ExitosoView(data: ClimaAndPronostico) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
              //  .weight(1f)

        ) {
            Text(text = data.ciudad ?: "Ciudad no disponible", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = data.descripcion ?: "Descripción no disponible", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(text = "Sensación Térmica:", style = MaterialTheme.typography.bodyLarge)
                Text(text = data.st?.toString() ?: "No disponible", style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(text = "Temperatura:", style = MaterialTheme.typography.bodyLarge)
                Text(text = data.temperatura?.toString() ?: "No disponible", style = MaterialTheme.typography.bodyLarge)
            }   }

        Column(
            modifier = Modifier
                //.weight(6f)
                .padding(top = 50.dp)

        ) {
            Text(text = "Pronostico Extendido", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(10.dp))

            data.pronostico?.forEach { pronostico ->
                val fechaFormateada = convertirTimestampAFecha(pronostico.dt)
                Text(text = fechaFormateada, style = MaterialTheme.typography.headlineSmall)
                Text(text = "Max: ${pronostico.main.temp_max}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Min: ${pronostico.main.temp_min}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}






fun convertirTimestampAFecha(timestamp: Long): String {
    // Crear un objeto Date con el timestamp (en milisegundos)
    val fecha = Date(timestamp * 1000)  // El timestamp es en segundos, así que multiplicamos por 1000

    // Formato de fecha deseado
    val formato = SimpleDateFormat("dd/MM/yyyy")  // Solo la fecha, sin la hora
    return formato.format(fecha)
}

/*
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun convertirTimestampAFecha(timestamp: Long): String {
    val instant = Instant.ofEpochSecond(timestamp)  // Convierte el timestamp a un Instant
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")  // Solo la fecha, sin la hora
        .withZone(ZoneId.systemDefault())  // Usa la zona horaria predeterminada
    return formatter.format(instant)
}

fun main() {
    val timestamp = 1732309200L
    println(convertirTimestampAFecha(timestamp))  // Salida: 23/09/2025
}


*/