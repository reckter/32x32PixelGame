package me.reckter.Level.Menu;

/**
 * Created by mediacenter on 13.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class MainMenu extends BaseMenu {

	@Override
	public void init() {
		super.init();
		addNewMenueItem("Start Game");
		addNewMenueItem("Options");
		addNewMenueItem("Credits");
		addNewMenueItem("Exit");
		spaceOutMenueItems();
	}
}
