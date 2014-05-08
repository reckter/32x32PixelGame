package me.reckter.Entity.Events;

import me.reckter.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class DeathEvent<T extends BaseEntity> extends BaseEvent<T> {

	public DeathEvent(T victim, BaseEntity offender) {
		super(victim, offender);
	}

}
