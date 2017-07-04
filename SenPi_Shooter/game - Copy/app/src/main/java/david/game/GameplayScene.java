package david.game;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import static android.R.attr.left;
import static android.R.attr.right;

/**
 * Created by David on 5/16/2017.
 */

public class GameplayScene implements Scene{
    private Rect r = new Rect();
    protected RectPlayer player;
    protected Point playerPoint;

    private RectBaddy baddy;
    private Point baddyPoint;

    BitmapFactory bf = new BitmapFactory();
    Bitmap background = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gameplaybackground);
    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private long gameOverTime;
    private MainThread thread;

//    private Point upPoint = new Point(Constants.SCREEN_WIDTH / 5, 3 * Constants.SCREEN_HEIGHT / 4);
//    private Point downPoint = new Point(Constants.SCREEN_WIDTH / 5, 5 * Constants.SCREEN_HEIGHT / 6);
//    private Point leftPoint = new Point(Constants.SCREEN_WIDTH / 10, 79 * Constants.SCREEN_HEIGHT / 100);
//    private Point rightPoint = new Point(Constants.SCREEN_WIDTH / 10, 79 * Constants.SCREEN_HEIGHT / 100);

//    Bitmap upPic = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.up_arrow);
//    Bitmap downPic = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.down_arrow);
//    Bitmap leftPic = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.left_arrow);
//    Bitmap rightPic = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.right_arrow);

//    private Button up = new Button(upPic, upPoint);
//    private Button down = new Button(downPic, downPoint);
//    private Button left = new Button(leftPic, leftPoint);
//    private Button right = new Button(rightPic, rightPoint);

    private MovementManager movementManager = new MovementManager(playerPoint);


    public GameplayScene() {
        if(Constants.IS_GRILL) {
            player = new RectPlayer(new Rect(300, 300, 600, 600));
        }else{
            player = new RectPlayer(new Rect(200,200,400,400));
        }
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);

        baddy = new RectBaddy(new Rect(400, 400, 800, 800));
        baddyPoint = new Point(Constants.SCREEN_WIDTH / 2, 1 * Constants.SCREEN_HEIGHT / 4);


    }
    public void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);

        baddyPoint = new Point(Constants.SCREEN_WIDTH / 2, 1 * Constants.SCREEN_HEIGHT / 4);
        baddy.update(baddyPoint);

        movingPlayer = false;
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 1;

    }

    @Override
    public void receiveTouch(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if(movementManager.getUpButtonRect().contains(x, y)) {
            playerPoint.set(playerPoint.x, playerPoint.y - 10);
        }
        if(movementManager.getDownButtonRect().contains(x, y)) {
            playerPoint.set(playerPoint.x, playerPoint.y + 10);
        }
        if(movementManager.getLeftButtonRect().contains(x, y)) {
            playerPoint.set(playerPoint.x - 10, playerPoint.y);
        }
        if(movementManager.getRightButtonRect().contains(x, y)) {
            playerPoint.set(playerPoint.x + 10, playerPoint.y);
        }
//        movementManager.movementOnClick(event);
//        if(up.getRectangle().contains((int) event.getX(), (int) event.getY())) {
//            Point temp = up.onClick(event, playerPoint.x, playerPoint.y - 10);
//            playerPoint.set(temp.x, temp.y);
//        }
//        Point temp;
////        up.onClick(event, playerPoint.x, playerPoint.y + 10);
////            if (playerPoint.x != Constants.SCREEN_WIDTH && playerPoint.y != Constants.SCREEN_HEIGHT && playerPoint.x > 0 && playerPoint.y > 0) {

//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        if (!gameOver && up.getRectangle().contains((int) event.getX(), (int) event.getY())) {
//                            movingPlayer = true;
//                        }
//                        if (!gameOver && player.getRectangle().contains((int) event.getX(), (int) event.getY())) {
//                            movingPlayer = true;
//                        }
//                        if (gameOver && System.currentTimeMillis() - gameOverTime >= 2000) {
//                            reset();
//                            gameOver = false;
//                        }
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if (!gameOver && movingPlayer) {
//                            playerPoint.set((int) event.getX(), (int) event.getY());
//                        }
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        movingPlayer = false;
//                        break;
//                }

    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        canvas.drawBitmap(background, null, new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), paint);

        paint.setTextSize(75);
        paint.setColor(Color.BLUE);
        canvas.drawText("Drag to move",Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT/10, paint);

        player.draw(canvas);
        baddy.draw(canvas);

        movementManager.draw(canvas);

//        movementManager.getUpButton().draw(canvas);
//        movementManager.getDownButton().draw(canvas);
//        movementManager.getLeftButton().draw(canvas);
//        movementManager.getRightButton().draw(canvas);

//        movementManager.draw(canvas);
    }

    @Override
    public void update() {
        if (!gameOver) {
            player.update(playerPoint);
            baddy.update(baddyPoint);

            movementManager.update();

            if(Rect.intersects(baddy.getRectangle(), player.getRectangle())) {
                terminate();
            }
        }
    }

}