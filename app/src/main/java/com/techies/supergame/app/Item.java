package com.techies.supergame.app;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by elyassbenhdech on 04/04/2014.
 */
public class Item implements Serializable{

    private int X;
    private int Y;
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

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        this.Y = y;
    }

}