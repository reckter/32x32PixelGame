package me.reckter.Entity.Events.Handlers;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.AcceleratingEvent;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface AcceleratingEventHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * gets called when the given entity accelerates
	 *
	 * @param event
	 */
	public void onAcceleration(AcceleratingEvent<T> event);
}
