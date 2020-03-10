package ru.droidcat.civilizationfaq

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tech(var name: String?,
                var helptext: String?,
                var graphic: String?,
                var graphic_alt: String?,
                var flags: String?,
                var req1: String?,
                var req2: String?,
                var description: String?,
                var format_version: String?,
                var options: String?) : Parcelable

    /*constructor(parcel: Parcel) : this (
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.name)
        parcel.writeString(this.helptext)
        parcel.writeString(this.graphic)
        parcel.writeString(this.graphic_alt)
        parcel.writeString(this.flags)
        parcel.writeString(this.req1)
        parcel.writeString(this.req2)
        parcel.writeString(this.description)
        parcel.writeString(this.format_version)
        parcel.writeString(this.options)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tech> {
        override fun createFromParcel(parcel: Parcel): Tech {
            return Tech(parcel)
        }

        override fun newArray(size: Int): Array<Tech?> {
            return arrayOfNulls(size)
        }
    }*/