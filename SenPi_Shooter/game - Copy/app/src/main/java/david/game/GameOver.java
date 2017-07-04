package david.game;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GameOver extends Activity implements Scene{
    private BitmapFactory bf = new BitmapFactory();
    private Bitmap girl = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.game_over_girl);
    private Bitmap boy = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.game_over_boy);

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        if(Constants.IS_GRILL) {
            canvas.drawBitmap(girl, null, new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), new Paint());
        } else{
            canvas.drawBitmap(boy, null, new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), new Paint());
        }
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 4;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                terminate();
                break;
        }
    }
}