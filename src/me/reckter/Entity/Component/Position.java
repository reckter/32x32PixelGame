package me.reckter.Entity.Component;

import me.reckter.Tile.BaseTile;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 08.04.14.
 *
 * @author Hannes Güdelhöfer
 */
public class Position extends BaseComponent {
	public BaseTile tile;
	public Vector2f size;
	public double angle;

	public Position(BaseTile tile, Vector2f size, double angle) {
		this.tile = tile;
		this.size = size;
		this.angle = angle;
	}

	public Position(BaseTile tile) {
		this(tile, new Vector2f(10,10), 0);
	}



	public Rectangle getAAHitBox() {
		return new Rectangle(tile.x - size.x, tile.y - size.y, size.x * 2, size.y * 2);
	}

	public Shape getHitBox() {
		return new Rectangle(tile.x - size.x, tile.y - size.y, size.x * 2, size.y * 2);
	}

	public void setHeight(float height) {
		this.size.y = height;
	}
}
