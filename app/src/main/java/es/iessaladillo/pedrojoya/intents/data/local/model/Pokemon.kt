package es.iessaladillo.pedrojoya.intents.data.local.model

import android.os.Parcel
import android.os.Parcelable

// TODO: Define las propiedades de un pokemon
data class Pokemon (
    val power : Int,
    val image : Int,
    val id : Long,
    val name : String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString().toString(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(power)
        parcel.writeInt(image)
        parcel.writeLong(id)
        parcel.writeString(name)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }

}