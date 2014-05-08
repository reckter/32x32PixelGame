package me.reckter.Entity.Component;

import me.reckter.Entity.Category.CollisionGroup;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by mediacenter on 09.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Collision extends BaseComponent {

	public TreeSet<CollisionGroup> collisionGroups;
	public Collision(CollisionGroup goup) {
		collisionGroups = new TreeSet<CollisionGroup>();
		collisionGroups.add(goup);
	}


	/**
	 * gets called with a already initialized ArrayList
	 * should get overriden, if something requires another component
	 *
	 * @param ret the list, which should get filled
	 * @return the list, which contains every Component, that is required
	 */
	@Override
	protected ArrayList<Class<? extends BaseComponent>> requires(ArrayList<Class<? extends BaseComponent>> ret) {
		ret.add(Position.class);
		return ret;
	}
}
