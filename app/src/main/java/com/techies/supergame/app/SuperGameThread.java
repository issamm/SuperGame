package com.techies.supergame.app;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.techies.supergame.app.SuperGameSurfaceView;
import com.techies.supergame.app.SuperGameUpdater;

/**
 * Created by elyassbenhdech on 01/04/2014.
 */
public class SuperGameThread extends Thread{
    private static final String TAG = SuperGameThread.class.getSimpleName();

    private SurfaceHolder surfaceHolder;
    private SuperGameSurfaceView gamePanel;

    private boolean running;
    private final static int 	MAX_FPS = 50;
    private final static int	MAX_FRAME_SKIPS = 5;
    private final static int	FRAME_PERIOD = 1000 / MAX_FPS;
    private SuperGameUpdater gameUpdater;

    public SuperGameThread(SurfaceHolder surfaceHolder, SuperGameSurfaceView gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        this.gameUpdater = new SuperGameUpdater();
    }

    @Override
    public void run() {
        Canvas canvas;

        long beginTime;
        long timeDiff;
        int sleepTime = 0;
        int framesSkipped;

        while (running) {
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    beginTime = System.currentTimeMillis();
                    framesSkipped = 0;

                    running = gameUpdater.update();
                    gameUpdater.render(canvas);

                    timeDiff = System.currentTimeMillis() - beginTime;
                    sleepTime = (int)(FRAME_PERIOD - timeDiff);
                    if (sleepTime > 0) {
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {}
                    }

                    while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                        running = gameUpdater.update();
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
        return gameUpdater;
    }

    public void setRunning(boolean running) {

        this.running = running;
    }
}
