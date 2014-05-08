package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.DeathEvent;
import me.reckter.Entity.Events.Handlers.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

/**
 * Created by mediacenter on 09.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class TimeToLife extends BaseLogic implements TickEventHandler {
	public TimeToLife(BaseLevel level) {
		super(new Matching("timeToLife"), level);
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, BaseEntity entity) {
		me.reckter.Entity.Component.TimeToLife timeToLife = entity.getComponent(me.reckter.Entity.Component.TimeToLife.class);
		timeToLife.timeToLife -= delta;
		if(timeToLife.timeToLife <= 0) {
			fire(new DeathEvent(entity, entity));
		}
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.HIGH;
	}
}
