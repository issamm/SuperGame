package com.techies.supergame.app;

import java.util.Random;

/**
 * Created by elyassbenhdech on 05/04/2014.
 */
public class ItemGenerator {

    //seconds max before generation
    private int _maxTime;
    //seconds min before generation
    private int _minTime;

    private int _screenMaxX;
    private int _screenMaxY;

    public ItemGenerator(int minTime, int maxTime,
                         int screenMaxX, int screenMaxY){
        this._minTime = minTime;
        this._maxTime = maxTime;
        this._screenMaxX = screenMaxX;
        this._screenMaxY = screenMaxY;
    }

    public Item generate(long lastGeneration){
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastGeneration > this._maxTime)
            return this.getItem();

        if (currentTime - lastGeneration < this._minTime)
            return null;

        Random r=new Random();
        int time= r.nextInt(this._maxTime);

        if (currentTime - lastGeneration > time)
            return this.getItem();

        return null;
    }

    private Item getItem(){
        Item item = new Item();

        Random r=new Random();
        int y=(r.nextInt(this._screenMaxY));

        item.setX(this._screenMaxX + 10);
        item.setY(y);

        item.setLargeur(50);
        item.setLongueur(50);

        item.setRotate(0);

        return item;
    }
}
