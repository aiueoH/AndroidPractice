package com.kdanmobile.androidpractice.screen.imagemanipulation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.RelativeLayout;

public class ManipulationContainer extends RelativeLayout {
    private String TAG = getClass().getSimpleName();

    private View selectedItem = null;
    private float downX, downY;
    private int itemOriginalLeftMargin, itemOriginalTopMargin;
    private float itemOriginalRotation;
    private ScaleGestureDetector scaleGestureDetector;
    private RotationGestureDetector rotationGestureDetector;

    public ManipulationContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        scaleGestureDetector = new ScaleGestureDetector(getContext(), new OnScaleGestureListener());
        rotationGestureDetector = new RotationGestureDetector(new OnRotationGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        rotationGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "action down.");
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d(TAG, "action pointer down.");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "action move");
                if (selectedItem != null) {
                    MarginLayoutParams layoutParams = (MarginLayoutParams) selectedItem.getLayoutParams();
                    float deltaX = event.getX() - downX;
                    float deltaY = event.getY() - downY;
                    layoutParams.setMargins(itemOriginalLeftMargin + (int) deltaX, itemOriginalTopMargin + (int) deltaY, 0, 0);
                    selectedItem.setLayoutParams(layoutParams);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "action up");
                selectedItem = null;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, "action pointer up");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                selectedItem = null;
                break;
        }
        return true;
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        child.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        selectedItem = v;
                        MarginLayoutParams layoutParams = (MarginLayoutParams) v.getLayoutParams();
                        itemOriginalLeftMargin = layoutParams.leftMargin;
                        itemOriginalTopMargin = layoutParams.topMargin;
                        break;
                }
                return false;
            }
        });
    }

    public void clear() {
        removeAllViews();
    }

    private class OnRotationGestureListener implements RotationGestureDetector.OnRotationGestureListener {
        @Override
        public void onRotation(RotationGestureDetector detector) {
            float angle = -detector.getAngle();
            for (int i = 0; i < getChildCount(); i++) {
                View item = getChildAt(i);
                item.setRotation(itemOriginalRotation + angle);
            }
        }

        @Override
        public void onRotationBegin(RotationGestureDetector detector) {
            if (getChildCount() > 0)
                itemOriginalRotation = getChildAt(0).getRotation();
        }

        @Override
        public void onRotationEnd(RotationGestureDetector detector) {

        }
    }

    private class OnScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float factor = detector.getScaleFactor();
            for (int i = 0; i < getChildCount(); i++) {
                View item = getChildAt(i);
                item.setScaleX(item.getScaleX() * factor);
                item.setScaleY(item.getScaleY() * factor);
            }
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}