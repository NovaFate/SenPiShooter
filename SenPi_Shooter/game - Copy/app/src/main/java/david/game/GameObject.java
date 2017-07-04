package david.game;

import android.graphics.Canvas;

/**
 * Created by David on 5/15/2017.
 */

public interface GameObject {
    public void draw(Canvas canvas);
    public void update();
}
