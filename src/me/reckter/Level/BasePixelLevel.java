package me.reckter.Level;

import me.reckter.Engine;
import me.reckter.Entity.Component.Building;
import me.reckter.Entity.Component.Position;
import me.reckter.Entity.Entities.Building.BaseBuilding;
import me.reckter.Entity.Entities.Building.Energizer;
import me.reckter.Interface.HUD.Minerals;
import me.reckter.Tile.EnergyTile;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

/**
 * Created by mediacenter on 08.05.14.
 *
 * @author Hannes Güdelhöfer
 */
public class BasePixelLevel extends BaseLevel {

	public int PIXEL = 32;

	public int lastCamInput;


	/**
	 * gets called bevor every logic tick
	 *
	 * @param input
	 */
	@Override
	public void processInput(Input input) {
		if(lastCamInput <= 0) {
			lastCamInput = 20;
			if(input.isKeyDown(Input.KEY_UP)) {
				camY--;
			}
			if(input.isKeyDown(Input.KEY_DOWN)) {
				camY++;
			}
			if(input.isKeyDown(Input.KEY_LEFT)) {
				camX--;
			}
			if(input.isKeyDown(Input.KEY_RIGHT)) {
				camX++;
			}
		}

		if(camX < 0) {
			camX = 0;
		}
		if(camX + PIXEL > tileHandler.WIDTH) {
			camX = tileHandler.WIDTH - PIXEL;
		}

		if(camY < 0) {
			camY = 0;
		}
		if(camY + PIXEL > tileHandler.HEIGHT) {
			camY = tileHandler.HEIGHT - PIXEL;
		}
		super.processInput(input);
	}

	/**
	 * populates the Level
	 */
	@Override
	public void populate() {
		Log.info("pixel: " + PIXEL);

		super.populate();
		interfaces.add(new Minerals(this));
		for(int x = 0; x < tileHandler.WIDTH; x++) {
			for(int y = 0; y < tileHandler.HEIGHT; y++) {
				tileHandler.tiles[x][y] = new EnergyTile(this, x, y);
				//((EnergyTile) tileHandler.tiles[x][y]).energyLevel = (float) Math.random() * 10 + 10;

				int tmp = Math.abs((int) (((Math.abs(Math.cosh(x / 5f))+ 0.2) * (Math.atan(Math.cos(y / 7f)) + 0.2) * (Math.random() + 0.5) * 257) % 255));
				int count = 1;
				if(x > 0) {
					tmp += tileHandler.tiles[x - 1][y].color;
					count++;
				}
				if(y > 0) {
					tmp += tileHandler.tiles[x][y - 1].color;
					count++;
					tmp /= count;
					tileHandler.tiles[x][y].color = tmp;
				}
			}
		}
		BaseBuilding tmp = new BaseBuilding(this, players.get("player"));
		tmp.init();
		tmp.getComponent(Position.class).tile = tileHandler.getTile(10,10);
		add(tmp);

		tmp = new BaseBuilding(this, players.get("player"));
		tmp.init();
		tmp.getComponent(Position.class).tile = tileHandler.getTile(5,16);
		tmp.getComponent(Position.class).size = new Vector2f(5,4);
		tmp.getComponent(Building.class).energyCost = 500;
		add(tmp);

		tmp = new Energizer(this, players.get("player"));
		tmp.init();
		tmp.getComponent(Position.class).tile = tileHandler.getTile(15,16);
		add(tmp);

		tmp = new Energizer(this, players.get("player"));
		tmp.init();
		tmp.getComponent(Position.class).tile = tileHandler.getTile(20,16);
		tmp.getComponent(Position.class).size = new Vector2f(10,10);
		tmp.getComponent(me.reckter.Entity.Component.Energizer.class).MAX_COOLDOWN = 10000;
		tmp.getComponent(me.reckter.Entity.Component.Energizer.class).efficiency = 0.7f;
		add(tmp);
	}

	/**
	 * gets called every logic tick
	 *
	 * @param delta the time in ms since the last logic tick
	 */
	@Override
	public void logic(int delta) {
		super.logic(delta);
		lastCamInput-=delta;
	}

	/**
	 * gets called when Level is initialized
	 */
	@Override
	public void init() {
		super.init();
		lastCamInput = 0;
	}

	/**
	 * gets called evey render
	 *
	 * @param g the graphics object on which should be drawn
	 */
	@Override
	public void render(Graphics g) {

		g.scale(Engine.SCREEN_WIDTH / PIXEL, Engine.SCREEN_HEIGHT / PIXEL);
		g.translate((int) -camX, (int) -camY);

		tileHandler.render(g);

		//particles.render(g);

		entities.render(g);

		interfaces.render(g);
	}
}
