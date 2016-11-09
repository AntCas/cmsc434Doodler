package me.castrio.doodler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.w3c.dom.Attr;

import java.util.ArrayList;

/**
 * Created by anthonycastrio on 11/3/16.
 */

public class DoodleView extends View {
    private ArrayList<Line> _lineList = new ArrayList<Line>();
    private int _currColor;
    private int _currWidth;
    private int _currOpacity;
    private boolean _mirrorOn;
    
    private class Line {
        Path _path = new Path();
        Paint _paint = new Paint();
        
        Line(Path path, Paint paint) {
            _path = path;
            _paint = paint;
        }
        
        void drawLine (Canvas canvas) {
            canvas.drawPath(_path, _paint);
        }
    }

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        _currColor = Color.BLUE;
        _currWidth = 5;
        _currOpacity = 255;
        _mirrorOn = false;
    }

    // Getters and Setters
    public void setColor(int color) {
        _currColor = color;
    }

    public int getColor() {
        return this._currColor;
    }
    
    public void setSize(int size) {
        _currWidth = size;
    }

    public int getSize() {
        return this._currWidth;
    }

    public void setOpacity(int opacity) {
        _currOpacity = opacity;
    }

    public int getOpacity() {
        return this._currOpacity;
    }

    public boolean getMirrorOn() {
        return this._mirrorOn;
    }

    public void setMirrorOn(boolean mirrorOn) {
        this._mirrorOn = mirrorOn;
    }

    // Draw all the lines onto the canvas
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Line line : _lineList) {
            line.drawLine(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchx = motionEvent.getX();
        float touchy = motionEvent.getY();
        float mirrorx = this.getWidth() - touchx;
        float mirrory = this.getHeight() - touchy;

        // create invible line
        Paint blankPaint = new Paint();
        blankPaint.setAlpha(0);
        Line invisibleLine = new Line(new Path(), blankPaint);

        // Primary path
        Path path;
        Paint paint = new Paint();

        // x-axis mirrored path
        Path pathMX;
        Paint paintMX = new Paint();

        // y-axis mirrored path
        Path pathMY;
        Paint paintMY = new Paint();

        // x and y mirrored path
        Path pathMXY;
        Paint paintMXY = new Paint();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                pathMX = new Path();
                pathMY = new Path();
                pathMXY = new Path();

                // Set attributes of the current path
                paint.setColor(_currColor);
                paint.setStrokeWidth(_currWidth);
                paint.setAlpha(_currOpacity);

                // These are always the same
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);

                // Move to start point of the path and add to Line list
                path.moveTo(touchx, touchy);
                _lineList.add(new Line(path, paint));

                // handle the mirrored lines
                pathMX.moveTo(mirrorx, touchy);
                pathMY.moveTo(touchx, mirrory);
                pathMXY.moveTo(mirrorx, mirrory);

                if (_mirrorOn) {
                    _lineList.add(new Line(pathMX, paint));
                    _lineList.add(new Line(pathMY, paint));
                    _lineList.add(new Line(pathMXY, paint));
                } else {
                    _lineList.add(invisibleLine);
                    _lineList.add(invisibleLine);
                    _lineList.add(invisibleLine);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                // retrieve the path currently being drawn from the line list
                path = _lineList.get(_lineList.size() - 4)._path;
                pathMX = _lineList.get(_lineList.size() - 3)._path;
                pathMY = _lineList.get(_lineList.size() - 2)._path;
                pathMXY = _lineList.get(_lineList.size() - 1)._path;

                // update the path
                path.lineTo(touchx, touchy);
                pathMX.lineTo(mirrorx, touchy);
                pathMY.lineTo(touchx, mirrory);
                pathMXY.lineTo(mirrorx, mirrory);
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        
        invalidate();
        return true;
    }

    // Redraws the entire canvas white
    public void clearCanvas() {
        _lineList.clear();
        invalidate();
    }
}
