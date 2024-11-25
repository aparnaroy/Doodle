package com.example.doodle.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class DoodleView extends View {
    public static final float TOUCH_TOLERANCE = 10;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private Paint paintScreen; // The pencil on the screen
    private Paint paintLine; // The actual line we can use to draw
    private HashMap<Integer, Path> pathMap; // The path of the drawn lines
    private HashMap<Integer, Point> previousPointMap;

    public DoodleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    void init() {
        paintScreen = new Paint();

        paintLine = new Paint();
        paintLine.setAntiAlias(true); // Make lines smooth
        paintLine.setColor(Color.BLACK);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(10);
        paintLine.setStrokeCap(Paint.Cap.ROUND); // Make end of drawn line round

        pathMap = new HashMap<>();
        previousPointMap = new HashMap<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888); // Holds all the points/ lines drawn
        bitmapCanvas = new Canvas(bitmap); // Puts all drawn points/lines on the screen/canvas
        bitmap.eraseColor(Color.WHITE); // Color we will be "drawing" with when erasing
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, paintScreen); // Start canvas from top left corner of screen

        for (Integer key: pathMap.keySet()) {
            canvas.drawPath(pathMap.get(key), paintLine);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked(); // Event type (ex: first touching screen, releasing / letting go)
        int actionIndex = event.getActionIndex(); // The pointer (ex: finger or mouse)

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_UP) {
            // If screen has been touched
            touchStarted(event.getX(actionIndex), event.getY(actionIndex), event.getPointerId(actionIndex));
        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP) {
            // If screen has been released
            touchEnded(event.getPointerId(actionIndex));
        } else {
            // If something is being drawn (screen is being held down and finger/mouse is moving around on screen)
            touchMoved(event);
        }

        invalidate(); // Redraw the screen (so you can see things being drawn on the screen)

        return true;
    }

    private void touchStarted(float x, float y, int pointerId) {
        Path path; // Store the path for given touch
        Point point; // Store the last point in the path

        if (pathMap.containsKey(pointerId)) {
            path = pathMap.get(pointerId);
            point = previousPointMap.get(pointerId);
        } else {
            // If pathMap is empty, this is a new touch
            path = new Path();
            pathMap.put(pointerId, path);
            point = new Point();
            previousPointMap.put(pointerId, point);
        }

        // Move to the coordinates of the touch
        path.moveTo(x, y);
        point.x = (int) x;
        point.y = (int) y;
    }

    private void touchMoved(MotionEvent event) {
        for (int i = 0; i < event.getPointerCount(); i++) {
            int pointerId = event.getPointerId(i);
            int pointerIndex = event.findPointerIndex(pointerId);

            if (pathMap.containsKey(pointerId)) {
                float newX = event.getX(pointerIndex);
                float newY = event.getY(pointerIndex);

                Path path = pathMap.get(pointerId);
                Point point = previousPointMap.get(pointerId);

                // Calculate how far the user moved from the last update
                float deltaX = Math.abs(newX - point.x);
                float deltaY = Math.abs(newY - point.y);

                // If the distance is significant enough to be considered a movement, create the path
                if (deltaX >= TOUCH_TOLERANCE || deltaY >= TOUCH_TOLERANCE) {
                    // Move th path to the new location
                    path.quadTo(point.x, point.y, (newX + point.x) / 2, (newY + point.y) / 2);

                    // Store the new coordinates
                    point.x = (int) newX;
                    point.y = (int) newY;
                }
            }
        }
    }

    private void touchEnded(int pointerId) {
        Path path = pathMap.get(pointerId); // Gte the corresponding Path
        bitmapCanvas.drawPath(path, paintLine); // Draw to bitmapCanvas object
        path.reset();
    }

    public void clear() {
        pathMap.clear(); // Removes all previous paths/lines
        previousPointMap.clear();
        bitmap.eraseColor(Color.WHITE); // White to match the background
        invalidate(); // Refresh the screen
    }

    public void setDrawingColor(int color) {
        paintLine.setColor(color);
    }

    public int getDrawingColor() {
        return paintLine.getColor();
    }

    public void setLineWidth(int width) {
        paintLine.setStrokeWidth(width);
    }

    public int getLineWidth() {
        return (int) paintLine.getStrokeWidth();
    }
}
