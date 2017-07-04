package david.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class BaddieProjectileManager {
    private ArrayList<BaddieProjectiles> baddieProjectiles;
    private int baddyProjectileHeight;

    private int counter = 0;

    private RectPlayer player;
    private RectBaddy baddie;

    public BaddieProjectileManager(int projectileHeight, RectPlayer player, RectBaddy baddy){
        this.baddyProjectileHeight = projectileHeight;
        this.baddie = baddy;

        this.player = player;

        baddieProjectiles = new ArrayList<>();

        populateBaddieProjectiles();
    }

    public boolean baddieShotCollide(RectPlayer player) {
        for(BaddieProjectiles bad : baddieProjectiles) {
            if(bad.playerCollide(player)) {
                baddieProjectiles.remove(baddieProjectiles.indexOf(bad));
                player.setPlayerHealth(player.getPlayerHealth() - 5);
                return true;
            }
        }
        return false;
    }

    private void populateBaddieProjectiles() {
        int currY = -5 * Constants.SCREEN_HEIGHT / 4;
        while(currY < 0) {
//            baddieProjectiles.add(new BaddieProjectiles(baddie.getRectangle().left + 75, baddie.getRectangle().top  + 400, baddie.getRectangle().right - 50, baddie.getRectangle().bottom - 50));
//            baddieProjectiles.add(new BaddieProjectiles(baddie.getRectangle().left, baddie.getRectangle().top + 300, baddie.getRectangle().right, baddie.getRectangle().bottom + 150));
            baddieProjectiles.add(new BaddieProjectiles(baddie.getRectangle().left, baddie.getRectangle().top + 300, baddie.getRectangle().right, baddie.getRectangle().bottom + 150));
            currY += baddyProjectileHeight;
        }
    }

    public void update(){
        for(BaddieProjectiles bad : baddieProjectiles) {
            bad.incrementY(40);
        }
        counter ++;
        if (counter % 25 == 0) {
            populateBaddieProjectiles();
        }
        if (baddieProjectiles.size() > 5) {
            baddieProjectiles.remove(0);
        }
    }

    public void draw(Canvas canvas){
        for(BaddieProjectiles bad : baddieProjectiles) {
            bad.draw(canvas);
        }
        Paint paint = new Paint();
        paint.setTextSize(75);
        paint.setColor(Color.GREEN);
        canvas.drawText("Player Health: " + player.getPlayerHealth(), 50, Constants.SCREEN_HEIGHT - 100, paint);
    }
}