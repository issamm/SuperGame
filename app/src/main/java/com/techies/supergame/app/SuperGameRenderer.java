package com.techies.supergame.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by elyassbenhdech on 04/04/2014.
 */
public class SuperGameRenderer {

    private int screenX;
    private int screenY;

    private SuperGameSurfaceView _superGameSurfaceView;

    public SuperGameRenderer(SuperGameSurfaceView superGameSurfaceView){
        this._superGameSurfaceView = superGameSurfaceView;
    }

    public void render(ArrayList<Item> obstacles, Item hero){
        Paint obstaclePaint = new Paint();
        obstaclePaint.setColor(Color.RED);
        obstaclePaint.setStyle(Paint.Style.FILL);

        Paint heroPaint = new Paint();
        heroPaint.setColor(Color.BLUE);
        heroPaint.setStyle(Paint.Style.FILL);

        Canvas canvas = this._superGameSurfaceView.getHolder().lockCanvas();

        for(Item item : obstacles){
            canvas.drawCircle(item.getX(), item.getY(), 60, obstaclePaint);
        }

        canvas.drawCircle(hero.getX(), hero.getY(), 30, heroPaint);
    }
}
