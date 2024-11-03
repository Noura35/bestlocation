package com.example.bestlocation;

public class Position {
    int idposition;
    String pseudo,longitude,numero,latitude;

    public Position(int idposition, String pseudo, String numero,String longitude, String latitude) {
        this.idposition = idposition;
        this.pseudo = pseudo;
        this.numero = numero;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "idposition=" + idposition +
                ", pseudo='" + pseudo + '\'' +
                ", numero='" + numero + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

    public String getPseudo() {
        return this.pseudo;
    }
}