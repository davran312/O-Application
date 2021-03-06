package sample.test.models

import android.os.Parcel
import android.os.Parcelable

data class Comment(var postId: Int,
                   var id: Int,
                   var name: String? = null,
                   var email: String? = null,
                   var body: String? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(postId)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comment> {
        override fun createFromParcel(parcel: Parcel): Comment {
            return Comment(parcel)
        }

        override fun newArray(size: Int): Array<Comment?> {
            return arrayOfNulls(size)
        }
    }
}