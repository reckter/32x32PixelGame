package me.reckter.Interface;

import me.reckter.Entity.Component.Life;
import me.reckter.Entity.Component.Position;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by mediacenter on 30.12.13.
 */
public class LifeBar extends BaseInterface {


	double healthDisplayed;

	protected BaseEntity on;
	Position position;
	Life life;

	public LifeBar(BaseLevel level, BaseEntity on) {
		super(level);
		this.on = on;

		height = 3;
		position = on.getComponent(Position.class);
		life = on.getComponent(Life.class);
		width = (int) (position.size.y * 0.8);
	}


	@Override
	public boolean isAlive() {
		return on.isAlive;
	}


	@Override
	public void logic(int delta) {

		width = (int) (position.size.length() * 1.8f);

		x = (int) (position.tile.x - width / 2);
		y = (int) (position.tile.y - (position.size.y + 3));
		healthDisplayed += (life.lifePercentage - healthDisplayed) / (300 / delta);

	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.white);
		g.fill(new Rectangle(x, y, width, height));

		g.setColor(Color.red);
		g.draw(new Rectangle(x, y, (float) (width * healthDisplayed), height));
		g.fill(new Rectangle(x, y, (float) (life.lifePercentage * (double) width), height));
	}
}
