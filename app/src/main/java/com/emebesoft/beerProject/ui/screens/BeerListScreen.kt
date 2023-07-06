package com.emebesoft.beerProject.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.emebesoft.beerProject.ui.viewmodels.BeerViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.emebesoft.baseProject.R
import com.emebesoft.beerProject.domain.model.Beer
import com.emebesoft.beerProject.ui.common.MyToolbar
import com.emebesoft.beerProject.ui.states.SearchBeerUiState

class BeerListScreen(private val navController: NavController) {

    @Composable
    fun BeerList(beerViewModel: BeerViewModel = hiltViewModel()) {

        var queryText by rememberSaveable { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }
        val uiState by beerViewModel.searchBeerListFlow.collectAsStateWithLifecycle()
        var beerList: List<Beer>


        Scaffold(
            topBar = { MyToolbar(title = stringResource(id = R.string.app_name)) })
        {
            it.calculateBottomPadding()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 65.dp)
            ) {

                SearchBar(
                    modifier = Modifier.align(Alignment.TopCenter),
                    query = queryText,
                    onQueryChange = { newQuery ->
                        queryText = newQuery
                        beerViewModel.searchBeers(newQuery)
                    },
                    onSearch = { active = false },
                    active = true,
                    onActiveChange = { isActive ->
                        active = isActive
                    },
                    placeholder = { Text(stringResource(id = R.string.beer_search_hint)) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search, contentDescription = stringResource(
                                id = R.string.content_description_search_beer
                            )
                        )
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                queryText = ""
                                beerViewModel.searchBeers("")
                            })
                    }
                ) {

                    when (uiState) {
                        is SearchBeerUiState.Loading -> {
                            LoadingScreen()
                        }

                        is SearchBeerUiState.Success -> {
                            Log.i("LIST_SCREEN", (uiState as SearchBeerUiState.Success).data.toString())
                            beerList = (uiState as SearchBeerUiState.Success).data
                            if (beerList.isEmpty()) {
                                ErrorScreen(iconId = R.drawable.ic_empty, stringResource(id = R.string.beer_search_empty))
                            } else {
                                FillList(beerList = beerList)
                            }
                        }

                        is SearchBeerUiState.Error -> {
                            ErrorScreen(iconId = R.drawable.ic_error, message = "")
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun FillList(beerList: List<Beer>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(items = beerList, itemContent = { item ->
                ListItemView(item)
            })
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun ListItemView(beerItem: Beer) {

        val keyboardController = LocalSoftwareKeyboardController.current

        Box(modifier = Modifier.padding(10.dp)) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.light_blue)
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .shadow(elevation = 10.dp)
                    .clickable {
                        keyboardController?.hide()
                        navController.navigate("BeerDetailScreen/${beerItem.id}")
                    }
            ) {

                Row {
                    AsyncImage(
                        model = beerItem.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .height(150.dp)
                            .width(75.dp)
                            .padding(10.dp),
                        contentScale = ContentScale.Fit,
                        error = painterResource(id = R.drawable.ic_placeholder)
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
                            color = colorResource(id = R.color.dark_blue),
                        )

                        Text(
                            text = beerItem.tagline,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = colorResource(id = R.color.dark_blue)
                        )
                    }
                }
            }
        }
    }
}