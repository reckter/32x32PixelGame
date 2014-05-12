package me.reckter.Entity.Component;

/**
 * Created by mediacenter on 09.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Player extends BaseComponent {

	public me.reckter.Player owner;

	public Player(me.reckter.Player owner) {
		this.owner = owner;
	}
}
