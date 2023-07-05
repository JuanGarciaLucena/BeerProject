package com.emebesoft.beerProject.domain.model

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String,
    val abv: Double,
    val ibu: Double,
    val targetFg: Double,
    val targetOg: Double,
    val ebc: Double?,
    val srm: Double?,
    val ph: Double?,
    val attenuationLevel: Double,
    val volume: Volume,
    val boilVolume: BoilVolume,
    val method: Method,
    val ingredients: Ingredients,
    val foodPairing: List<String>,
    val brewersTips: String,
    val contributedBy: String
)

data class Volume (
    val value: Double,
    val unit: String
)

data class BoilVolume (
    val value: Double,
    val unit: String
)

data class Method (
    val mashTemp: List<MashTemp>,
    val fermentation: Fermentation,
    val twist: String?
)

data class MashTemp (
    val temp: Temp,
    val duration: Int?
)

data class Fermentation (
    val temp: Temp
)

data class Temp (
    val value: Double,
    val unit: String
)

data class Ingredients (
    val malt: List<Malt>,
    val hops: List<Hops>,
    val yeast: String
)

data class Amount (
    val value: Double,
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

