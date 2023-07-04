package com.emebesoft.beerProject.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer_table")
data class BeerEntity(
    @PrimaryKey
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
    val ebc: Double,
    val srm: Double,
    val ph: Double,
    val attenuationLevel: Double,
    val volume: VolumeEntity,
    val boilVolume: BoilVolumeEntity,
    val method: MethodEntity,
    val ingredients: IngredientsEntity,
    val foodPairing: List<String>,
    val brewersTips: String,
    val contributedBy: String
)

data class VolumeEntity (
    val value: Double,
    val unit: String
)

data class BoilVolumeEntity (
    val value: Double,
    val unit: String
)

data class MethodEntity (
    val mashTemp: List<MashTempEntity>,
    val fermentation: FermentationEntity,
    val twist: String?
)

data class MashTempEntity (
    val temp: TempEntity,
    val duration: Int?
)

data class FermentationEntity (
    val temp: TempEntity
)

data class TempEntity (
    val value: Double,
    val unit: String
)

data class IngredientsEntity (
    val malt: List<MaltEntity>,
    val hops: List<HopsEntity>,
    val yeast: String
)

data class AmountEntity (
    val value: Double,
    val unit: String
)

data class MaltEntity (
    val name: String,
    val amount: AmountEntity
)

data class HopsEntity (
    val name: String,
    val amount: AmountEntity,
    val add: String,
    val attribute: String
)