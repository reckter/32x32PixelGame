package me.reckter.Entity.Events;

import me.reckter.Entity.Entities.BaseEntity;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class EntityAddEvent<T extends BaseEntity> extends BaseEvent<T> {

	public EntityAddEvent(T victim) {
		super(victim, victim);

	}
}
