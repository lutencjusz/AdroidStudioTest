package com.example.kalkulatorodleglosci;

public class Cal extends MiaraAnglo implements IAngloP {

    public Cal(double odl) {
        super(odl);
    }

    @Override
    public double policzOdl() {
        return odl/calP;
    }
}
