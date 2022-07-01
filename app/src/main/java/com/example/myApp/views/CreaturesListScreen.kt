package com.example.myApp.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myApp.data.domain.Creature

@Composable
fun CreaturesListScreen(
    navController: NavController,
    creaturesViewModel: CreaturesViewModel
) {
    val creaturesList by creaturesViewModel.creatures.observeAsState(listOf())
    CreaturesList(navController = navController, creaturesList = creaturesList)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreaturesList(
    navController: NavController,
    creaturesList: List<Creature>
) {
    LazyVerticalGrid(
        modifier = Modifier.background(Color.LightGray),
        cells = GridCells.Fixed(1)
    ){
        items(creaturesList){ creatures ->
            CreaturesEntry(creatures = creatures, onView = { navController.navigate("creature?name=${creatures.name}") })
        }
    }
}


@Composable
fun CreaturesEntry(
    creatures: Creature,
    onView: () -> Unit
){
    Card(
        modifier = Modifier.padding(6.dp),
        elevation = 8.dp
    ) {
        Box {

                Text(
                    text = creatures.name,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.dp),
                    style = MaterialTheme.typography.h5.copy(
                        color = Color.Black
                    ),
                )
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .align(Alignment.CenterEnd)
                        .size(32.dp)
                        .clickable {
                            onView()
                        },
                    imageVector = Icons.Default.Search,
                    contentDescription = "View"
                )
            }

    }
}



