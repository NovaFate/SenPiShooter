package david.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by David on 6/2/2017.
 */


public class Projectile implements GameObject {
    private Rect rectangle;

    private BitmapFactory bf = new BitmapFactory();

    private Bitmap rasen = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rasengan);
    private Bitmap pooh = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.winnie);

    private Bitmap chosen;

    public Projectile(int xCord, int yCord, int xSide, int ySide) {
        int random = (int) (Math.random() * 2);
        if (random == 0) {
            chosen = pooh;
        } else {
            chosen = rasen;
        }
        rectangle = new Rect(xCord, yCord, xSide, ySide);
    }

    @Override
    public void draw(Canvas canvas) {
//        canvas.drawRect(rectangle, new Paint());
        canvas.drawBitmap(chosen, null, rectangle, new Paint());
    }

    @Override
    public void update() {

    }

    public boolean playerCollide(RectBaddy baddy) {
        Rect newHitBox = new Rect(rectangle.left + 50, rectangle.top + 100, rectangle.right - 25, rectangle.bottom - 50);
        return Rect.intersects(newHitBox, baddy.getRectangle());
    }

    public void decrementY(float y) {
        rectangle.top -= y;
        rectangle.bottom -= y;
    }
}