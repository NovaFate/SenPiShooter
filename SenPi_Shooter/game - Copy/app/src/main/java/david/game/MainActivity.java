package david.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_main);
        Constants.IS_GRILL = false;

    }
    public void clickToCharacter(View v) {
        setContentView(R.layout.activity_other);
    }

    public void onClick1(View v){
        Constants.IS_GRILL = true;
        setContentView(new GamePanel(this));
    }

    public void onClick2(View v){
        Constants.IS_GRILL = false;
        setContentView(new GamePanel(this));
    }
}