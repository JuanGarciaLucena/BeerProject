package com.emebesoft.beerProject.data.database

import androidx.room.TypeConverter
import com.emebesoft.beerProject.data.database.entity.BoilVolumeEntity
import com.emebesoft.beerProject.data.database.entity.IngredientsEntity
import com.emebesoft.beerProject.data.database.entity.MaltEntity
import com.emebesoft.beerProject.data.database.entity.MethodEntity
import com.emebesoft.beerProject.data.database.entity.VolumeEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromVolumeEntity(volume: VolumeEntity): String {
        return gson.toJson(volume)
    }

    @TypeConverter
    fun toVolumeEntity(volumeJson: String): VolumeEntity {
        val type = object : TypeToken<VolumeEntity>() {}.type
        return gson.fromJson(volumeJson, type)
    }

    @TypeConverter
    fun fromBoilVolumeEntity(boilVolume: BoilVolumeEntity): String {
        return gson.toJson(boilVolume)
    }

    @TypeConverter
    fun toBoilVolumeEntity(boilVolumeJson: String): BoilVolumeEntity {
        val type = object : TypeToken<BoilVolumeEntity>() {}.type
        return gson.fromJson(boilVolumeJson, type)
    }

    @TypeConverter
    fun fromMethodEntity(method: MethodEntity): String {
        return gson.toJson(method)
    }

    @TypeConverter
    fun toMethodEntity(methodJson: String): MethodEntity {
        val type = object : TypeToken<MethodEntity>() {}.type
        return gson.fromJson(methodJson, type)
    }

    @TypeConverter
    fun fromIngredientsEntity(ingredients: IngredientsEntity): String {
        return gson.toJson(ingredients)
    }

    @TypeConverter
    fun toIngredientsEntity(ingredientsJson: String): IngredientsEntity {
        val type = object : TypeToken<IngredientsEntity>() {}.type
        return gson.fromJson(ingredientsJson, type)
    }

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListToMaltEntityList(list: List<MaltEntity>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToMaltEntityList(json: String): List<MaltEntity> {
        val type = object : TypeToken<List<MaltEntity>>() {}.type
        return gson.fromJson(json, type)
    }
}