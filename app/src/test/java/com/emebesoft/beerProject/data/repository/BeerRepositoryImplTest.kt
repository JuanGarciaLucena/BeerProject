import com.emebesoft.beerProject.data.model.Amount
import com.emebesoft.beerProject.data.model.BeerModel
import com.emebesoft.beerProject.data.model.BoilVolume
import com.emebesoft.beerProject.data.model.Fermentation
import com.emebesoft.beerProject.data.model.Hops
import com.emebesoft.beerProject.data.model.Ingredients
import com.emebesoft.beerProject.data.model.Malt
import com.emebesoft.beerProject.data.model.MashTemp
import com.emebesoft.beerProject.data.model.Method
import com.emebesoft.beerProject.data.model.Temp
import com.emebesoft.beerProject.data.model.Volume
import com.emebesoft.beerProject.data.network.BeerDataSource
import com.emebesoft.beerProject.data.repository.BeerRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BeerRepositoryImplTest {

    private lateinit var beerDataSource: BeerDataSource
    private lateinit var beerRepository: BeerRepositoryImpl

    @Before
    fun setup() {
        beerDataSource = mockk(relaxed = true)
        beerRepository = BeerRepositoryImpl(beerDataSource)
    }

    @Test
    fun `fetch beers returns flow of beer models`() = runTest {
        // Arrange
        val expectedBeerList = listOf(
            beerModel, beerModel2
        )
        coEvery { beerDataSource.fetchBeersFromApi() } returns expectedBeerList

        // Act
        val result = beerRepository.fetchBeers()

        // Assert
        result.collect { actualBeerList ->
            assertEquals(expectedBeerList, actualBeerList)
        }
    }

    @Test
    fun `search beer returns flow of beer models`() = runTest {
        // Arrange
        val query = "IPA"
        val expectedBeerList = listOf(
            beerModel, beerModel2
        )
        coEvery { beerDataSource.searchBeers(query) } returns expectedBeerList

        // Act
        val result = beerRepository.searchBeer(query)

        // Assert
        result.collect { actualBeerList ->
            assertEquals(expectedBeerList, actualBeerList)
        }
    }

    @Test
    fun `search beer by id returns flow of beer models`() = runTest {
        // Arrange
        val beerId = "123"
        val expectedBeerList = listOf(
            beerModel
        )
        coEvery { beerDataSource.searchBeerById(beerId) } returns expectedBeerList

        // Act
        val result = beerRepository.searchBeerById(beerId)

        // Assert
        result.collect { actualBeerList ->
            assertEquals(expectedBeerList, actualBeerList)
        }
    }

    val beerModel = BeerModel(
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

    val beerModel2 = BeerModel(
        id = 2,
        name = "Example Beer 2",
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
}
