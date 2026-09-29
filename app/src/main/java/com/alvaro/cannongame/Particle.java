// Particle.java
// A single spark of the explosion shown when a Target is destroyed
// (extra feature: frame-by-frame particle animation)
package com.alvaro.cannongame;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Particle {
    private static final float LIFE_SECONDS = 0.6f; // how long a spark lives
    private static final float SPEED = 220f; // initial radial speed, px/second
    private static final float DRAG = 1.8f; // velocity decay per second

    private final float[] direction; // unit vector of the radial movement
    private final int color; // spark color
    private float x, y; // current position
    private float velocityX, velocityY; // current velocity
    private float lifeLeft = LIFE_SECONDS; // remaining life in seconds
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Particle(float x, float y, float angle, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        direction = new float[]{(float) Math.cos(angle), (float) Math.sin(angle)};
        velocityX = SPEED * direction[0];
        velocityY = SPEED * direction[1];
        paint.setColor(color);
    }

    // advances the particle and returns false when it must be removed
    boolean update(double interval) {
        float seconds = (float) interval;
        x += velocityX * seconds;
        y += velocityY * seconds;
        velocityX -= velocityX * DRAG * seconds; // friction
        velocityY -= velocityY * DRAG * seconds;
        lifeLeft -= seconds;
        return lifeLeft > 0;
    }

    // draws the particle with a fade-out from its color to transparent
    void draw(Canvas canvas) {
        int alpha = (int) (255 * (lifeLeft / LIFE_SECONDS));
        paint.setAlpha(Math.max(0, Math.min(255, alpha)));
        canvas.drawCircle(x, y, Math.max(1f, 8f * lifeLeft / LIFE_SECONDS),
           paint);
    }
}
