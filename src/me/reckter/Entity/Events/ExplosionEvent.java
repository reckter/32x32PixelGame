package me.reckter.Entity.Events;

import me.reckter.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 2/25/14.
 *
 * @author Hannes Güdelhöfer
 */
public class ExplosionEvent<T extends BaseEntity> extends BaseEvent<T> {

	public ExplosionEvent(T victim, BaseEntity offender) {
		super(victim, offender);
	}


	public ExplosionEvent(T victim) {
		this(victim, victim);
	}


}
