package com.bezzo.moviecatalogue.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = AppConstant.DATA_MOVIE)
data class Movie(
    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    @Expose
    val popularity: Double,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    @Expose
    val voteCount: Int,

    @SerializedName("video")
    @ColumnInfo(name = "video")
    @Expose
    val video: Boolean,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    @Expose
    val posterPath: String,

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @Expose
    @PrimaryKey
    @NonNull
    val id: Int,

    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    @Expose
    val adult: Boolean,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    @Expose
    val backdropPath: String,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    @Expose
    val originalLanguage: String,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    @Expose
    val originalTitle: String,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    @Expose
    val title: String,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    @Expose
    val voteAverage: Double,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    @Expose
    val overview: String,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    @Expose
    val releaseDate: String
) : Parcelable