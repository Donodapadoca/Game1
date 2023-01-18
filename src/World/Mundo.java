package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entidades.Arma;
import Entidades.Balas;
import Entidades.Enemy;
import Entidades.Entity;
import Entidades.Vida;
import Main.Game;

public class Mundo {

    public static Tile[] tiles;
    public static int WIDTH, HEIGHT;
    public static final int TILE_SIZE = 16;

    public Mundo(String path) {
        try {
            BufferedImage mapinha = ImageIO.read(getClass().getResource(path));
            int[] pix = new int[mapinha.getWidth() * mapinha.getHeight()];

            WIDTH = mapinha.getWidth();
            HEIGHT = mapinha.getHeight();
            tiles = new Tile[mapinha.getWidth() * mapinha.getHeight()];
            mapinha.getRGB(0, 0, mapinha.getWidth(), mapinha.getHeight(), pix, 0, mapinha.getWidth());
            for (int xx = 0; xx < mapinha.getWidth(); xx++) {
                for (int yy = 0; yy < mapinha.getHeight(); yy++) {
                    int atualpix = pix[xx + (yy * mapinha.getWidth())];
                    tiles[xx + (yy * WIDTH)] = new Tilechao(xx * 16, yy * 16, Tile.TILE_FLOOR);
                    if (atualpix == 0xFF000000) {
                        // Floor
                        tiles[xx + (yy * WIDTH)] = new Tilechao(xx * 16, yy * 16, Tile.TILE_FLOOR);
                    } else if (atualpix == 0xFFFFFFFF) {
                        // Parede
                        tiles[xx + (yy * WIDTH)] = new Tileparede(xx * 16, yy * 16, Tile.TILE_WALL);
                    } else if (atualpix == 0xFF0026FF) {
                        Game.player.setX(xx * 16);
                        Game.player.setY(yy * 16);
                        // player

                    } else if (atualpix == 0xFFFF0000) {

                        Enemy en = new Enemy(xx * 16, yy * 16, 16, 16, Entity.inimigo);
                        Game.entities.add(en);
                        Game.inimigos.add(en);
                        // inimigo

                    } else if (atualpix == 0xFF4CFF00) {
                        Game.entities.add(new Vida(xx * 16, yy * 16, 16, 16, Entity.maça));

                        // vida

                    } else if (atualpix == 0xFF7F3F3F) {
                        Game.entities.add(new Balas(xx * 16, yy * 16, 16, 16, Entity.bala));

                        // balas

                    } else if (atualpix == 0xFFFFD800) {
                        Game.entities.add(new Arma(xx * 16, yy * 16, 16, 16, Entity.arma));

                        // arma

                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean estalivre(int nextx, int nexty) {
        int x1 = nextx / Game.TILE_SIZE;
        int y1 = nexty / Game.TILE_SIZE;

        int x2 = (nextx + Game.TILE_SIZE - 1) / Game.TILE_SIZE;
        int y2 = nexty / Game.TILE_SIZE;

        int x3 = nextx / Game.TILE_SIZE;
        int y3 = (nexty + Game.TILE_SIZE - 1) / Game.TILE_SIZE;

        int x4 = (nextx + Game.TILE_SIZE - 1) / Game.TILE_SIZE;
        int y4 = (nexty + Game.TILE_SIZE - 1) / Game.TILE_SIZE;

        return !((tiles[x1 + (y1 * Mundo.WIDTH)] instanceof Tileparede) ||
                (tiles[x2 + (y2 * Mundo.WIDTH)] instanceof Tileparede) ||
                (tiles[x3 + (y3 * Mundo.WIDTH)] instanceof Tileparede) ||
                (tiles[x4 + (y4 * Mundo.WIDTH)] instanceof Tileparede));

    }

    public void render(Graphics g) {
        int xstart = Camera.x / 16;
        int ystart = Camera.y / 16;

        int xfinal = xstart + (Game.WIDTH / 16);
        int yfinal = ystart + (Game.HEIGHT / 16);
        for (int xx = xstart; xx <= xfinal; xx++) {
            for (int yy = ystart; yy <= yfinal; yy++) {
                if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
                    continue;
                Tile tile = tiles[xx + (yy * WIDTH)];
                tile.render(g);
            }
        }
    }

}
