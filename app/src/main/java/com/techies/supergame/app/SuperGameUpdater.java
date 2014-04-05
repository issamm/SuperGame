package com.techies.supergame.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;

import com.techies.supergame.app.Item;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;

/**
 * Created by elyassbenhdech on 04/04/2014.
 */
public class SuperGameUpdater {

    private final static int DEFAULT_HERO_GOING_DOWN_SPEED = 10;
    private final static int DEFAULT_ITEM_GOING_LEFT_SPEED = 5;

    private static final String TAG = SuperGameUpdater.class.getSimpleName();
    private MotionEvent currentMotionEvent;

    public SuperGameUpdater(long screenHeight, long screenWidth){
        this.currentMotionEvent = null;
    }

    public boolean update(List<Item> items, Item hero){
        updateItems(items);

        if(this.currentMotionEvent == null)
            return true;

        this.logEvent();
        this.currentMotionEvent = null;
        return true;
    }

    private void updateItems(List<Item> items){
        if(items == null || items.size() < 1){
            return;
        }
        Iterator<Item> iterator = items.iterator();
        //while(iterator.hasNext()){

        //}
    }

    private void updateHero(Item itemHero){
        // TODO : update hero.
    }

    private void logEvent(){
        if(this.currentMotionEvent == null)
            return;
        if (this.currentMotionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "Coords: x=" + this.currentMotionEvent.getX() + ",y=" + this.currentMotionEvent.getY());
        }
    }

    public MotionEvent getCurrentMotionEvent() {
        return currentMotionEvent;
    }

    public void setCurrentMotionEvent(MotionEvent currentMotionEvent) {
        this.currentMotionEvent = currentMotionEvent;
    }

}