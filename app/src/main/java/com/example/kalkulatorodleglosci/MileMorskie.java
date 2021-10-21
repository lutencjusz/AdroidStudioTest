package com.example.kalkulatorodleglosci;

public class MileMorskie extends MiaraAnglo implements IAngloP{
    @Override
    public double policzOdl() {
        return odl/milaP;
    }

    public MileMorskie(double odl) {
        super(odl);
    }
}
