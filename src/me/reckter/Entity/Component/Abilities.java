package me.reckter.Entity.Component;

import me.reckter.Abilities.AbilityHandler;
import me.reckter.Abilities.BaseAbility;

/**
 * Created by mediacenter on 09.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Abilities extends BaseComponent {
	public AbilityHandler abilities;

	public String weapon;

	public Abilities() {
		this(new AbilityHandler(), "");
		abilities = new AbilityHandler();
		weapon = "";
	}

	public Abilities(AbilityHandler abilities) {
		this(abilities, "");
		this.abilities = abilities;
	}

	public Abilities(AbilityHandler abilities, String weapon) {
		this.abilities = abilities;
		this.weapon = weapon;
	}

	public boolean castWeapon() {
		return abilities.cast(weapon);
	}

	public BaseAbility getActiveWeapon() {
		return abilities.get(weapon);
	}

}
