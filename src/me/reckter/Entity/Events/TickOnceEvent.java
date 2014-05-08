package me.reckter.Entity.Events;

import me.reckter.Entity.Entities.BaseEntity;

import java.util.ArrayList;

/**
 * Created by mediacenter on 05.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class TickOnceEvent extends BaseEvent {
	public ArrayList<BaseEntity> entities;

	public TickOnceEvent(ArrayList<BaseEntity> entities) {
		super(null, null);
		this.entities = entities;
	}
}
