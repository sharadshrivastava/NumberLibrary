package com.test.numberslib.data.cache.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(indices = [Index(value = ["value"], unique = true)])
data class Number(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "value") val value: Int
) : Parcelable