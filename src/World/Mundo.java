package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mundo {

    public static Tile[] tiles;
    public static int WIDTH, HEIGHT;
    // public static final int TILE_SIZE = 16;

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
                        // }else if(pixelAtual == 0xFF0026FF) {

                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {

        for (int xx = 0; xx <= WIDTH; xx++) {
            for (int yy = 0; yy <= HEIGHT; yy++) {
                if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
                    continue;
                Tile tile = tiles[xx + (yy * WIDTH)];
                tile.render(g);
            }
        }
    }

}
