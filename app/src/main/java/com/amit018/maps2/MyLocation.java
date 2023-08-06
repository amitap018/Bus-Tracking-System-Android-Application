package com.amit018.maps2;

public class MyLocation {
  //  private double latitude;
   // private double longitude;
    private float f_latitude;
    private float f_longitude;
/*
    public MyLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MyLocation(){}

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

 */
public MyLocation(float f_latitude, float f_longitude) {
    this.f_latitude = f_latitude;
    this.f_longitude = f_longitude;
}

    public MyLocation(){}

    public float getLatitude() {
        return f_latitude;
    }

    public void setLatitude(float f_latitude) {
        this.f_latitude = f_latitude;
    }

    public float getLongitude() {
        return f_longitude;
    }

    public void setLongitude(float f_longitude) {
        this.f_longitude = f_longitude;
    }


}
