package me.reckter.Tile;

import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by mediacenter on 08.05.14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseTile {
	public BaseLevel level;
	public int x;
	public int y;

	public int color;


	public BaseTile(BaseLevel level, int x, int y) {
		this.level = level;
		this.x = x;
		this.y = y;
		color = 0;
	}

	public void render(Graphics g){
		g.setColor(new Color(0, color,0));
		g.fill(new Rectangle(x, y, 2, 2));
	}
}
