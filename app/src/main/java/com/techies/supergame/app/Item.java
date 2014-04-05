package com.techies.supergame.app;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by elyassbenhdech on 04/04/2014.
 */
public class Item implements Serializable{

    private float X;
    private float Y;
    private int longueur;
    private int largeur;

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public Item(){
        this.X = 0;
        this.Y = 0;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        this.Y = y;
    }
}
