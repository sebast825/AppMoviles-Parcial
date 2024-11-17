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

        Button(onClick = {
            Log.d("llega el boton","asd")

            ejecutar(ClimaIntencion.actualizarClima)
        }) {
            Text(text = "Refrescar")
        }
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

}

@Composable
fun VacioView(){
    Text(
        text = "No hay anda que mostrar ClimaPage")

}

/*
@Preview (showBackground = true)
@Composable
fun ClimaViewCargando(){
    ClimaView(estado = ClimaEstado.Cargando){}
}

@Preview (showBackground = true)
@Composable
fun ClimaViewError(){
    ClimaView(estado = ClimaEstado.Error("Fallo de no se que cosa")){}
}

@Preview (showBackground = true)
@Composable
fun ClimaViewVacio(){
    ClimaView(estado = ClimaEstado.Vacio){}
}*/