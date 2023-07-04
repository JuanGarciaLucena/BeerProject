package com.emebesoft.beerProject.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.emebesoft.beerProject.ui.states.BeerListUiState
import com.emebesoft.beerProject.ui.viewmodels.BeerViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.emebesoft.baseProject.R
import com.emebesoft.beerProject.domain.model.Beer
import com.emebesoft.beerProject.ui.common.MyToolbar

class BeerListScreen(private val navController: NavController) {

    @Composable
    fun BeerListUiStateManager(beerViewModel: BeerViewModel = hiltViewModel()) {

        val uiState by beerViewModel.beerListFlow.collectAsStateWithLifecycle()


        when (uiState) {
            is BeerListUiState.Loading -> {
                Log.i("LOADING", "LOADING")
            }

            is BeerListUiState.Success -> {
                BeerList(beerList = (uiState as BeerListUiState.Success).data)
            }

            is BeerListUiState.Error -> {
                Log.i("ERROR", "error")
            }
        }
    }

    @Composable
    fun BeerList(beerList: List<Beer>) {

        Scaffold(topBar = { MyToolbar(title = stringResource(id = R.string.app_name)) }) {
            it.calculateBottomPadding()

            LazyColumn(modifier = Modifier
                .fillMaxHeight()
                .padding(top = 65.dp)) {
                items(items = beerList, itemContent = { item ->
                    ListItemView(item)
                })
            }
        }
    }

    @Composable
    fun ListItemView(beerItem: Beer) {

        Box(modifier = Modifier.padding(10.dp)) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.purple_200)
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .shadow(elevation = 10.dp)
                    .clickable { navController.navigate("BeerDetailScreen/${beerItem.id}") }
            ) {

                Row {
                    AsyncImage(
                        model = beerItem.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .height(150.dp)
                            .width(75.dp)
                            .padding(top = 10.dp, bottom = 10.dp),
                        contentScale = ContentScale.Fit,
                    )

                    Column(
                        modifier = Modifier
                            .height(150.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = beerItem.name,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            color = Color.White,
                        )

                        Text(
                            text = beerItem.tagline,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}