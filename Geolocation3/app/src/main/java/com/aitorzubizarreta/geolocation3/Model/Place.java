package com.aitorzubizarreta.geolocation3.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cursomovil on 8/04/15.
 */
public class Place implements Parcelable {
    private String name;
    private Double latitude;
    private Double longitude;

    // Constructor
    public Place(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter
    public String getName() {
        return name;
    }
    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public Place getPlace() {
        return this;
    }

    /*
    * PARCELABLE
    * */

    // Constructor para crear el objeto a partir de Parcelable
    public Place(Parcel in) {
        name = new String();
        latitude = new Double(null);
        longitude = new Double(null);
        readFromParcel(in);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    // Creamos el parcelable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
    // Clase para recuperar los datos de un parcel.
    // IMPORTANTE! Leer los datos en el mismo orden en el que se escriben
    private void readFromParcel(Parcel parcel) {
        name = parcel.readString();
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
    }
    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {

        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
