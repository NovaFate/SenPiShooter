package david.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by David on 6/28/2017.
 */

public class UpButton implements Button{
    Bitmap upPic = Constants.BF.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.up_arrow);
    private Rect rectangle = new Rect(Constants.SCREEN_WIDTH / 5, Constants.SCREEN_WIDTH / 5, Constants.SCREEN_HEIGHT / 5, Constants.SCREEN_HEIGHT / 5);
//    private Rect rectangle = new Rect( 300, 300, 600, 600);
    private Point playerPoint;
    private Point upPoint = new Point(Constants.SCREEN_WIDTH / 5, 7 * Constants.SCREEN_HEIGHT / 10);

    public UpButton(Point playerPoint) {
        this.playerPoint = playerPoint;

    }

    @Override
    public Rect getRectangle() {
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(upPic, null, rectangle, new Paint());
    }

    @Override
    public void update() {
        rectangle.set(upPoint.x - rectangle.width() / 2, upPoint.y - rectangle.height() / 2, upPoint.x + rectangle.width() / 2, upPoint.y + rectangle.height() / 2);
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
