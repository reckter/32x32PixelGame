package me.reckter.Entity.Entities.Enemy;

import me.reckter.Entity.Category.CollisionGroup;
import me.reckter.Entity.Component.Collision;
import me.reckter.Entity.Component.Enemy;
import me.reckter.Entity.Component.Position;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Interface.LifeBar;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseEnemy extends BaseEntity {


	public BaseEnemy(BaseLevel level) {
		super(level);
	}


	@Override
	public void init() {
		super.init();
		addComponent(new Enemy());
		getComponent(Position.class).size = new Vector2f(13,13);
		Collision collision = getComponent(Collision.class);
		collision.collisionGroups.clear();
		collision.collisionGroups.add(CollisionGroup.B);

		level.add(new LifeBar(level, this));
	}
}
