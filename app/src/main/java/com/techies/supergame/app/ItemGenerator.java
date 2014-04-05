package com.techies.supergame.app;

import java.util.Random;

/**
 * Created by elyassbenhdech on 05/04/2014.
 */
public class ItemGenerator {

    //seconds since last generation
    private int _lastGeneration;

    //seconds max before generation
    private int _maxTime;
    //seconds min before generation
    private int _minTime;

    private int _screenMaxX;
    private int _screenMaxY;

    public ItemGenerator(int lastGeneration, int maxTime, int minTime,
                         int screenMaxX, int screenMaxY){
        this._lastGeneration = lastGeneration;
        this._maxTime = maxTime;
        this._screenMaxX = screenMaxX;
        this._screenMaxY = screenMaxY;
    }

    public Item generate(){
        if (this._lastGeneration > this._maxTime)
            return this.getItem();

        if (this._lastGeneration < this._minTime)
            return null;

        Random r=new Random();
        int time= r.nextInt(this._maxTime);

        if (this._lastGeneration > time)
            return this.getItem();

        return null;
    }

    private Item getItem(){
        Item item = new Item();

        Random r=new Random();
        int y=(r.nextInt(this._screenMaxY));

        item.setX(this._screenMaxX + 10);
        item.setY(y);

        return item;
    }
}
