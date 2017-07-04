package david.game;

/**
 * Created by frk_ktho on 6/2/2017.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import static david.game.R.drawable.baddie;

/**
 * Created by David on 5/15/2017.
 */

public class RectBaddy implements GameObject {

    private Rect rectangle;
    private Bitmap idleImg;
    private int baddieHealth = 50;

    public Rect getRectangle() {
        return rectangle;
    }
    public RectBaddy(Rect rectangle) {
        this.rectangle = rectangle;

        BitmapFactory bf = new BitmapFactory();
        idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), baddie);


    }

    public int getBaddieHealth() {
        return baddieHealth;
    }

    public void setBaddieHealth(int x) {
        baddieHealth = x;
    }

    @Override
    public void draw (Canvas canvas) {
        canvas.drawBitmap(idleImg, null, rectangle, new Paint());
    }

    @Override
    public void update() {

    }

    public void update(Point point) {
        //1,t,r,b
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
    }

}