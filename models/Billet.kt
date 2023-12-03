package com.example.ecotansit.models

import android.os.Parcel
import android.os.Parcelable
data class Billet(
    val _id: String,
    val distance: Double,
    val estimatedPrice: Double,
    val estimatedTime: String,
    val imageName: String

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readDouble() ?: 0.0,
        parcel.readDouble() ?: 0.0,
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )
    override fun describeContents(): Int {
        return 0
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeDouble(distance)
        parcel.writeDouble(estimatedPrice)
        parcel.writeString(estimatedTime)
        parcel.writeString(imageName)


    }

    companion object CREATOR : Parcelable.Creator<Billet> {
        override fun createFromParcel(parcel: Parcel): Billet {
            return Billet(parcel)
        }
        override fun newArray(size: Int): Array<Billet?> {
            return arrayOfNulls(size)
        }
    }
}
data class BilletServiceResponse(
    val statusCode: Int,
    val message: String,
    val billets: List<Billet>
)