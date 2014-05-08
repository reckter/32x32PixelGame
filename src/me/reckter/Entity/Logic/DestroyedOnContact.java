package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Events.CollisionEvent;
import me.reckter.Entity.Events.DeathEvent;
import me.reckter.Entity.Events.Handlers.CollisionEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

/**
 * Created by mediacenter on 11.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class DestroyedOnContact extends BaseLogic implements CollisionEventHandler {
	public DestroyedOnContact(BaseLevel level) {
		super(new Matching("destroyedOnContact"), level);
	}

	/**
	 * gets called when the victim collides with the offender
	 *
	 * @param event
	 */
	@Override
	public void onCollsion(CollisionEvent event) {
		me.reckter.Entity.Component.DestroyedOnContact countdown = event.victim.getComponent(me.reckter.Entity.Component.DestroyedOnContact.class);
		if(countdown.onCollision()) {
			fire(new DeathEvent(event.victim, event.offender));
		}
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.LOW;
	}
}
