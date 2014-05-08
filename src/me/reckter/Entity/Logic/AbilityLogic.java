package me.reckter.Entity.Logic;

import me.reckter.Abilities.AbilityHandler;
import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Component.Abilities;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.Handlers.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

/**
 * Created by mediacenter on 10.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class AbilityLogic extends BaseLogic implements TickEventHandler {
	public AbilityLogic(BaseLevel level) {
		super(new Matching("ability"), level);
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, BaseEntity entity) {
		AbilityHandler handler = entity.getComponent(Abilities.class).abilities;
		handler.logic(delta);
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.MIDDLE;
	}
}
