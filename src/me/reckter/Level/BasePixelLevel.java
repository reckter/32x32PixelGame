package me.reckter.Level;

import me.reckter.Engine;
import me.reckter.Entity.Component.Position;
import me.reckter.Interface.HUD.FPSlabel;
import me.reckter.Tile.BaseTile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.Log;

/**
 * Created by mediacenter on 08.05.14.
 *
 * @author Hannes Güdelhöfer
 */
public class BasePixelLevel extends BaseLevel {

	public int PIXEL = 32;


	/**
	 * gets called bevor every logic tick
	 *
	 * @param input
	 */
	@Override
	public void processInput(Input input) {
		if(input.isKeyDown(Input.KEY_UP)) {
			camY++;
		}
		if(input.isKeyDown(Input.KEY_DOWN)) {
			camY--;
		}
		if(input.isKeyDown(Input.KEY_LEFT)) {
			camX++;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			camX--;
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

		interfaces.add(new FPSlabel(this));
		super.populate();
		for(int x = 0; x < tileHandler.WIDTH; x++) {
			for(int y = 0; y < tileHandler.HEIGHT; y++) {
				tileHandler.tiles[x][y] = new BaseTile(this, x, y);
			}
		}
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

		//entities.render(g);

		interfaces.render(g);
	}
}
