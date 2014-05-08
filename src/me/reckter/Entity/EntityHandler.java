package me.reckter.Entity;

import me.reckter.Engine;
import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Component.Position;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.BaseEvent;
import me.reckter.Entity.Events.EntityAddEvent;
import me.reckter.Entity.Events.EntityDelEvent;
import me.reckter.Entity.Logic.BaseLogic;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class EntityHandler {
	public ArrayList<BaseEntity> entities;
	public LogicHandler logicHandler;
	ArrayList<BaseEntity> entitiesToRemove;
	ArrayList<BaseEntity> entitiesToAdd;
	BaseLevel level;

	public EntityHandler(BaseLevel level) {
		entities = new ArrayList<BaseEntity>();
		entitiesToRemove = new ArrayList<BaseEntity>();
		entitiesToAdd = new ArrayList<BaseEntity>();
		logicHandler = new LogicHandler(this);
		this.level = level;
	}

	public ArrayList<BaseEntity> getEntitiesWichMatch(Matching matching) {
		ArrayList<BaseEntity> ret = new ArrayList<BaseEntity>();
		for (BaseEntity entity : entities) {
			if (matching.resolve(entity.components.values())) {
				ret.add(entity);
			}
		}
		return ret;
	}

	public void updateEntityList() {
		entities.addAll(entitiesToAdd);
		for (BaseEntity entity : entitiesToAdd) {
			logicHandler.fireEvent(new EntityAddEvent(entity));
		}

		for (BaseEntity entity : entitiesToRemove) {
			logicHandler.fireEvent(new EntityDelEvent(entity));
		}

		entities.removeAll(entitiesToRemove);
		if (!entitiesToRemove.isEmpty() || !entitiesToAdd.isEmpty()) {
			logicHandler.updateLogics();
		}
		entitiesToAdd = new ArrayList<BaseEntity>();
		entitiesToRemove = new ArrayList<BaseEntity>();
	}

	public void logic(int delta) {
		updateEntityList();

		logicHandler.logic(delta);


		//removing all dead entities
		for (BaseEntity entity : entities) {
			if (!entity.isAlive) {
				entitiesToRemove.add(entity);
			}
		}

	}

	public void render(Graphics g) {
		Rectangle screen = new Rectangle(level.getRealX(0), level.getRealY(0), Engine.SCREEN_WIDTH, Engine.SCREEN_HEIGHT);
		for (BaseEntity entity : entities) {
			if (entity.getComponent(Position.class).getAAHitBox().intersects(screen)) {
				entity.render(g);
			}
		}
	}

	public void add(BaseLogic logic, Class<? extends BaseEvent>... events) {
		logicHandler.add(logic, events);
	}

	public void del(BaseLogic logic) {
		logicHandler.del(logic);
	}

	public void add(BaseEntity entity) {
		entitiesToAdd.add(entity);
	}

	public void del(BaseEntity entity) {
		entity.isAlive = false;
		entitiesToRemove.add(entity);
	}
}
