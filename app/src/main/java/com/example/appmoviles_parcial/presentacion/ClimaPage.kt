package com.example.appmoviles_parcial.presentacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ClimaPage() {

    val viewModel = ClimaViewModel()
    ClimaView(
        modifier = Modifier,
        estado = viewModel.estado,

    ){
        viewModel.ejecutar((it))
    }

}