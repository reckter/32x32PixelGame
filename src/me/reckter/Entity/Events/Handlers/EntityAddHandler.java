package me.reckter.Entity.Events.Handlers;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.EntityAddEvent;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public interface EntityAddHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * gets called when an entity gets added
	 *
	 * @param event the entity Addition event
	 */
	public void onEntityAdd(EntityAddEvent<T> event);
}
