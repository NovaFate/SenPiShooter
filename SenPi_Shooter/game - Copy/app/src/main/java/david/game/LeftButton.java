package david.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by David on 6/28/2017.
 */

public class LeftButton implements Button {
    Bitmap leftPic = Constants.BF.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.left_arrow);
    private Rect rectangle = new Rect(Constants.SCREEN_WIDTH / 5, Constants.SCREEN_WIDTH / 5, Constants.SCREEN_HEIGHT / 5, Constants.SCREEN_HEIGHT / 5);
    private Point playerPoint;
    private Point rightPoint = new Point(Constants.SCREEN_WIDTH / 10, (int) 8.5 * Constants.SCREEN_HEIGHT / 10);
//    private Point leftPoint = new Point(Constants.SCREEN_WIDTH / 10, 79 * Constants.SCREEN_HEIGHT / 100);

    public LeftButton(Point playerPoint) {
        this.playerPoint = playerPoint;
    }

    @Override
    public Rect getRectangle() {
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(leftPic, null, rectangle, new Paint());
    }

    @Override
    public void update() {
        rectangle.set(rightPoint.x - rectangle.width() / 2, rightPoint.y - rectangle.height() / 2, rightPoint.x + rectangle.width() / 2, rightPoint.y + rectangle.height() / 2);
//        rectangle.set(leftPoint.x - rectangle.width() / 2, leftPoint.y - rectangle.height() / 2, leftPoint.x + rectangle.width() / 2, leftPoint.y + rectangle.height() / 2);
    }

    @Override
    public void onClick(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        if(rectangle.contains(x, y)) {
            playerPoint.set(playerPoint.x - 10, playerPoint.y);
        }
    }
}
