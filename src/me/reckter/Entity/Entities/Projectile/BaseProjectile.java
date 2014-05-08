package me.reckter.Entity.Entities.Projectile;

import me.reckter.Entity.Category.CollisionGroup;
import me.reckter.Entity.Component.*;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Exception.ComponentNotFoundException;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashMap;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseProjectile extends BaseEntity {


	public BaseProjectile(BaseLevel level) {
		super(level);
	}

	public BaseProjectile(BaseLevel level, BaseEntity origin) {
		this(level);
	}

	public BaseProjectile(BaseLevel level, BaseEntity origin, Vector2f coord, Vector2f movement, float speed) {
		this(level);
		init();

		try {
			Movement movementComp = getComponent(Movement.class);
			movementComp.movement = movement;
			movementComp.movement = origin.getComponent(Movement.class).movement.copy().add(movement.normalise().scale(speed));
		} catch(ComponentNotFoundException e) {}

		//getComponent(Position.class).coords = coord;
		getComponent(Projectile.class).origin = origin;
	}


	@Override
	public void init() {

		isAlive = true;
		components = new HashMap<Class<? extends BaseComponent>, BaseComponent>();

		//addComponent(new Position());
		addComponent(new Movement());
		addComponent(new Collision(CollisionGroup.H));
		addComponent(new Projectile(10));
		addComponent(new TimeToLife(3000));
		addComponent(new DestroyedOnContact());

	}

	@Override
	public void render(Graphics g) {
		Position position = getComponent(Position.class);
		g.setColor(Color.white);
		//g.fill(new Circle(position.coords.x, position.coords.y, position.size.y));
		super.render(g);
	}
}
