package me.reckter.Entity.Events.Handlers;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.DamageEvent;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface DamageEventHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * gets called when the victim gets Damage from the offender
	 *
	 * @param event
	 */
	public void onDamage(DamageEvent<T> event);
}
