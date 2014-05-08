package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Component.Projectile;
import me.reckter.Entity.Entities.Projectile.BaseProjectile;
import me.reckter.Entity.Events.DeathEvent;
import me.reckter.Entity.Events.Handlers.DeathEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class EnemyDeath extends BaseLogic implements DeathEventHandler {
	public EnemyDeath(BaseLevel level) {
		super(new Matching("enemy"), level);
	}

	/**
	 * gets called when the victim died from the offender
	 *
	 * @param event
	 */
	@Override
	public void onDeath(DeathEvent event) {
		if (event.offender instanceof BaseProjectile) {

		}
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.MIDDLE;
	}
}
