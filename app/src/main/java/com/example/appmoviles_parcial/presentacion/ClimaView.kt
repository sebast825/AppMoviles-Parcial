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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun ClimaView (
    modifier: Modifier = Modifier,
    estado: ClimaEstado,
    //Unit es como void en c#
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
            is ClimaEstado.Exitoso -> ExitosoView(estado.clima)
            is ClimaEstado.Vacio -> VacioView()

        }
        Button(onClick = {
            Log.d("llega el boton","asd")

            ejecutar(ClimaIntencion.actualizar)
        }) {
            Text(text = "Refrescar")
        }
    }
}

@Composable
fun CargandoView(){
    Log.d("cargando", "vista")
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
fun ExitosoView(clima: Clima){

}@Composable
fun VacioView(){
    Text(
        text = "No hay anda que mostrar")

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