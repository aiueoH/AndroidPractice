package com.kdanmobile.androidpractice.screen.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MyClock extends SurfaceView {
    private String TAG = getClass().getSimpleName();
    private Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint hourHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint minuteHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint secondHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MyClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setupPaint();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                postInvalidate();
            }
        }, 0, 1000);
    }

    private void setupPaint() {
        circlePaint.setColor(Color.GREEN);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10);
        hourHandPaint.setColor(Color.BLUE);
        hourHandPaint.setStyle(Paint.Style.STROKE);
        hourHandPaint.setStrokeWidth(5);
        minuteHandPaint.setColor(Color.RED);
        minuteHandPaint.setStyle(Paint.Style.STROKE);
        minuteHandPaint.setStrokeWidth(3);
        secondHandPaint.setColor(Color.WHITE);
        secondHandPaint.setStyle(Paint.Style.STROKE);
        secondHandPaint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawClock(canvas);
    }

    private void drawClock(Canvas canvas) {
        float centerX = canvas.getWidth() / 2;
        float centerY = canvas.getHeight() / 2;
        float radius = Math.min(canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.drawCircle(centerX, centerY, radius, circlePaint);
        Calendar calendar = Calendar.getInstance();
        float degree;
        degree = calendar.get(Calendar.HOUR) * 360 / 12;
        drawHand(canvas, degree, centerX, centerY, radius * 0.5f, hourHandPaint);
        degree = calendar.get(Calendar.MINUTE) * 360 / 60;
        drawHand(canvas, degree, centerX, centerY, radius * 0.7f, minuteHandPaint);
        degree = calendar.get(Calendar.SECOND) * 360 / 60;
        drawHand(canvas, degree, centerX, centerY, radius * 0.9f, secondHandPaint);
    }

    private void drawHand(Canvas canvas, float degree, float centerX, float centerY, float length, Paint paint) {
        canvas.save();
        canvas.rotate(degree, centerX, centerY);
        canvas.drawLine(centerX, centerY, centerX, centerY - length, paint);
        canvas.restore();
    }

}
