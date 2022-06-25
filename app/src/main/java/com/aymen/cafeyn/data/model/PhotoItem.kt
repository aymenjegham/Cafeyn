package com.aymen.cafeyn.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "Photo", indices = [Index(value = ["id"], unique = true)])
@Parcelize
data class PhotoItem(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "albumId")
    @Expose
    @SerializedName("albumId")
    val albumId: Int,

    @ColumnInfo(name = "thumbnailUrl")
    @Expose
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,

    @ColumnInfo(name = "title")
    @Expose
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "url")
    @Expose
    @SerializedName("url")
    val url: String
) : Parcelable