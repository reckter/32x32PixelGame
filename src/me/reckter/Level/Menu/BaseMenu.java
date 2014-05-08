package me.reckter.Level.Menu;

import me.reckter.Engine;
import me.reckter.Entity.Component.Menu;
import me.reckter.Entity.Component.Position;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Enemy.Menu.BaseMenueItem;
import me.reckter.Entity.Events.DeathEvent;
import me.reckter.Level.BasePixelLevel;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;


/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseMenu extends BasePixelLevel {

	protected Vector2f start;
	protected Vector2f shift;
	protected int selectedItem;

	private ArrayList<BaseMenueItem> menueItems;


	@Override
	public void init() {
		super.init();

		menueItems = new ArrayList<BaseMenueItem>();
		selectedItem = 0;

		entities.add(new me.reckter.Entity.Logic.Menu(this), DeathEvent.class);

		WIDTH = Engine.SCREEN_WIDTH;
		HEIGHT = Engine.SCREEN_HEIGHT;

		start = new Vector2f(WIDTH / 2 - 65, 100);
		shift = new Vector2f(0, 50);
	}

	@Override
	public void populate() {
		super.populate();
	}

	@Override
	public void render(Graphics g) {
		camX = 0;
		camY = 0;

		particles.render(g);

		entities.render(g);

		interfaces.render(g);
	}


	/**
	 * gets called bevor every logic tick
	 *
	 * @param input
	 */
	@Override
	public void processInput(Input input) {
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			selectedItem += 1;
			if(selectedItem >= menueItems.size()) {
				selectedItem = 0;
			}
		}
		if(input.isKeyPressed(Input.KEY_UP)) {
			selectedItem -= 1;
			if(selectedItem < 0) {
				selectedItem = menueItems.size() - 1;
			}
		}
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			invoke(menueItems.get(selectedItem).getComponent(Menu.class).name);
		}
		super.processInput(input);
	}

	/**
	 * gets called every logic tick
	 *
	 * @param delta the time in ms since the last logic tick
	 */
	@Override
	public void logic(int delta) {
		for(BaseMenueItem menuItem: menueItems) {
			menuItem.getComponent(Menu.class).isHighlighted = false;
		}
		if(menueItems.size() > 0) {
			menueItems.get(selectedItem).getComponent(Menu.class).isHighlighted = true;
		}
		super.logic(delta);
	}

	public BaseMenueItem addNewMenueItem(String name) {
		BaseMenueItem tmp = new BaseMenueItem(this, name, start.copy().add(shift.copy().scale(menueItems.size())));
		menueItems.add(tmp);
		add(tmp);
		return tmp;
	}

	/**
	 * ads an entity to the level
	 *
	 * @param entity
	 */
	@Override
	public void add(BaseEntity entity) {
		super.add(entity);
	}

	public void spaceOutMenueItems() {
		float maxY = 650;
		for(int i = 0; i < menueItems.size(); i++) {
			BaseMenueItem item = menueItems.get(i);
			item.getComponent(Position.class).tile.y = (int) (start.y + (maxY / menueItems.size()) * i);
		}
	}

	public void invoke(String name) {
		if(name.equals("Start Game")) {
			nextLevel = new BasePixelLevel();
		} else if(name.equals("Option")) {
			nextLevel = new MainMenu();
		} else if(name.equals("Exit")) {
			nextLevel = new Exit();
		} else if(name.equals("Credits")) {
			nextLevel = new MainMenu();
		}
	}
}
