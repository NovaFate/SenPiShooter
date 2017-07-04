package david.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by David on 6/5/2017.
 */

public class BaddieProjectiles implements GameObject{
    private Rect rectangle;
    private BitmapFactory bf = new BitmapFactory();
    private Bitmap e = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.e);
    private Bitmap rock = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rock);
    private Bitmap chosen;


    public Rect getRectangle() {
        return rectangle;
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
    }

    public BaddieProjectiles(int xCord, int yCord, int xSide, int ySide) {
        int random = (int) (Math.random() * 2);
        if (random == 0) {
            chosen = e;
        } else {
            chosen = rock;
        }
        rectangle = new Rect(xCord, yCord, xSide, ySide);
    }

    public boolean playerCollide(RectPlayer player) {
        return Rect.intersects(rectangle, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(chosen, null, rectangle , null);
//        Paint paint  = new Paint(Color.BLACK);
//        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }
}