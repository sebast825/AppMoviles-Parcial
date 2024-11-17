package com.example.appmoviles_parcial.presentacion

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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

      /*  Button(onClick = {
            Log.d("llega el boton","asd")

            ejecutar(ClimaIntencion.actualizarClima)
        }) {
            Text(text = "Refrescar")
        }*/
    }
}

@Composable
fun CargandoView(){

    Text(text = "Cargando")
}

@Composable
fun ErrorView(mensaje: String){
    Text(
        text = mensaje,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.error)

}
@Composable
fun ExitosoView(data : ClimaAndPronostico ){


    Column {
        Text(text = data.ciudad ?: "Ciudad no disponible")
        Text(text = data.descripcion ?: "Descripción no disponible")
        Text(text = data.st?.toString() ?: "Temperatura no disponible")
        Text(text = data.temperatura?.toString() ?: "Temperatura no disponible")

    }

    Column {
        data.pronostico?.forEach{
                pronostico ->
            var fechaFormatiada = convertirTimestampAFecha(pronostico.dt)
            Text(text = fechaFormatiada)

            Text(text = pronostico.main.temp_max.toString())
            Text(text = pronostico.main.temp_min.toString())


        }
    }


}

@Composable
fun VacioView(){
    Text(
        text = "No hay anda que mostrar ClimaPage")

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