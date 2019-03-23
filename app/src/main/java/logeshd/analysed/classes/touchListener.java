package logeshd.analysed.classes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class touchListener implements OnItemTouchListener {
    private ClickListener clickListener;
    private GestureDetector gestureDetector;

    public interface ClickListener {
        void onClick(View view, int i);
        void onLongClick(View view, int i);
    }

    public touchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        this.gestureDetector = new GestureDetector(context, new SimpleOnGestureListener() {
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    if (clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            }
        });
    }

    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && this.clickListener != null && this.gestureDetector.onTouchEvent(e)) {
            this.clickListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
