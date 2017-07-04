package david.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by David on 5/15/2017.
 */

public class RectPlayer implements GameObject {

    Bitmap idlee;
    private Rect rectangle;
    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;
    private Animation walkUp;
    private Animation walkDown;
    private int playerHealth = 50;
    private AnimationManager animManager;


    public RectPlayer(Rect rectangle) {
        this.rectangle = rectangle;

        BitmapFactory bf = new BitmapFactory();
        Bitmap idleImg;
        Bitmap frontstill;
        Bitmap frontwalk1;
        Bitmap frontwalk2;
        Bitmap leftstill;
        Bitmap leftwalk1;
        Bitmap leftwalk2;
        Bitmap rightstill;
        Bitmap rightwalk1;
        Bitmap rightwalk2;
        Bitmap backstill;
        Bitmap backwalk1;
        Bitmap backwalk2;

        if (Constants.IS_GRILL) {
            idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.back_still);
            frontstill = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.front_still);
            frontwalk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.front_walk1);
            frontwalk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.front_walk2);
            leftstill = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.left_still);
            leftwalk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.left_walk1);
            leftwalk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.left_walk2);
            rightstill = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.right_still);
            rightwalk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.right_walk1);
            rightwalk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.right_walk2);
            backstill = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.back_still);
            backwalk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.back_walk1);
            backwalk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.back_walk2);
        } else  {
            idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mback_still);
            frontstill = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mfront_still);
            frontwalk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mfront_walk1);
            frontwalk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mfront_walk2);
            leftstill = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mleft_still);
            leftwalk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mleft_walk1);
            leftwalk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mleft_walk2);
            rightstill = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mright_still);
            rightwalk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mright_walk1);
            rightwalk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mright_walk2);
            backstill = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mback_still);
            backwalk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mback_walk1);
            backwalk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.mback_walk2);
        }

        idle = new Animation (new Bitmap[]{idleImg}, 2);
        walkRight = new Animation (new Bitmap[]{rightwalk1, rightstill, rightwalk2, rightstill}, 0.5f);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        idlee = Bitmap.createBitmap(idleImg, 0, 0, idleImg.getWidth(), idleImg.getHeight(), m, false);
//        walk2 = Bitmap.createBitmap(rightwalk2, 0, 0, rightwalk2.getWidth(), rightwalk2.getHeight(), m, false);

        walkRight = new Animation (new Bitmap[]{rightwalk1, rightstill, rightwalk2, rightstill}, 0.5f);
        walkLeft = new Animation (new Bitmap[]{leftwalk1, leftstill, leftwalk2, leftstill}, 0.5f);
        walkDown = new Animation (new Bitmap[]{frontwalk1, frontstill, frontwalk2, frontstill}, 0.5f);
        walkUp = new Animation (new Bitmap[]{backwalk1, backstill, backwalk2, backstill}, 0.5f);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft, walkUp, walkDown});
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int health) {
        playerHealth = health;
    }

    @Override
    public void draw (Canvas canvas) {
        animManager.draw(canvas, rectangle);
    }

    @Override
    public void update() {
        animManager.update();
    }

    public void update(Point point) {
        float oldLeft = rectangle.left;
        float oldTop = rectangle.top;
        //1,t,r,b
      rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
//      rectangle.set(point.x - rectangle.width() / 2, rectangle.height() - 3*Constants.SCREEN_HEIGHT/4, point.x + rectangle.width() / 2, rectangle.height() + 3*Constants.SCREEN_HEIGHT/4);

        int state = 0;

        if(SceneManager.ACTIVE_SCENE == 1){
            rectangle.set(point.x - rectangle.width() / 2, rectangle.height() - 3*Constants.SCREEN_HEIGHT/4, point.x + rectangle.width() / 2, rectangle.height() + 3*Constants.SCREEN_HEIGHT/4);

        }
        if(rectangle.left - oldLeft > 5) {
            state = 1;
        } else if (rectangle.left - oldLeft < -5) {
            state = 2;
        } else if (rectangle.top - oldTop < -5){
            state = 3;
        } else if(rectangle.top - oldTop > 5){
            state = 4;
        }

        animManager.playAnim(state);
        animManager.update();
    }

}