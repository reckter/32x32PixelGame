package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Events.DeathEvent;
import me.reckter.Entity.Events.Handlers.DeathEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;
import me.reckter.Level.Menu.BaseMenu;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Menu extends BaseLogic implements DeathEventHandler {
	public Menu(BaseLevel level) {
		super(new Matching("menu"), level);
	}

	/**
	 * gets called when the victim died from the offender
	 *
	 * @param event
	 */
	@Override
	public void onDeath(DeathEvent event) {
		((BaseMenu) level).invoke(event.victim.getComponent(me.reckter.Entity.Component.Menu.class).name);
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.HIGHEST;
	}
}
