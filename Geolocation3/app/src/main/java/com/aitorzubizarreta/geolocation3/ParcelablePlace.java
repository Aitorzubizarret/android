package com.aitorzubizarreta.geolocation3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cursomovil on 9/04/15.
 */
public class ParcelablePlace implements Parcelable {

    private int mData;
    private String mName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
        out.writeString(mName);
    }

    public static final Parcelable.Creator<ParcelablePlace> CREATOR = new Parcelable.Creator<ParcelablePlace>() {
        @Override
        public ParcelablePlace createFromParcel(Parcel in) {
            return new ParcelablePlace(in);
        }

        @Override
        public ParcelablePlace[] newArray(int size) {
            return new ParcelablePlace[size];
        }
    };
}
