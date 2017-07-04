package david.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.IdRes;
import android.view.MotionEvent;

/**
 * Created by David on 6/27/2017.
 */

public class DownButton implements Button{
    Bitmap downPic = Constants.BF.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.down_arrow);
    private Rect rectangle = new Rect(Constants.SCREEN_WIDTH / 5, Constants.SCREEN_WIDTH / 5, Constants.SCREEN_HEIGHT / 5, Constants.SCREEN_HEIGHT / 5);
    private Point playerPoint;
    private Point downPoint = new Point(Constants.SCREEN_WIDTH / 5,  9 * Constants.SCREEN_HEIGHT / 10);

    public DownButton(Point playerPoint) {
        this.playerPoint = playerPoint;
    }

    @Override
    public Rect getRectangle() {
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(downPic, null, rectangle, new Paint());
    }

    @Override
    public void update() {
        rectangle.set(downPoint.x - rectangle.width() / 2, downPoint.y - rectangle.height() / 2, downPoint.x + rectangle.width() / 2, downPoint.y + rectangle.height() / 2);
    }

    @Override
    public void onClick(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        if(rectangle.contains(x, y)) {
            playerPoint.set(playerPoint.x, playerPoint.y + 10);
        }
    }
}
