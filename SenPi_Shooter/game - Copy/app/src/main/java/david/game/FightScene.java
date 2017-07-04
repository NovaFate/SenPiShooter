package david.game;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by David on 5/23/2017.
 */

public class FightScene extends Activity implements Scene{
    private Rect r = new Rect();
    private RectPlayer player;
    private Point playerPoint;

    private RectBaddy baddy;
    private Point baddyPoint;

    private ProjectileManager projectileManager;
//    private BaddieProjectileManager baddieProjectileManager;


    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private boolean win = false;

    private boolean wentLeft = false;
    private boolean wentRight = false;

    private boolean bgInitted = false;

    private BitmapFactory bf = new BitmapFactory();
    private Bitmap bg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.fightscenebackground_resized);

    private int counter = 0;
    private int random = 0;


    public FightScene() {
        if(Constants.IS_GRILL){
            player = new RectPlayer(new Rect(500, 500, 800, 800));
        }else {
            player = new RectPlayer(new Rect(200, 200, 400, 400));
        }
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);

        baddy = new RectBaddy(new Rect(300, 300, 600, 600));
        baddyPoint = new Point(Constants.SCREEN_WIDTH / 2, 1 * Constants.SCREEN_HEIGHT / 5);


        projectileManager = new ProjectileManager(1000000000, player, baddy);
//        baddieProjectileManager = new BaddieProjectileManager(1000000000, player, baddy);

    }

    public void update() {
        counter ++;
        if (counter % 20 == 0) {
            random = (int) (Math.random() * Constants.SCREEN_WIDTH);
        }
        if (baddyPoint.x - 10 < random && baddyPoint.x + 10 > random) {
        } else if (baddyPoint.x < random) {
            if (baddyPoint.x - 10 > random) {
                random -= 5;
            }
            baddyPoint.set(baddyPoint.x + 10, baddyPoint.y);
        } else {
            if (baddyPoint.x + 10 < random) {
                random += 5;
            }
            baddyPoint.set(baddyPoint.x - 10, baddyPoint.y);
        }

        if (baddy.getBaddieHealth() == 0) {
            win = true;
        }
        if (player.getPlayerHealth() == 0) {
            gameOver = true;
        }

        if (!gameOver && !win) {
            baddy.update(baddyPoint);
            player.update(playerPoint);

//            baddieProjectileManager.update();
//            baddieProjectileManager.baddieShotCollide(player);
            projectileManager.update();
            projectileManager.playerCollide(baddy);
        }
    }

    public void reset() {
        player = new RectPlayer(new Rect(400, 400, 700, 700));
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);

        baddy = new RectBaddy(new Rect(100, 100, 400, 400));
        baddyPoint = new Point(Constants.SCREEN_WIDTH / 2, 1 * Constants.SCREEN_HEIGHT / 5);

        projectileManager = new ProjectileManager(1000000000, player, baddy);
//        baddieProjectileManager = new BaddieProjectileManager(1000000000, player, baddy);
    }


    public void terminate() {
        SceneManager.ACTIVE_SCENE = 2;

    }


    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!gameOver && player.getRectangle().contains((int) event.getX(), (int) event.getY())) {
                    movingPlayer = true;
                }
                if(gameOver && System.currentTimeMillis() >= 2000) {
                    reset();
                    gameOver = false;
                }
                if (win && System.currentTimeMillis() >= 2000) {
                    reset();
                    win = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!gameOver && movingPlayer) {
                    playerPoint.set((int) event.getX(), (int) event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;

        }
    }


    public void draw(Canvas canvas) {
        if(!gameOver && !win) {
            canvas.drawBitmap(bg, null, new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), new Paint());
//            baddieProjectileManager.draw(canvas);
            projectileManager.draw(canvas);
        } else if (gameOver) {
            terminate();
        } else if (win) {
            SceneManager.ACTIVE_SCENE = 3;
        }

        player.draw(canvas);


        baddy.draw(canvas);

    }

    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }
}