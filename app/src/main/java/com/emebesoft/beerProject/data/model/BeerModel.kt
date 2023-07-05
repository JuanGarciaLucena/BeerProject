package com.emebesoft.beerProject.data.model

import com.emebesoft.beerProject.domain.model.Beer
import com.google.gson.annotations.SerializedName

data class BeerModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("first_brewed") val firstBrewed: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("abv") val abv: Double?,
    @SerializedName("ibu") val ibu: Double?,
    @SerializedName("target_fg") val targetFg: Double?,
    @SerializedName("target_og") val targetOg: Double?,
    @SerializedName("ebc") val ebc: Double?,
    @SerializedName("srm") val srm: Double?,
    @SerializedName("ph") val ph: Double?,
    @SerializedName("attenuation_level") val attenuationLevel: Double?,
    @SerializedName("volume") val volume: Volume,
    @SerializedName("boil_volume") val boilVolume: BoilVolume,
    @SerializedName("method") val method: Method,
    @SerializedName("ingredients") val ingredients: Ingredients,
    @SerializedName("food_pairing") val foodPairing: List<String>,
    @SerializedName("brewers_tips") val brewersTips: String?,
    @SerializedName("contributed_by") val contributedBy: String?
)

data class Volume (

    @SerializedName("value") val value : Double?,
    @SerializedName("unit") val unit : String?
)

data class BoilVolume (

    @SerializedName("value") val value : Double?,
    @SerializedName("unit") val unit : String?
)

data class Method (

    @SerializedName("mash_temp") val mashTemp : List<MashTemp>,
    @SerializedName("fermentation") val fermentation : Fermentation,
    @SerializedName("twist") val twist : String?
)

data class MashTemp (

    @SerializedName("temp") val temp : Temp,
    @SerializedName("duration") val duration : Int?
)

data class Fermentation (

    @SerializedName("temp") val temp : Temp
)

data class Temp (

    @SerializedName("value") val value : Double?,
    @SerializedName("unit") val unit : String?
)

data class Ingredients (

    @SerializedName("malt") val malt : List<Malt>,
    @SerializedName("hops") val hops : List<Hops>,
    @SerializedName("yeast") val yeast : String?
)

data class Amount (

    @SerializedName("value") val value : Double?,
    @SerializedName("unit") val unit : String?
)

data class Malt (

    @SerializedName("name") val name : String?,
    @SerializedName("amount") val amount : Amount
)

data class Hops (

    @SerializedName("name") val name : String?,
    @SerializedName("amount") val amount : Amount,
    @SerializedName("add") val add : String?,
    @SerializedName("attribute") val attribute : String?
)



//Model object to Domain object
fun BeerModel.toDomain(): Beer {
    return Beer(
        id = id,
        name = name ?: "",
        tagline = tagline ?: "",
        firstBrewed = firstBrewed ?: "",
        description = description ?: "",
        imageUrl = imageUrl ?: "",
        abv = abv ?: 0.0,
        ibu = ibu ?: 0.0,
        targetFg = targetFg ?: 0.0,
        targetOg = targetOg ?: 0.0,
        ebc = ebc ?: 0.0,
        srm = srm ?: 0.0,
        ph = ph ?: 0.0,
        attenuationLevel = attenuationLevel ?: 0.0,
        volume = volume.toDomain(),
        boilVolume = boilVolume.toDomain(),
        method = method.toDomain(),
        ingredients = ingredients.toDomain(),
        foodPairing = foodPairing,
        brewersTips = brewersTips ?: "",
        contributedBy = contributedBy ?: ""
    )
}

fun Volume.toDomain(): com.emebesoft.beerProject.domain.model.Volume {
    return com.emebesoft.beerProject.domain.model.Volume(
        value = value ?: 0.0,
        unit = unit ?: ""
    )
}

fun BoilVolume.toDomain(): com.emebesoft.beerProject.domain.model.BoilVolume {
    return com.emebesoft.beerProject.domain.model.BoilVolume(
        value = value ?: 0.0,
        unit = unit ?: ""
    )
}

fun Method.toDomain(): com.emebesoft.beerProject.domain.model.Method {
    return com.emebesoft.beerProject.domain.model.Method(
        mashTemp = mashTemp.map { it.toDomain() },
        fermentation = fermentation.toDomain(),
        twist = twist ?: ""
    )
}

fun MashTemp.toDomain(): com.emebesoft.beerProject.domain.model.MashTemp {
    return com.emebesoft.beerProject.domain.model.MashTemp(
        temp = temp.toDomain(),
        duration = duration ?: 0
    )
}

fun Fermentation.toDomain(): com.emebesoft.beerProject.domain.model.Fermentation {
    return com.emebesoft.beerProject.domain.model.Fermentation(
        temp = temp.toDomain()
    )
}

fun Temp.toDomain(): com.emebesoft.beerProject.domain.model.Temp {
    return com.emebesoft.beerProject.domain.model.Temp(
        value = value ?: 0.0,
        unit = unit ?: ""
    )
}

fun Ingredients.toDomain(): com.emebesoft.beerProject.domain.model.Ingredients {
    return com.emebesoft.beerProject.domain.model.Ingredients(
        malt = malt.map { it.toDomain() },
        hops = hops.map { it.toDomain() },
        yeast = yeast ?:""
    )
}

fun Malt.toDomain(): com.emebesoft.beerProject.domain.model.Malt {
    return com.emebesoft.beerProject.domain.model.Malt(
        name = name ?: "",
        amount = amount.toDomain()
    )
}

fun Hops.toDomain(): com.emebesoft.beerProject.domain.model.Hops {
    return com.emebesoft.beerProject.domain.model.Hops(
        name = name ?: "",
        amount = amount.toDomain(),
        add = add ?: "",
        attribute = attribute ?: ""
    )
}

fun Amount.toDomain(): com.emebesoft.beerProject.domain.model.Amount {
    return com.emebesoft.beerProject.domain.model.Amount(
        value = value ?: 0.0,
        unit = unit ?: ""
    )
}