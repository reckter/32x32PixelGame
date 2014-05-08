package me.reckter.Tile;

import me.reckter.Level.BaseLevel;
import me.reckter.Level.BasePixelLevel;
import org.newdawn.slick.Graphics;

/**
 * Created by mediacenter on 08.05.14.
 *
 * @author Hannes Güdelhöfer
 */
public class TileHandler {

	public int WIDTH = 128;
	public int HEIGHT = 128;


	public BaseLevel level;

	public BaseTile[][] tiles;

	public TileHandler(BaseLevel level) {
		this.level = level;
		tiles = new BaseTile[WIDTH][HEIGHT];
	}

	public BaseTile getTile(int x, int y) {
		return tiles[x][y];
	}

	public BaseTile getNeighbour(BaseTile tile, Direction direction){
		switch (direction) {
			case North: return tiles[tile.x + 1][tile.y];
			case South: return tiles[tile.x - 1][tile.y];
			case East: return tiles[tile.x][tile.y + 1];
			case West: return tiles[tile.x][tile.y - 1];
			case Northeast: return tiles[tile.x + 1][tile.y + 1];
			case Northwest: return tiles[tile.x + 1][tile.y - 1];
			case Southeast: return tiles[tile.x - 1][tile.y + 1];
			case Southwest: return tiles[tile.x - 1][tile.y - 1];
		}
		return tile;
	}

	public boolean tileExists(int x, int y) {
		return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
	}


	public void render(Graphics g) {
		for(int x = level.getCamX(); x < level.getCamX() + ((BasePixelLevel) level).PIXEL; x++) {
			for(int y = level.getCamY(); y < level.getCamY() + ((BasePixelLevel) level).PIXEL; y++){
				tiles[x][y].render(g);
			}
		}
	}
}
