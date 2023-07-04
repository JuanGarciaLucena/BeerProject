package com.emebesoft.beerProject.domain.model

import com.emebesoft.beerProject.data.database.entity.AmountEntity
import com.emebesoft.beerProject.data.database.entity.BeerEntity
import com.emebesoft.beerProject.data.database.entity.BoilVolumeEntity
import com.emebesoft.beerProject.data.database.entity.FermentationEntity
import com.emebesoft.beerProject.data.database.entity.HopsEntity
import com.emebesoft.beerProject.data.database.entity.IngredientsEntity
import com.emebesoft.beerProject.data.database.entity.MaltEntity
import com.emebesoft.beerProject.data.database.entity.MashTempEntity
import com.emebesoft.beerProject.data.database.entity.MethodEntity
import com.emebesoft.beerProject.data.database.entity.TempEntity
import com.emebesoft.beerProject.data.database.entity.VolumeEntity

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String,
    val abv: Double,
    val ibu: Int,
    val targetFg: Int,
    val targetOg: Int,
    val ebc: Int,
    val srm: Int,
    val ph: Double,
    val attenuationLevel: Int,
    val volume: Volume,
    val boilVolume: BoilVolume,
    val method: Method,
    val ingredients: Ingredients,
    val foodPairing: List<String>,
    val brewersTips: String,
    val contributedBy: String
)

data class Volume (
    val value: Int,
    val unit: String
)

data class BoilVolume (
    val value: Int,
    val unit: String
)

data class Method (
    val mashTemp: List<MashTemp>,
    val fermentation: Fermentation,
    val twist: String
)

data class MashTemp (
    val temp: Temp,
    val duration: Int
)

data class Fermentation (
    val temp: Temp
)

data class Temp (
    val value: Int,
    val unit: String
)

data class Ingredients (
    val malt: List<Malt>,
    val hops: List<Hops>,
    val yeast: String
)

data class Amount (
    val value: Int,
    val unit: String
)

data class Malt (
    val name: String,
    val amount: Amount
)

data class Hops (
    val name: String,
    val amount: Amount,
    val add: String,
    val attribute: String
)


fun BeerEntity.toDomain(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        firstBrewed = firstBrewed,
        description = description,
        imageUrl = imageUrl,
        abv = abv,
        ibu = ibu,
        targetFg = targetFg,
        targetOg = targetOg,
        ebc = ebc,
        srm = srm,
        ph = ph,
        attenuationLevel = attenuationLevel,
        volume = volume.toDomain(),
        boilVolume = boilVolume.toDomain(),
        method = method.toDomain(),
        ingredients = ingredients.toDomain(),
        foodPairing = foodPairing,
        brewersTips = brewersTips,
        contributedBy = contributedBy
    )
}

fun VolumeEntity.toDomain(): Volume {
    return Volume(
        value = value,
        unit = unit
    )
}

fun BoilVolumeEntity.toDomain(): BoilVolume {
    return BoilVolume(
        value = value,
        unit = unit
    )
}

fun MethodEntity.toDomain(): Method {
    return Method(
        mashTemp = mashTemp.map { it.toDomain() },
        fermentation = fermentation.toDomain(),
        twist = twist
    )
}

fun MashTempEntity.toDomain(): MashTemp {
    return MashTemp(
        temp = temp.toDomain(),
        duration = duration
    )
}

fun FermentationEntity.toDomain(): Fermentation {
    return Fermentation(
        temp = temp.toDomain()
    )
}

fun TempEntity.toDomain(): Temp {
    return Temp(
        value = value,
        unit = unit
    )
}

fun IngredientsEntity.toDomain(): Ingredients {
    return Ingredients(
        malt = malt.map { it.toDomain() },
        hops = hops.map { it.toDomain() },
        yeast = yeast
    )
}

fun MaltEntity.toDomain(): Malt {
    return Malt(
        name = name,
        amount = amount.toDomain()
    )
}

fun HopsEntity.toDomain(): Hops {
    return Hops(
        name = name,
        amount = amount.toDomain(),
        add = add,
        attribute = attribute
    )
}

fun AmountEntity.toDomain(): Amount {
    return Amount(
        value = value,
        unit = unit
    )
}