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
    private final int screenHeight;
    private final int screenWidth;

    public SuperGameUpdater(int screenHeight, int screenWidth){
        this.currentMotionEvent = null;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void update(List<Item> items, Item hero){
        updateItems(items);
        updateHero(hero);
    }

    private void updateItems(List<Item> items){
        if(items == null || items.size() < 1){
            return;
        }
        Iterator<Item> iterator = items.iterator();

        // For each item to move.
        while(iterator.hasNext()){
            Item currentItem = iterator.next();
            int xBorderLeft = currentItem.getX();
            int xBorderRight = currentItem.getX() + currentItem.getLongueur();
            int yBorderTop = currentItem.getY();
            int yBorderBottom = currentItem.getY() + currentItem.getLargeur();
            // Do we have to remove the item ?
            boolean removeItem = xBorderRight < 0;
            if(removeItem){
                iterator.remove();
            }
            currentItem.setX(xBorderLeft - DEFAULT_ITEM_GOING_LEFT_SPEED);
        }
    }

    private void updateHero(Item itemHero){
        if(itemHero == null){
            return;
        }
        int yBorderTop = itemHero.getY();

        boolean isPassive = currentMotionEvent == null;
        if(isPassive) {
            itemHero.setY(yBorderTop + DEFAULT_HERO_GOING_DOWN_SPEED);
            return;
        }
        int actionMasked = currentMotionEvent.getActionMasked();
        boolean moveHero = actionMasked == MotionEvent.ACTION_MOVE;
        if(moveHero) {
            itemHero.setY(yBorderTop - DEFAULT_HERO_GOING_DOWN_SPEED);
        }
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