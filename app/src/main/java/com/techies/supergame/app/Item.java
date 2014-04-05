package com.techies.supergame.app;

import java.io.Serializable;

/**
 * Created by elyassbenhdech on 04/04/2014.
 */
public class Item implements Serializable{

    private int X;
    private int Y;
    private int longueur;
    private int largeur;
    private int rotate;

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

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