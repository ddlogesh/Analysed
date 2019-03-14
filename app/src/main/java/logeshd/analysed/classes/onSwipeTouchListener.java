package logeshd.analysed.classes;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class onSwipeTouchListener implements OnTouchListener {
    private final GestureDetector gestureDetector;

    private final class GestureListener extends SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        private GestureListener() {
        }

        public boolean onDown(MotionEvent e) {
            return true;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > 100.0f && Math.abs(velocityX) > 100.0f) {
                        if (diffX > 0.0f) {
                            onSwipeTouchListener.this.onSwipeRight();
                        } else {
                            onSwipeTouchListener.this.onSwipeLeft();
                        }
                        result = true;
                    }
                } else if (Math.abs(diffY) > 100.0f && Math.abs(velocityY) > 100.0f) {
                    if (diffY > 0.0f) {
                        onSwipeTouchListener.this.onSwipeBottom();
                    } else {
                        onSwipeTouchListener.this.onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public onSwipeTouchListener(Context ctx) {
        this.gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    public boolean onTouch(View v, MotionEvent event) {
        return this.gestureDetector.onTouchEvent(event);
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}
