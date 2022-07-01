package com.example.myApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myApp.data.domain.Creature
import com.example.myApp.ui.theme.MyAppTheme
import com.example.myApp.views.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<CreaturesViewModel> {
            CreatureVMFactory(
                (this.applicationContext as MyAppApplication).repository
            )
        }
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp(viewModel)
                }
            }
        }
    }
}



@Composable
fun MyApp(
    viewModel: CreaturesViewModel
) {
    val navController = rememberNavController()
    CreaturesListScreen(navController = navController, creaturesViewModel = viewModel)
    Scaffold {
        NavHost(navController = navController, startDestination = "creatures"){
            composable("creatures"){
                CreaturesListScreen(navController, viewModel)
            }
            composable(
                route = "creature?name={name}",
                arguments = listOf(navArgument("name"){
                    defaultValue = ""
                    type = NavType.StringType
                })
            ){ navBackStackEntry ->
                val name = navBackStackEntry.arguments?.getString("name") ?: ""
                val creature = getCreature(name, viewModel)
                CreatureDetail(creature)
            }
        }
    }

}

fun getCreature(name: String?, viewModel: CreaturesViewModel): Creature {
    viewModel.creatures.value?.forEach{
        if(name == it.name){
            return it
        }
    }
    return Creature(
        "",
        "",
        ""
    )
}
