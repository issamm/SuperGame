package com.techies.supergame.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by elyassbenhdech on 04/04/2014.
 */
public class SuperGameRenderer {

    private SuperGameSurfaceView _superGameSurfaceView;

    public SuperGameRenderer(SuperGameSurfaceView superGameSurfaceView){
        _superGameSurfaceView = superGameSurfaceView;
    }

    public void render(Canvas canvas, ArrayList<Item> obstacles, Item hero){
        if (canvas== null)
            return;

        canvas.drawColor(0, PorterDuff.Mode.CLEAR);

        Bitmap alien = BitmapFactory.decodeResource(this._superGameSurfaceView.getResources(), R.drawable.alien);
        Bitmap asteroid = BitmapFactory.decodeResource(this._superGameSurfaceView.getResources(), R.drawable.asteroid);

        Paint obstaclePaint = new Paint();
        obstaclePaint.setColor(Color.RED);
        obstaclePaint.setStyle(Paint.Style.FILL);

        Paint heroPaint = new Paint();
        heroPaint.setColor(Color.BLUE);
        heroPaint.setStyle(Paint.Style.FILL);

        //Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        Matrix matrix = new Matrix();

        for(Item item : obstacles){
            item.setRotate(item.getRotate()+1);

            matrix.reset();
            matrix.postRotate(item.getRotate());

            Bitmap currentAsteroid = Bitmap.createBitmap(asteroid, 0, 0, asteroid.getWidth(), asteroid.getHeight(),
                    matrix, true);

            canvas.drawBitmap(asteroid, item.getX(), item.getY(), obstaclePaint);
        }

        canvas.drawBitmap(alien, hero.getX(), hero.getY(), heroPaint);
    }


}
