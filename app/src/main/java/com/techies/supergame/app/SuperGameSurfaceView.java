package com.techies.supergame.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.techies.supergame.app.SuperGameThread;

/**
 * Created by elyassbenhdech on 01/04/2014.
 */
public class SuperGameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SuperGameThread thread;
    private static final String TAG = SuperGameSurfaceView.class.getSimpleName();

    public SuperGameSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);

        // create the game loop thread
        thread = new SuperGameThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(this.thread.getGameUpdater().getCurrentMotionEvent() == null)
            this.thread.getGameUpdater().setCurrentMotionEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    }
}
