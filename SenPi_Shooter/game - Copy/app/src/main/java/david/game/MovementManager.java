package david.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import static android.R.attr.left;
import static android.R.attr.right;

/**
 * Created by David on 6/23/2017.
 */

public class MovementManager {

    private Point playerPoint;

    private UpButton up = new UpButton(playerPoint);
    private DownButton down = new DownButton(playerPoint);
    private LeftButton left = new LeftButton(playerPoint);
    private RightButton right = new RightButton(playerPoint);

    public MovementManager(Point playerPoint) {
        this.playerPoint = playerPoint;
    }

    public Rect getUpButtonRect() {
        return up.getRectangle();
    }

    public Rect getDownButtonRect() {
        return down.getRectangle();
    }

    public Rect getLeftButtonRect() {
        return left.getRectangle();
    }

    public Rect getRightButtonRect() {
        return right.getRectangle();
    }

//    public Button getUpButton() {
//        return up;
//    }
//    public Button getDownButton() {
//        return down;
//    }
//    public Button getLeftButton() {
//        return left;
//    }
//    public Button getRightButton() {
//        return right;
//    }
//
    public void movementOnClick(MotionEvent event) {
        up.onClick(event);
        down.onClick(event);
        right.onClick(event);
        left.onClick(event);
//        up.onClick(event, playerPoint.x, playerPoint.y - 10);
//        down.onClick(event, playerPoint.x, playerPoint.y + 10);
//        left.onClick(event, playerPoint.x - 10, playerPoint.y);
//        right.onClick(event, playerPoint.x + 10, playerPoint.y);

//        if(up.getRectangle().contains((int) event.getX(), (int) event.getY())) {
//            Point temp = up.onClick(event, playerPoint.x, playerPoint.y - 10);
//            playerPoint.set(temp.x, temp.y);
//        }
//        if(down.getRectangle().contains((int) event.getX(), (int) event.getY())) {
//            Point temp = up.onClick(event, playerPoint.x, playerPoint.y - 10);
//            playerPoint.set(temp.x, temp.y);
//        }
//        if(left.getRectangle().contains((int) event.getX(), (int) event.getY())) {
//            Point temp = up.onClick(event, playerPoint.x, playerPoint.y - 10);
//            playerPoint.set(temp.x, temp.y);
//        }
//        if(right.getRectangle().contains((int) event.getX(), (int) event.getY())) {
//            Point temp = up.onClick(event, playerPoint.x, playerPoint.y - 10);
//            playerPoint.set(temp.x, temp.y);
//        }
    }

    public void draw(Canvas canvas) {
        up.draw(canvas);
        down.draw(canvas);
        left.draw(canvas);
        right.draw(canvas);
    }

    public void update() {
        up.update();
        down.update();
        left.update();
        right.update();

    }

}
