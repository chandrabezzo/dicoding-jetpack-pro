package com.bezzo.moviecatalogue.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bezzo.moviecatalogue.constanta.AppConstant
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity(tableName = AppConstant.DATA_TV)
data class TvShow(
    @SerializedName("original_name")
    @ColumnInfo(name = "original_name")
    @Expose
    var originalName: String,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    @Expose
    var name: String,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    @Expose
    var popularity: Double,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    @Expose
    var voteCount: Int,

    @SerializedName("first_air_date")
    @ColumnInfo(name = "first_air_date")
    @Expose
    var firstAirDate: String,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    @Expose
    var backdropPath: String,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    @Expose
    var originalLanguage: String,

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @Expose
    var id: Int,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    @Expose
    var voteAverage: Double,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    @Expose
    var overview: String,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    @Expose
    var posterPath: String
) : Parcelable