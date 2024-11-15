package com.example.appmoviles_parcial.presentacion


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appmoviles_parcial.repositorio.Repositorio
import com.example.appmoviles_parcial.repositorio.RepositorioApi


@Composable
fun ClimaPage(

    modifier: Modifier = Modifier
) {

    val viewModel : ClimaViewModel = viewModel(
        factory = ClimaViewModelFactory(
            repositorio = RepositorioApi(),
            34.0901,
            -118.4065
        )
    )

    
    ClimaView(
        modifier = Modifier,
        estado = viewModel.estado

    ){
        viewModel.ejecutar((it))
    }

}


class ClimaViewModelFactory(
    private val repositorio: Repositorio,
    private val lat: Double,
    private val lon: Double,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimaViewModel::class.java)) {
            return ClimaViewModel(repositorio,lat,lon) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


/*
class ClimaViewModelFactory(
    private val repositorio: Repositorio,
    //private val router: Router
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimaViewModel::class.java)) {
            return ClimaViewModel(repositorio) as T
            //return ClimaViewModel(repositorio,router) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/