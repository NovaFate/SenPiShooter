package david.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by David on 5/16/2017.
 */

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
