package com.gene.trynewarchitecture.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gene.trynewarchitecture.room.entity.ExceptionList

@Dao
interface ExceptionListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exceptionList: ExceptionList)

    @Query("SELECT * FROM exception_list WHERE type = :type")
    fun getExceptionList(type: String): LiveData<ExceptionList>
}