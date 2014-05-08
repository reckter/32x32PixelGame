package me.reckter.Entity.Events;

import me.reckter.Entity.Entities.BaseEntity;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class EntityDelEvent<T extends BaseEntity> extends BaseEvent<T> {

	public EntityDelEvent(T victim) {
		super(victim, victim);

	}
}
