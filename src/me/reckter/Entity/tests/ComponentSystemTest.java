package me.reckter.Entity.tests;

import junit.framework.Assert;
import me.reckter.Entity.Component.Enemy;
import me.reckter.Entity.Component.Life;
import me.reckter.Entity.Component.Position;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Exception.ComponentNotFoundException;
import me.reckter.Level.BaseLevel;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mediacenter on 08.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class ComponentSystemTest {

	BaseEntity entity;

	@Before
	public void setUp() {
		BaseLevel level = new BaseLevel();
		entity = new BaseEntity(level);
		entity.init();
	}


	@Test
	public void testingGetComponent() {

		Assert.assertTrue("didn't get position back", entity.<Position>getComponent(Position.class) instanceof Position);
		Assert.assertTrue("didn't get Life back", entity.<Life>getComponent(Life.class) instanceof Life);


		Assert.assertTrue("didn't get position back", entity.getComponent(Position.class) instanceof Position);
		Assert.assertTrue("didn't get Life back", entity.getComponent(Life.class) instanceof Life);
	}

	@Test(expected = ComponentNotFoundException.class)
	public void testingComponentNotFoundException() {
		entity.getComponent(Enemy.class);
	}
}
