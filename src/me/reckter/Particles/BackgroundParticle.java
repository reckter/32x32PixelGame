package me.reckter.Particles;

import me.reckter.Engine;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by reckter on 1/1/14.
 */
public class BackgroundParticle extends BaseParticle { //TODO us an Image for this, this is slow as fuck!


	public Vector2f offset;
	public float offsetFactor;

	public int size;
	public BackgroundParticle(BaseLevel level, float x, float y) {
		super(level, x, y, new Vector2f(0, 0));
	}

	@Override
	public void init() {
		super.init();

		offset = new Vector2f(x ,y);
		offsetFactor = 1;
		size = 0;
		canBeRemovedForSpeed = false;
		diesAfterTime = false;
	}

	@Override
	public void logic(int delta) {
		super.logic(delta);

		float tmpX = (level.getCamX() * offsetFactor + offset.x) % (Engine.SCREEN_WIDTH * 1.1f);
		float tmpY = (level.getCamY() * offsetFactor + offset.y) % (Engine.SCREEN_HEIGHT * 1.1f);
		if(tmpX < 0) {
			tmpX += Engine.SCREEN_WIDTH * 1.1f;
		}

		if(tmpY < 0) {
			tmpY += Engine.SCREEN_HEIGHT * 1.1f;
		}

		x = -level.getCamX() + tmpX;
		y = -level.getCamY() + tmpY;
	}

	@Override
	public void render(Graphics g) {
		if(size == 0) {
			super.render(g);
		} else {
			g.fill(new Rectangle(x - size / 2, y - size / 2,size, size));
		}
	}
}
