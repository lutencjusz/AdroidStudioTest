package com.example.kalkulatorodleglosci;

public class Jard extends MiaraAnglo implements IAngloP {

    public Jard(double odl) {
        super(odl);
    }

    @Override
    public double policzOdl() {
        return odl/jardP;
    }
}
