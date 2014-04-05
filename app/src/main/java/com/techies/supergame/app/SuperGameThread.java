package com.techies.supergame.app;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.techies.supergame.app.SuperGameSurfaceView;
import com.techies.supergame.app.SuperGameUpdater;

import java.util.ArrayList;

/**
 * Created by elyassbenhdech on 01/04/2014.
 */
public class SuperGameThread extends Thread{
    private static final String TAG = SuperGameThread.class.getSimpleName();

    private SurfaceHolder surfaceHolder;
    private SuperGameSurfaceView _superGameSurfaceView;

    private int _screenHeight = 0;
    private int _screenWidth = 0;
    private long _lastGeneration = 0;
    private int _minTime = 2000;
    private int _maxTime = 5000;

    private boolean running;
    private final static int 	MAX_FPS = 50;
    private final static int	MAX_FRAME_SKIPS = 5;
    private final static int	FRAME_PERIOD = 1000 / MAX_FPS;

    private SuperGameUpdater _gameUpdater;
    private ItemGenerator _itemGenerator;
    private SuperGameRenderer _superGameRenderer;

    ArrayList<Item> _obstacles;
    Item _hero;

    public SuperGameThread(SurfaceHolder surfaceHolder, SuperGameSurfaceView gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this._superGameSurfaceView = gamePanel;
        this._obstacles = new ArrayList<Item>();
        this._hero = new Item();

    }

    private void init(){
        this._screenHeight = this._superGameSurfaceView.getHeight();
        this._screenWidth = this._superGameSurfaceView.getWidth();
        this._gameUpdater = new SuperGameUpdater(this._screenHeight, this._screenWidth);
        this._itemGenerator = new ItemGenerator(this._minTime,this._maxTime,
                this._screenWidth, this._screenHeight);
        this._superGameRenderer = new SuperGameRenderer(this._superGameSurfaceView);

        this._hero.setX(420);
        this._hero.setY(this._screenHeight);
        this._hero.setLargeur(32);
        this._hero.setLongueur(32);
    }


    @Override
    public void run() {
        Canvas canvas;

        long beginTime;
        long timeDiff;
        int sleepTime = 0;
        int framesSkipped;

        init();

        while (running) {
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    beginTime = System.currentTimeMillis();
                    framesSkipped = 0;

                    this._lastGeneration++;
                    //Generating Item
                    Item item = this._itemGenerator.generate(this._lastGeneration);
                    if (item != null){
                        this._lastGeneration = System.currentTimeMillis();
                        this._obstacles.add(item);
                    }

                    //Updating Game
                    running = this._gameUpdater.update(_obstacles, _hero);

                    //rendering game
                    this._superGameRenderer.render(canvas, this._obstacles, this._hero);


                    timeDiff = System.currentTimeMillis() - beginTime;
                    sleepTime = (int)(FRAME_PERIOD - timeDiff);
                    if (sleepTime > 0) {
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {}
                    }

                    while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                        running = this._gameUpdater.update(_obstacles, _hero);
                        sleepTime += FRAME_PERIOD;
                        framesSkipped++;
                    }
                }
            } finally {
                if (canvas != null) {
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public SuperGameUpdater getGameUpdater() {
        return this._gameUpdater;
    }

    public void setRunning(boolean running) {

        this.running = running;
    }
}
