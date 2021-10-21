package com.example.kalkulatorodleglosci;

public class MilaLad extends MiaraAnglo implements IAngloP {
    public MilaLad(double odl) {
        super(odl);
    }

    @Override
    public double policzOdl() {
        return odl/milaLP;
    }

}
