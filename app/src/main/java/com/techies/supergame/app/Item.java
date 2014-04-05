package com.techies.supergame.app;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by elyassbenhdech on 04/04/2014.
 */
public class Item implements Serializable{

    private long X;
    private long Y;
    private long longueur;
    private long largeur;

    public long getLongueur() {
        return longueur;
    }

    public void setLongueur(long longueur) {
        this.longueur = longueur;
    }

    public long getLargeur() {
        return largeur;
    }

    public void setLargeur(long largeur) {
        this.largeur = largeur;
    }

    public Item(){
        this.X = 0;
        this.Y = 0;
    }

    public long getX() {
        return X;
    }

    public void setX(long x) {
        X = x;
    }

    public long getY() {
        return Y;
    }

    public void setY(long y) {
        this.Y = y;
    }

}