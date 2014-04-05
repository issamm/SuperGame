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

    private final static int DEFAULT_HERO_GOING_UP_SPEED = 15;
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

    public boolean update(List<Item> items, Item hero){
        updateHero(hero);
        boolean collision = updateItems(items, hero);
        return !collision;
    }

    /**
     * Returns True if collision between items and hero.
     * @param items
     * @param hero
     * @return True if collision, false otherwise
     */
    private boolean updateItems(List<Item> items, Item hero){
        if(items == null || items.size() < 1){
            return false;
        }
        Iterator<Item> iterator = items.iterator();

        // For each item to move.
        while(iterator.hasNext()){
            Item currentItem = iterator.next();
            int xBorderLeft = currentItem.getX();
            int xBorderRight = currentItem.getX() + currentItem.getLongueur();
            int yBorderTop = currentItem.getY();
            int yBorderBottom = currentItem.getY() + currentItem.getLargeur();
            /*
             * Do we have to remove the item ?
             */
            boolean removeItem = xBorderRight < 0;
            if(removeItem){
                iterator.remove();
            }
            currentItem.setX(xBorderLeft - DEFAULT_ITEM_GOING_LEFT_SPEED);

            /*
             * Collision between Hero and item ?
             */
            boolean collision = collision(currentItem, hero);
            if(collision){
                return true;
            }
        }
        return false;
    }

    private boolean collision(Item item, Item hero){
        int xBorderLeft = item.getX();
        int xBorderRight = item.getX() + item.getLongueur();
        int yBorderTop = item.getY();
        int yBorderBottom = item.getY() + item.getLargeur();

        int xHeroLeft = hero.getX();
        int xHeroRight = hero.getX() + hero.getLongueur();
        int yHeroTop = hero.getY();
        int yHeroBottom = hero.getY() + hero.getLargeur();

        boolean xCollision = xBorderLeft <= xHeroRight && xBorderLeft >= xHeroLeft;
        boolean yCollisionBottom = yBorderBottom <= yHeroBottom && yBorderBottom >= yHeroTop;
        boolean yCollisionTop = yBorderTop <= yHeroBottom && yBorderTop >= yHeroTop;
        boolean yCollisionMiddle = (yHeroTop <= yBorderBottom && yHeroTop >= yBorderTop)
                                || (yHeroBottom >= yBorderTop && yHeroBottom <= yBorderBottom);
        boolean yCollision = yCollisionBottom || yCollisionTop || yCollisionMiddle;

        if(xCollision && yCollision){
            return true;
        }

        return false;
    }

    private void updateHero(Item itemHero){
        if(itemHero == null){
            return;
        }
        int yBorderTop = itemHero.getY();
        int yBorderBottom = itemHero.getY() + itemHero.getLargeur();

        boolean isPassive = true;
        if(currentMotionEvent != null) {
            int action = currentMotionEvent.getActionMasked();
            boolean moveHero = action == MotionEvent.ACTION_DOWN
                            || action == MotionEvent.ACTION_MOVE;
            if(moveHero){
                isPassive = false;
            }
        }

        /*
         * No action event for the hero.
         */
        if(isPassive) {
            // If going out the screen (bottom).
            if(yBorderBottom > screenHeight){
                itemHero.setY(screenHeight - itemHero.getLargeur());
            }else {
                // Setting the good position.
                itemHero.setY(yBorderTop + DEFAULT_HERO_GOING_DOWN_SPEED);
            }
            return;
        }

        /*
         * Action event : DOWN or MOVE, moving the Hero up.
         */
        if(yBorderTop < 0) {
            itemHero.setY(0);
        } else{
            itemHero.setY(yBorderTop - DEFAULT_HERO_GOING_UP_SPEED);
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