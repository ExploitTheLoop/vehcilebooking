package com.path.pathfinder;

public class locationcred
{
    static double langtitude,longtitude;
    static String location;
    public static void Setlang(double langt){
        langtitude = langt;
    }
    public static void setlong(double longt){
        longtitude = longt;
    }
    public static double getLangtitude(){
       return langtitude;
    }
    public static double getLongtitude(){
        return longtitude;
    }
}
