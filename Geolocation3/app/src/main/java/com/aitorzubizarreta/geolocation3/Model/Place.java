package com.aitorzubizarreta.geolocation3.Model;

/**
 * Created by cursomovil on 8/04/15.
 */
public class Place {
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
}
