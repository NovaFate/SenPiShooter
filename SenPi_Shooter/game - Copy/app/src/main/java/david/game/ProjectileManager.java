package david.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;


/**
 * Created by David on 6/2/2017.
 */

public class ProjectileManager {
    private ArrayList<Projectile> projectiles;
    private int projectileHeight;

    private int counter = 0;

    private RectPlayer player;
    private RectBaddy baddy;
    private BaddieProjectileManager baddieProjectileManager;


    public ProjectileManager(int projectileHeight, RectPlayer player, RectBaddy baddy){
        this.projectileHeight = projectileHeight;
        this.baddy = baddy;

        this.player = player;

        projectiles = new ArrayList<>();

        populateProjectiles();
        baddieProjectileManager = new BaddieProjectileManager(1000000000, player, baddy);
    }

    public boolean playerCollide(RectBaddy baddy) {
        for(Projectile pro: projectiles) {
            if(pro.playerCollide(baddy)) {
                projectiles.remove(projectiles.indexOf(pro));
                baddy.setBaddieHealth(baddy.getBaddieHealth() - 5);
                return true;
            }
        }
        return false;
    }

    private void populateProjectiles() {
        int currY = -5 * Constants.SCREEN_HEIGHT / 4;
        while(currY < -2000) {
//            projectiles.add(new Projectile(player.getRectangle().left, ))
//            projectiles.add(new Projectile(player.getRectangle().left + 50, player.getRectangle().top - 150, player.getRectangle().right - 100, player.getRectangle().bottom - 350));
            if (Constants.IS_GRILL) {
                //1,t,r,b
projectiles.add(new Projectile(player.getRectangle().left + 50, player.getRectangle().top + 2350, player.getRectangle().right - 100, player.getRectangle().bottom - 300));
//                projectiles.add(new Projectile(player.getRectangle().left + 50, player.getRectangle().top + 3000, player.getRectangle().right - 100, player.getRectangle().bottom - 3000));
//                projectiles.add(new Projectile(player.getRectangle().left + 50, player.getRectangle().top + Constants.SCREEN_HEIGHT/1000000000, player.getRectangle().right - 100, player.getRectangle().bottom - Constants.SCREEN_HEIGHT/1000000000));
            } else {
                projectiles.add(new Projectile(player.getRectangle().left + 50, player.getRectangle().top + 2350, player.getRectangle().right, player.getRectangle().bottom - 300));
            }
            currY += projectileHeight;
        }
    }

    public void update(){
        for(Projectile pro : projectiles){
            pro.decrementY(40);
        }
        counter++;
        if (counter % 10 == 0) {
            populateProjectiles();
        }
        if (projectiles.size() > 5) {
            projectiles.remove(0);
        }
        baddieProjectileManager.update();
        baddieProjectileManager.baddieShotCollide(player);
    }

    public void draw(Canvas canvas){
        for(Projectile pro: projectiles){
            pro.draw(canvas);
        }
        baddieProjectileManager.draw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(75);
        paint.setColor(Color.RED);
        canvas.drawText("Baddie Health: " + baddy.getBaddieHealth(), 50, 50 + paint.descent() - paint.ascent(), paint);
//        canvas.drawText("baddy left: " + baddy.getRectangle().left, 50, 650 + paint.descent() - paint.ascent(), paint);
//        canvas.drawText("baddy top: " + baddy.getRectangle().top, 50, 750 + paint.descent() - paint.ascent(), paint);
//        canvas.drawText("baddy right: " + baddy.getRectangle().right, 50, 850 + paint.descent() - paint.ascent(), paint);
//        canvas.drawText("baddy bottom: " + baddy.getRectangle().bottom, 50, 950 + paint.descent() - paint.ascent(), paint);
//
//        canvas.drawText("p left: " + player.getRectangle().left, 50, 1050 + paint.descent() - paint.ascent(), paint);
//        canvas.drawText("p top: " + player.getRectangle().top, 50, 1150 + paint.descent() - paint.ascent(), paint);
//        canvas.drawText("p right: " + player.getRectangle().right, 50, 1250 + paint.descent() - paint.ascent(), paint);
//        canvas.drawText("p bottom: " + player.getRectangle().bottom, 50, 1350 + paint.descent() - paint.ascent(), paint);
    }
}