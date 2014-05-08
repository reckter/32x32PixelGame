package me.reckter.Entity.Entities.Enemy.Menu;

import me.reckter.Entity.Component.Menu;
import me.reckter.Entity.Component.Position;
import me.reckter.Entity.Entities.Enemy.BaseEnemy;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseMenueItem extends BaseEnemy {
	public BaseMenueItem(BaseLevel level) {
		super(level);
	}

	public BaseMenueItem(BaseLevel level, String name, Vector2f coords) {
		super(level);
		init();
		getComponent(Menu.class).name = name;
		Position position = getComponent(Position.class);
		//position.coords = coords;
		position.size = new Vector2f(130,20);
	}

	@Override
	public void init() {
		super.init();
		addComponent(new Menu());
	}

	/**
	 * gets called every render frame, when the entity needs to be rendered
	 *
	 * @param g the graphic that should be rendered on
	 */
	@Override
	public void render(Graphics g) {
		Position position = getComponent(Position.class);
		Menu menu = getComponent(Menu.class);

		if(menu.isHighlighted) {
			g.setColor(Color.white);
			g.fill(position.getHitBox());
			g.setColor(Color.black);
			//g.drawString(menu.name, position.coords.x - menu.name.length() * 2.5f, position.coords.y - 5);
		} else {
			g.setColor(Color.white);
			g.draw(position.getHitBox());
			//g.drawString(menu.name, position.coords.x - menu.name.length() * 2.5f, position.coords.y - 5);
		}
	}
}
