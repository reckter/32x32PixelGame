package me.reckter.Level.Menu;

import me.reckter.Entity.Component.Position;
import me.reckter.Entity.Entities.BaseEntity;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Exit extends BaseMenu {

	BaseEntity explosion;
	int timeToClose;

	@Override
	public void init() {
		super.init();
		explosion.init();
		Position position = explosion.getComponent(Position.class);
		position.size = new Vector2f(2, 2);
		add(explosion);
	}

	/**
	 * gets called every logic tick
	 *
	 * @param delta the time in ms since the last logic tick
	 */
	@Override
	public void logic(int delta) {
		super.logic(delta);
		timeToClose -= delta;
		if(timeToClose < 0) {
			System.exit(0);
		}
	}
}
