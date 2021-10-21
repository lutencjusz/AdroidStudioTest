package com.example.kalkulatorodleglosci;

public class Stopa extends MiaraAnglo implements IAngloP{

    public Stopa(double odl) {
        super(odl);
    }

    @Override
    public double policzOdl() {
        return odl/stopaP;
    }
}
