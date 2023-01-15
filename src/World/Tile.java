package World;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import Main.Game;

public class Tile {

    public static BufferedImage TILE_FLOOR = Game.map.getSprite(0, 0, 16, 16);
    public static BufferedImage TILE_WALL = Game.map.getSprite(16, 0, 16, 16);
    private BufferedImage sprite;
    private int x, y;

    public Tile(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void render(Graphics g) {
        g.drawImage(sprite, x, y, null);

    }

}
