package me.reckter.Entity.Events.Handlers;

import me.reckter.Entity.Entities.BaseEntity;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public interface EntityAddDelHandler<T extends BaseEntity> extends EntityAddHandler<T>, EntityDelHandler<T> {

}
