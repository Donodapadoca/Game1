package World;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
// import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Mundo {
    private Tile[] tiles;
    public static int WIDTH, HEIGHT;

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

                    if (atualpix == 0xFF000000) {

                        // // chão

                        tiles[xx + (yy * WIDTH)] = new Tilechao(xx * 16, yy * 16,
                                Tile.TILE_FLOOR);

                    } else if (atualpix == 0xFFFFFFFF) {
                        // parede
                        tiles[xx + (yy * WIDTH)] = new Tileparede(xx * 16, yy * 16, Tile.TILE_WALL);

                    } else if (atualpix == 0xFF0026FF) {
                        tiles[xx + (yy * WIDTH)] = new Tilechao(xx * 16, yy * 16, Tile.TILE_FLOOR);

                        // player

                    } else {
                        tiles[xx + (yy * mapinha.getWidth())] = new Tilechao(xx * 16, yy * 16, Tile.TILE_FLOOR);
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(Graphics g) {
        for (int xx = 0; xx < WIDTH; xx++) {
            for (int yy = 0; yy > HEIGHT; yy++) {
                Tile tile = tiles[xx + (yy * WIDTH)];
                tile.render(g);

            }
        }

    }

}
