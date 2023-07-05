package com.emebesoft.beerProject.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.emebesoft.baseProject.R
import com.emebesoft.beerProject.domain.model.Amount
import com.emebesoft.beerProject.domain.model.Beer
import com.emebesoft.beerProject.domain.model.BoilVolume
import com.emebesoft.beerProject.domain.model.Fermentation
import com.emebesoft.beerProject.domain.model.Hops
import com.emebesoft.beerProject.domain.model.Ingredients
import com.emebesoft.beerProject.domain.model.Malt
import com.emebesoft.beerProject.domain.model.MashTemp
import com.emebesoft.beerProject.domain.model.Method
import com.emebesoft.beerProject.domain.model.Temp
import com.emebesoft.beerProject.domain.model.Volume
import com.emebesoft.beerProject.ui.common.MyToolbar
import com.emebesoft.beerProject.ui.states.SearchBeerByIdUiState
import com.emebesoft.beerProject.ui.viewmodels.BeerViewModel
import com.emebesoft.beerProject.utils.Constants

class BeerDetailScreen(private val navController: NavController, private val beerId: String) {

    @Composable
    fun Detail(beerViewModel: BeerViewModel = hiltViewModel()) {


        val uiState by beerViewModel.searchBeerByIdFlow.collectAsStateWithLifecycle()

        when (uiState) {
            is SearchBeerByIdUiState.Loading -> {
                Log.i("LOADING", "LOADING")
            }

            is SearchBeerByIdUiState.Success -> {
                Log.i("BEER_ID", beerId)
                BeerInfo((uiState as SearchBeerByIdUiState.Success).data.first())
            }

            is SearchBeerByIdUiState.Error -> {
                Log.i("ERROR", "error")
            }
        }

        LaunchedEffect(beerId) {
            beerViewModel.searchBeerById(beerId)
        }
    }


    @Composable
    fun BeerInfo(beer: Beer) {
        Scaffold(
            topBar = {
                MyToolbar(
                    title = "",
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.content_description_search_beer),
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        ) {
            it.calculateBottomPadding()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    AsyncImage(
                        model = beer.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(175.dp)
                            .padding(top = 20.dp),
                        contentScale = ContentScale.Inside,
                        error = painterResource(id = R.drawable.ic_placeholder)
                    )

                    Text(
                        text = beer.name,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.dark_blue),
                        fontSize = 25.sp,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )

                    Text(
                        text = beer.description,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp),
                        color = colorResource(id = R.color.dark_blue)
                    )

                    Divider(
                        color = colorResource(id = R.color.blue),
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        BeerInfoItem(
                            R.drawable.ic_alcohol,
                            stringResource(R.string.beer_detail_alcohol, beer.abv.toString())
                        )
                        BeerInfoItem(
                            R.drawable.ic_hop,
                            stringResource(R.string.beer_detail_ibu, beer.ibu.toString())
                        )
                        BeerInfoItem(
                            R.drawable.ic_ph,
                            stringResource(R.string.beer_detail_ph, beer.ph.toString())
                        )
                    }

                    Divider(
                        color = colorResource(id = R.color.blue),
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(id = R.color.blue)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp, top = 20.dp),
                            shape = RoundedCornerShape(5.dp)
                        ) {
                            Column(Modifier.padding(20.dp)) {
                                Text(
                                    text = stringResource(R.string.beer_detail_brewer_tip),
                                    fontSize = 25.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(bottom = 10.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = beer.brewersTips,
                                    color = Color.White,
                                    fontStyle = FontStyle.Italic
                                )
                                Text(
                                    text = beer.contributedBy,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }
                    }

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(id = R.color.blue)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp, top = 20.dp),
                            shape = RoundedCornerShape(5.dp)
                        ) {
                            Column(Modifier.padding(20.dp)) {
                                Text(
                                    text = stringResource(R.string.beer_detail_food_pairing),
                                    fontSize = 25.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(bottom = 10.dp),
                                    fontWeight = FontWeight.Bold
                                )

                                beer.foodPairing.forEach { food ->
                                    Text(
                                        text = food,
                                        fontStyle = FontStyle.Italic,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun BeerInfoItem(iconId: Int, data: String) {
        Box {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Image(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = iconId),
                    contentDescription = Constants.DATABASE_NAME
                )

                Text(
                    modifier = Modifier.wrapContentWidth(),
                    text = data,
                    color = colorResource(id = R.color.dark_blue),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview
@Composable
fun MyComposablePreview() {
    BeerDetailScreen(rememberNavController(), "1").BeerInfo(
        Beer(
            id = 1,
            name = "Example Beer",
            tagline = "Tasty and refreshing",
            firstBrewed = "01/2022",
            description = "A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.",
            imageUrl = "https://images.punkapi.com/v2/keg.png",
            abv = 5.0,
            ibu = 25.0,
            targetFg = 1010.0,
            targetOg = 1050.0,
            ebc = 15.0,
            srm = 8.0,
            ph = 4.5,
            attenuationLevel = 80.0,
            volume = Volume(value = 330.0, unit = "ml"),
            boilVolume = BoilVolume(value = 25.0, unit = "liters"),
            method = Method(
                mashTemp = listOf(
                    MashTemp(temp = Temp(value = 65.0, unit = "C"), duration = 60)
                ),
                fermentation = Fermentation(temp = Temp(value = 18.0, unit = "C")),
                twist = "Add some spices"
            ),
            ingredients = Ingredients(
                malt = listOf(
                    Malt(name = "Pale Malt", amount = Amount(value = 3.5, unit = "kg"))
                ),
                hops = listOf(
                    Hops(
                        name = "Cascade",
                        amount = Amount(value = 30.0, unit = "g"),
                        add = "start",
                        attribute = "bitter"
                    ),
                    Hops(
                        name = "Amarillo",
                        amount = Amount(value = 20.0, unit = "g"),
                        add = "middle",
                        attribute = "flavor"
                    )
                ),
                yeast = "Ale yeast"
            ),
            foodPairing = listOf(
                "Grilled chicken",
                "Spicy tacos",
                "Cheese platter"
            ),
            brewersTips = "The earthy and floral aromas from the hops can be overpowering. Drop a little Cascade in at the end of the boil to lift the profile with a bit of citrus.",
            contributedBy = "Sam Mason <samjbmason>"
        )
    )
}