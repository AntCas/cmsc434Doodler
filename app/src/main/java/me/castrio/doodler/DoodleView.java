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
    private Canvas _canvas = new Canvas();
    private int _currColor;
    private int _currWidth;
    private int _currOpacity;
    
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
    }

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

        Path path;
        Paint paint = new Paint();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                paint.setColor(_currColor);
                paint.setStrokeWidth(_currWidth);
                paint.setAlpha(_currOpacity);
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                path.moveTo(touchx, touchy);
                _lineList.add(new Line(path, paint));
                break;
            case MotionEvent.ACTION_MOVE:
                path = _lineList.get(_lineList.size() - 1)._path;
                path.lineTo(touchx, touchy);
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
