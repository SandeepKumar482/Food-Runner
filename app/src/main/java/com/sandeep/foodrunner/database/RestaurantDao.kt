package com.sandeep.foodrunner.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RestaurantDao {
    @Insert
    fun insertRestaurant(restaurantEntity: RestaurantEntity)

    @Delete
    fun deleteBook(restaurantEntity: RestaurantEntity)

    @Query("SELECT * FROM Restaurants")
    fun getAllBooks(): List<RestaurantEntity>

    @Query("SELECT * FROM Restaurants WHERE RestaurantId = :restaurant_id")
    fun getBookById(restaurant_id: String): RestaurantEntity
}