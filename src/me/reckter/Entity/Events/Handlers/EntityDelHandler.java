package me.reckter.Entity.Events.Handlers;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.EntityDelEvent;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public interface EntityDelHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * gets called when an entity gets deleted
	 *
	 * @param event the entity deltion event
	 */
	public void onEntityDel(EntityDelEvent<T> event);
}
