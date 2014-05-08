package me.reckter.Entity;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.*;
import me.reckter.Entity.Events.Handlers.*;
import me.reckter.Entity.Exception.StoppEventExecutionException;
import me.reckter.Entity.Logic.BaseLogic;
import me.reckter.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class LogicHandler {

	HashMap<BaseLogic, ArrayList<BaseEntity>> logicsEntities;
	HashMap<Class<? extends BaseEvent>, ArrayList<BaseLogic>> logics;

	protected int delta;

	EntityHandler entities;

	public LogicHandler(EntityHandler entityHandler) {
		entities = entityHandler;
		logicsEntities = new HashMap<BaseLogic, ArrayList<BaseEntity>>();
		logics = new HashMap<Class<? extends BaseEvent>, ArrayList<BaseLogic>>();
		registerEvents();
	}

	/**
	 * registers all Events, new Events have to get implemented here TODO better idea?
	 */
	private void registerEvents() {
		logics.put(AcceleratingEvent.class, new ArrayList<BaseLogic>());
		logics.put(CollisionEvent.class, new ArrayList<BaseLogic>());
		logics.put(CollisionEvent.class, new ArrayList<BaseLogic>());
		logics.put(DamageEvent.class, new ArrayList<BaseLogic>());
		logics.put(DeathEvent.class, new ArrayList<BaseLogic>());
		logics.put(EntityAddEvent.class, new ArrayList<BaseLogic>());
		logics.put(EntityDelEvent.class, new ArrayList<BaseLogic>());
		logics.put(ExplosionEvent.class, new ArrayList<BaseLogic>());
		logics.put(TickEvent.class, new ArrayList<BaseLogic>());
		logics.put(TickOnceEvent.class, new ArrayList<BaseLogic>());
	}


	public void logic(int delta) {
		this.delta = delta;
		for (BaseLogic logic : logics.get(TickEvent.class)) {
			for (BaseEntity entity : logicsEntities.get(logic)) {
				((TickEventHandler) logic).onTick(delta, entity);
			}
		}
		for (BaseLogic logic : logics.get(TickOnceEvent.class)) {
			if (logic instanceof TickOnceEventHandler) {
				((TickOnceEventHandler) logic).onTick(logicsEntities.get(logic), delta);
			}
		}
	}


	public void fireEvent(BaseEvent event) {
		event.delta = this.delta;
		try{
			for(BaseLogic logic: logics.get(event.getClass())) {
				if(event.victim.matches(logic.matching)){
					if (event instanceof DamageEvent) {
						((DamageEventHandler) logic).onDamage((DamageEvent) event);
					} else if (event instanceof DeathEvent) {
						((DeathEventHandler) logic).onDeath((DeathEvent) event);
					} else if (event instanceof CollisionEvent) {
						((CollisionEventHandler) logic).onCollsion((CollisionEvent) event);
					} else if (event instanceof AcceleratingEvent) {
						((AcceleratingEventHandler) logic).onAcceleration((AcceleratingEvent) event);
					} else if (event instanceof ExplosionEvent) {
						((ExplosionEventHandler) logic).onExplosion((ExplosionEvent) event);
					} else if (event instanceof EntityAddEvent) {
						((EntityAddHandler) logic).onEntityAdd((EntityAddEvent) event);
					} else if (event instanceof EntityDelEvent) {
						((EntityDelHandler) logic).onEntityDel((EntityDelEvent) event);
					}
				}
			}
		} catch (StoppEventExecutionException e) {
			if(e.needsToBeDisplayed) {
				Log.note("Stopped Event Execution of the event " + event + ": " + e.getMessage());
			}
		}
	}

	public void updateLogics() {
		for (BaseLogic logic : logicsEntities.keySet()) {
			logicsEntities.get(logic).clear();
			for (BaseEntity entity : entities.entities) {
				if (entity.matches(logic.matching)) {
					logicsEntities.get(logic).add(entity);
				}
			}
		}
	}

	public void add(BaseLogic logic, Class<? extends BaseEvent>... events) {
		logicsEntities.put(logic, new ArrayList<BaseEntity>());

		for (Class<? extends BaseEvent> event : events) {
			logics.get(event).add(logic);
			Collections.sort(logics.get(event), new LogicComparator());
		}

		updateLogics();
	}


	public void del(BaseLogic logic) {
		logicsEntities.remove(logic);
		for(ArrayList<BaseLogic> list: logics.values()) {
			list.remove(logic);
		}
	}

	private class LogicComparator implements Comparator<BaseEventHandler> {
		@Override
		public int compare(BaseEventHandler o1, BaseEventHandler o2) {
			return o1.getPriority().compareTo(o2.getPriority());
		}
	}
}
