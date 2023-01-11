package Entidades;

import java.awt.image.BufferedImage;

import Main.Game;

import java.awt.Graphics;

public class Player extends Entity {

    public boolean d, a, w, s;
    public double speed = 1.4;
    public int ddireita = 0, desquerda = 1, dcima = 2, dbaixo = 3;
    public int dir = ddireita;

    private int frames = 0, maxframe = 5, index = 0, maxindex = 3;
    private boolean move = false;
    private BufferedImage[] direitaPlayer;
    private BufferedImage[] esquerdaPlayer;
    private BufferedImage[] cimaPlayer;
    private BufferedImage[] baixoPlayer;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        direitaPlayer = new BufferedImage[4];
        esquerdaPlayer = new BufferedImage[4];
        cimaPlayer = new BufferedImage[4];
        baixoPlayer = new BufferedImage[4];

        for (int i = 0; i < 4; i++) {
            direitaPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
        }

        for (int i = 0; i < 4; i++) {
            esquerdaPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 16, 16, 16);
        }
        for (int i = 0; i < 4; i++) {
            cimaPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 32, 16, 16);
        }
        for (int i = 0; i < 4; i++) {
            baixoPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 48, 16, 16);
        }

    }

    public void tick() {
        move = false;
        if (d) {
            move = true;
            dir = ddireita;
            x += speed;

        }

        else if (a) {
            move = true;
            dir = desquerda;
            x -= speed;
        }

        if (w) {
            move = true;
            dir = dcima;
            y -= speed;
        }

        else if (s) {
            move = true;
            dir = dbaixo;
            y += speed;
        }
        if (move) {
            frames++;
            if (frames == maxframe) {
                frames = 0;
                index++;
                if (index > maxindex) {
                    index = 0;
                }
            }
        }

    }

    public void render(Graphics g) {
        if (dir == ddireita) {
            g.drawImage(direitaPlayer[index], this.getX(), this.getY(), null);

        } else if (dir == desquerda) {
            g.drawImage(esquerdaPlayer[index], this.getX(), this.getY(), null);
        }
        if (dir == dcima) {
            g.drawImage(cimaPlayer[index], this.getX(), this.getY(), null);

        }
        if (dir == dbaixo) {
            g.drawImage(baixoPlayer[index], this.getX(), this.getY(), null);

        }

    }

}
