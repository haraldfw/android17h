package org.haraldfw.sudoq.board.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.GridLayout;

/**
 * Created by Harald on 26.11.2017.
 */

public class GridBackground extends Drawable {

    private GridLayout grid;

    private Paint paint;

    GridBackground(GridLayout grid) {
        this.grid = grid;
        paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setStrokeWidth(3);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        float left = grid.getX();
        float right = left + grid.getMeasuredWidth();
        float bottom = grid.getY();
        float top = bottom + grid.getMeasuredHeight();

        left = (left + right) / 3f;
        canvas.drawLine(left, bottom, left, top, paint);

        left *= 2;
        canvas.drawLine(left, bottom, left, top, paint);

        left = grid.getX();
        bottom = (bottom + top) / 3f;
        canvas.drawLine(left, bottom, right, bottom, paint);

        bottom *= 2;
        canvas.drawLine(left, bottom, right, bottom, paint);
    }

    @Override
    public void setAlpha(int i) {
        paint.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
