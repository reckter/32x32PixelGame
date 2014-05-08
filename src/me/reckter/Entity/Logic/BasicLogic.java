package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Component.Life;
import me.reckter.Entity.Component.Position;
import me.reckter.Entity.Component.Projectile;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.DamageEvent;
import me.reckter.Entity.Events.DeathEvent;
import me.reckter.Entity.Events.Handlers.DamageEventHandler;
import me.reckter.Entity.Events.Handlers.DeathEventHandler;
import me.reckter.Entity.Events.Handlers.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Entity.Exception.ComponentNotFoundException;
import me.reckter.Interface.DamageText;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BasicLogic extends BaseLogic implements DeathEventHandler, DamageEventHandler, TickEventHandler {
	public BasicLogic(BaseLevel level) {
		super(new Matching("life"), level);
	}

	/**
	 * gets called when the victim gets Damage from the offender
	 *
	 * @param event
	 */
	@Override
	public void onDamage(DamageEvent event) {
		Life life = event.victim.<Life>getComponent(Life.class);
		if(life.immuneToDamge <= 0){
			try {
				life.immuneToDamge = event.offender.getComponent(Projectile.class).immuneToDamage * 10;
			} catch(ComponentNotFoundException e) {
				life.immuneToDamge = 400;
			}
			life.takeDamage(event.dmg);
			level.add(new DamageText(level, (int) event.dmg, event.victim));
			if(life.getLife() <= 0) {
				fire(new DeathEvent(event.victim, event.offender));
			}
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
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, BaseEntity entity) {
		Life life = entity.getComponent(Life.class);
		life.immuneToDamge -= delta;
	}

	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.HIGHEST;
	}

}
