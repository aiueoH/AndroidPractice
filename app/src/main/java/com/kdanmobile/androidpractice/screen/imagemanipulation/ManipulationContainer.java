package com.kdanmobile.androidpractice.screen.imagemanipulation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class ManipulationContainer extends RelativeLayout {
    private String TAG = getClass().getSimpleName();

    private View selectedItem = null;
    private float downX, downY;
    private int itemOriginalLeftMargin, itemOriginalTopMargin;

    public ManipulationContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (selectedItem != null) {
                    MarginLayoutParams layoutParams = (MarginLayoutParams) selectedItem.getLayoutParams();
                    float deltaX = event.getX() - downX;
                    float deltaY = event.getY() - downY;
                    layoutParams.setMargins(itemOriginalLeftMargin + (int) deltaX, itemOriginalTopMargin + (int) deltaY, 0, 0);
                    selectedItem.setLayoutParams(layoutParams);
                }
                break;
            case MotionEvent.ACTION_UP:
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
}
