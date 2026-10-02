// Cannonball.java
// Represents the Cannonball that the Cannon fires
package com.alvaro.cannongame;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Cannonball extends GameElement {
    private float velocityX;
    private boolean onScreen;

    // constructor
    public Cannonball(CannonView view, int color, int soundId, int x,
       int y, int radius, float velocityX, float velocityY) {
        super(view, color, soundId, x, y, 2 * radius, 2 * radius, velocityY);
        this.velocityX = velocityX;
        onScreen = true;
    }

    // get Cannonball's radius
    int getRadius() {
        return (shape.right - shape.left) / 2;
    }

    // test whether Cannonball collides with the given GameElement
    public boolean collidesWith(GameElement element) {
        return (Rect.intersects(shape, element.shape) && velocityX > 0);
    }

    // returns true if this Cannonball is on the screen
    public boolean isOnScreen() {
        return onScreen;
    }

    // reverses the Cannonball's horizontal velocity
    public void reverseVelocityX() {
        velocityX *= -1;
    }

    // returns the center of the Cannonball (used to spawn hit particles)
    int getCenterX() {
        return shape.centerX();
    }

    int getCenterY() {
        return shape.centerY();
    }

    // updates the Cannonball's position
    @Override
    public void update(double interval) {
        super.update(interval); // updates Cannonball's vertical position

        // update horizontal position
        shape.offset((int) (velocityX * interval), 0);

        // if Cannonball goes off the screen: only remove it after it has
        // completely left the display, otherwise it would be discarded on the
        // first frame, right after being spawned at x = -radius (visible on
        // high refresh rate displays, where the per-frame displacement is
        // smaller than the Cannonball's radius)
        if (shape.right < 0 || shape.left > view.getScreenWidth() ||
           shape.bottom < 0 || shape.top > view.getScreenHeight())
           onScreen = false; // set it to be removed
    }

    // draws the Cannonball on the given canvas
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(shape.left + getRadius(),
           shape.top + getRadius(), getRadius(), paint);
    }
}
