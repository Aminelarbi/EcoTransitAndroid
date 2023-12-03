package com.example.ecotansit.models

import android.os.Parcel
import android.os.Parcelable
data class Subscribe(
val _id: String,
val name: String,
val price: Double,
val startDateString: String,
val endDateString: String,
val imageName: String

): Parcelable {
    constructor(parcel: Parcel) : this(
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readDouble() ?: 0.0,

    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    )
    override fun describeContents(): Int {
        return 0
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeString(startDateString)
        parcel.writeString(endDateString)
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
data class SubscribeServiceResponse(
    val statusCode: Int,
    val message: String,
    val subscribes: List<Subscribe>
)