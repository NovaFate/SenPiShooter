package david.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by David on 5/16/2017.
 */

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager() {
        ACTIVE_SCENE = 0;
        scenes.add(new GameplayScene());
        scenes.add(new FightScene());
        scenes.add(new GameOver());
        scenes.add(new Win());
        scenes.add(new Credits());
        scenes.add(new WetWaters());
    }

    public void receiveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw (Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }

    public void setScene(int scene) {
        ACTIVE_SCENE = scene;
    }

    public int getActiveScene(){
        return ACTIVE_SCENE;
    }
}
