package com.kdanmobile.androidpractice.screen.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;

public class MyCanvas extends SurfaceView {
    private String TAG = getClass().getSimpleName();

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ArrayList<Path> pathList = new ArrayList<>();
    private Path currentPath;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Path path : pathList)
            canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                currentPath = new Path();
                pathList.add(currentPath);
                currentPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_OUTSIDE:
                currentPath = null;
                break;
        }
        invalidate();
        return true;
    }
}
