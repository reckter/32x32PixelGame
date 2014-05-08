package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.CollisionGroup;
import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Component.Collision;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.CollisionEvent;
import me.reckter.Entity.Events.EntityAddEvent;
import me.reckter.Entity.Events.EntityDelEvent;
import me.reckter.Entity.Events.Handlers.EntityAddDelHandler;
import me.reckter.Entity.Events.Handlers.TickOnceEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;
import me.reckter.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class CollisionChecking extends BaseLogic implements TickOnceEventHandler, EntityAddDelHandler {


	public TreeMap<CollisionGroup, ArrayList<BaseEntity>> groups;
	public TreeMap<CollisionGroup, HashSet<CollisionGroup>> collision;

	public CollisionChecking(BaseLevel level) {
		super(new Matching("collision"), level);

		groups = new TreeMap<CollisionGroup, ArrayList<BaseEntity>>();
		collision = new TreeMap<CollisionGroup, HashSet<CollisionGroup>>();

		for (CollisionGroup group : CollisionGroup.values()) {
			groups.put(group, new ArrayList<BaseEntity>());

			collision.put(group, new HashSet<CollisionGroup>());

			addCollisionRule(group, group);
		}
	}

	/**
	 * gets called every tick, after each entity allready got called, but only gets called once for all entities,
	 * with all entities that matches the gven Matching (requires to be a sublcass of BaseLogic)
	 *
	 * @param entities an ArrayList of matching entities
	 * @param delta    the delta between the last call and the current one
	 */
	@Override
	public void onTick(ArrayList<BaseEntity> entities, int delta) {

		HashSet<BaseEntity> checkedEntities	= new HashSet<BaseEntity>();
		for(CollisionGroup groupA: CollisionGroup.values()) {
			for (CollisionGroup groupB : collision.get(groupA)) {

				for(BaseEntity entityA: groups.get(groupA)) {
					checkedEntities.clear();
					for (BaseEntity entityB : groups.get(groupB)) {

						if(checkedEntities.contains(entityB)) {
							continue;
						}

						checkedEntities.add(entityB);
						if (entityA != entityB) {
							if (Util.checkCollision(entityA, entityB)) {
								level.fire(new CollisionEvent(entityA, entityB));
							}
						}
					}
				}

			}
		}

	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.MIDDLE;
	}

	/**
	 * gets called when an entity gets added
	 *
	 * @param event the entity Addition event
	 */
	@Override
	public void onEntityAdd(EntityAddEvent event) {
		for (CollisionGroup group : event.victim.getComponent(Collision.class).collisionGroups) {
			groups.get(group).add(event.victim);
		}
	}

	/**
	 * gets called when an entity gets deleted
	 *
	 * @param event the entity deltion event
	 */
	@Override
	public void onEntityDel(EntityDelEvent event) {
		for (CollisionGroup group : event.victim.getComponent(Collision.class).collisionGroups) {
			groups.get(group).remove(event.victim);
		}
	}

	/**
	 * adds the rule, that the collider collides with 'with', thus checking if collider is coliding with 'with'
	 *
	 * @param collider the group that will be checked
	 * @param with     the group that will be checked against
	 */
	public void addCollisionRule(CollisionGroup collider, CollisionGroup with) {
		collision.get(collider).add(with);
	}

	/**
	 * removes a collision rule, so that collider no longer collides with 'with'
	 *
	 * @param collider the group that has been checked
	 * @param with     the goup that has been checked against
	 */
	public void dellCollisionRule(CollisionGroup collider, CollisionGroup with) {
		collision.get(collider).remove(with);
	}


}
