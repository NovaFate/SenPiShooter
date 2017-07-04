package david.game;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by David on 6/28/2017.
 */

public interface Button {
    public Rect getRectangle();
    public void draw(Canvas canvas);
    public void update();
    public void onClick(MotionEvent ev);
}
