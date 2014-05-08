package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Events.EntityAddEvent;
import me.reckter.Entity.Events.EntityDelEvent;
import me.reckter.Entity.Events.Handlers.EntityAddDelHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

/**
 * Created by mediacenter on 02.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class LogicUpdater extends BaseLogic implements EntityAddDelHandler {
	public LogicUpdater(BaseLevel level) {
		super(new Matching("ALL"), level);
	}

	/**
	 * gets called when an entity gets added
	 *
	 * @param event the entity Addition event
	 */
	@Override
	public void onEntityAdd(EntityAddEvent event) {

	}

	/**
	 * gets called when an entity gets deleted
	 *
	 * @param event the entity deltion event
	 */
	@Override
	public void onEntityDel(EntityDelEvent event) {

	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.HIGHEST;
	}
}
