package com.emebesoft.beerProject.data.model

import com.google.gson.annotations.SerializedName

data class BeerModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("first_brewed") val firstBrewed: String,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("abv") val abv: Double,
    @SerializedName("ibu") val ibu: Int,
    @SerializedName("target_fg") val targetFg: Int,
    @SerializedName("target_og") val targetOg: Int,
    @SerializedName("ebc") val ebc: Int,
    @SerializedName("srm") val srm: Int,
    @SerializedName("ph") val ph: Double,
    @SerializedName("attenuation_level") val attenuationLevel: Int,
    @SerializedName("volume") val volume: Volume,
    @SerializedName("boil_volume") val boilVolume: BoilVolume,
    @SerializedName("method") val method: Method,
    @SerializedName("ingredients") val ingredients: Ingredients,
    @SerializedName("food_pairing") val foodPairing: List<String>,
    @SerializedName("brewers_tips") val brewersTips: String,
    @SerializedName("contributed_by") val contributedBy: String
)

data class Volume (

    @SerializedName("value") val value : Int,
    @SerializedName("unit") val unit : String
)

data class BoilVolume (

    @SerializedName("value") val value : Int,
    @SerializedName("unit") val unit : String
)

data class Method (

    @SerializedName("mash_temp") val mashTemp : List<MashTemp>,
    @SerializedName("fermentation") val fermentation : Fermentation,
    @SerializedName("twist") val twist : String
)

data class MashTemp (

    @SerializedName("temp") val temp : Temp,
    @SerializedName("duration") val duration : Int
)

data class Fermentation (

    @SerializedName("temp") val temp : Temp
)

data class Temp (

    @SerializedName("value") val value : Int,
    @SerializedName("unit") val unit : String
)

data class Ingredients (

    @SerializedName("malt") val malt : List<Malt>,
    @SerializedName("hops") val hops : List<Hops>,
    @SerializedName("yeast") val yeast : String
)

data class Amount (

    @SerializedName("value") val value : Int,
    @SerializedName("unit") val unit : String
)

data class Malt (

    @SerializedName("name") val name : String,
    @SerializedName("amount") val amount : Amount
)

data class Hops (

    @SerializedName("name") val name : String,
    @SerializedName("amount") val amount : Amount,
    @SerializedName("add") val add : String,
    @SerializedName("attribute") val attribute : String
)