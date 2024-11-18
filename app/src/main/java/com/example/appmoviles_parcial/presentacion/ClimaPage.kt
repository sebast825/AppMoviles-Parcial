package com.example.appmoviles_parcial.presentacion


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appmoviles_parcial.repositorio.Repositorio
import com.example.appmoviles_parcial.repositorio.RepositorioApi


@Composable
fun ClimaPage(

    modifier: Modifier = Modifier,
    ciudad : String = "",
    navHostController: NavHostController,

    ) {

    val viewModel : ClimaViewModel = viewModel(
        factory = ClimaViewModelFactory(
            repositorio = RepositorioApi(),
            navController = navHostController

        )
    )

    
    ClimaView(
        modifier = Modifier,
        estado = viewModel.estado,
        ciudad = ciudad

    ){
        viewModel.ejecutar((it))
    }

}


class ClimaViewModelFactory(
    private val repositorio: Repositorio,
    private val navController: NavHostController

) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimaViewModel::class.java)) {
            return ClimaViewModel(repositorio,navController) as T
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