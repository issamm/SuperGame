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
import java.util.List;

/**
 * Created by elyassbenhdech on 04/04/2014.
 */
public class SuperGameUpdater {
    private static final String TAG = SuperGameUpdater.class.getSimpleName();
    private MotionEvent currentMotionEvent;

    ArrayList<Item> _obstacles;
    Item _hero;

    public SuperGameUpdater(ArrayList<Item> obstacles, Item hero){
        this.currentMotionEvent = null;
        this._obstacles = obstacles;
        this._hero = hero;
    }

    public boolean update(){
        if(this.currentMotionEvent == null)
            return true;

        this.addItem();
        this.logEvent();
        this.currentMotionEvent = null;
        return true;
    }

    public void render(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        for(Item item : this._obstacles){
            canvas.drawCircle(item.getX(), item.getY(), 50, paint);
        }
    }

    private void logEvent(){
        if(this.currentMotionEvent == null)
            return;
        if (this.currentMotionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "Coords: x=" + this.currentMotionEvent.getX() + ",y=" + this.currentMotionEvent.getY());
        }
    }

    private void addItem(){
        Item item = new Item();
        item.setX(this.currentMotionEvent.getX());
        item.setY(this.currentMotionEvent.getY());
        this._obstacles.add(item);
    }

    public MotionEvent getCurrentMotionEvent() {
        return currentMotionEvent;
    }

    public void setCurrentMotionEvent(MotionEvent currentMotionEvent) {
        this.currentMotionEvent = currentMotionEvent;
    }
}
