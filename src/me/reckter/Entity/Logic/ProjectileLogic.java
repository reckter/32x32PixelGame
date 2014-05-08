package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Component.Projectile;
import me.reckter.Entity.Entities.Projectile.BaseProjectile;
import me.reckter.Entity.Events.CollisionEvent;
import me.reckter.Entity.Events.DamageEvent;
import me.reckter.Entity.Events.DeathEvent;
import me.reckter.Entity.Events.Handlers.CollisionEventHandler;
import me.reckter.Entity.Events.Handlers.DeathEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Entity.Exception.StoppEventExecutionException;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class ProjectileLogic extends BaseLogic implements CollisionEventHandler, DeathEventHandler {

	public ProjectileLogic(BaseLevel level) {
		super(new Matching("projectile"), level);
	}

	/**
	 * gets called when the victim collides with the offender
	 *
	 * @param event
	 */
	@Override
	public void onCollsion(CollisionEvent event) {
		Projectile projectile = event.victim.getComponent(Projectile.class);
		if(!projectile.originCanTakeDamage && event.offender == projectile.origin) {
			throw new StoppEventExecutionException("Collision with the origin");
		}
		if (!(event.offender instanceof BaseProjectile) && event.victim instanceof BaseProjectile && event.offender != event.victim.getComponent(Projectile.class).origin) {
			level.fire(new DamageEvent(event.offender, event.victim, ((BaseProjectile) event.victim).getComponent(Projectile.class).dmg));
		}
	}

	/**
	 * gets called when the victim died from the offender
	 *
	 * @param event
	 */
	@Override
	public void onDeath(DeathEvent event) {
		event.victim.isAlive = false;
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.MIDDLE;
	}

}
