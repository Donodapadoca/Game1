package Entidades;

import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;
import World.Mundo;

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
        if (d && Mundo.estalivre((int) (x + speed), this.getY())) {
            move = true;
            dir = ddireita;
            x += speed;

        }

        else if (a && Mundo.estalivre((int) (x - speed), this.getY())) {
            move = true;
            dir = desquerda;
            x -= speed;
        }

        if (w && Mundo.estalivre(this.getX(), (int) (y - speed))) {
            move = true;
            dir = dcima;
            y -= speed;
        }

        else if (s && Mundo.estalivre(this.getX(), (int) (y + speed))) {
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

            Camera.x = Camera.clamp(this.getX() - Game.WIDTH / 2, 0, Mundo.WIDTH * 16 - Game.WIDTH);
            Camera.y = Camera.clamp(this.getY() - Game.HEIGHT / 2, 0, Mundo.HEIGHT * 16 - Game.HEIGHT);
        }

    }

    public void render(Graphics g) {
        if (dir == ddireita) {
            g.drawImage(direitaPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

        } else if (dir == desquerda) {
            g.drawImage(esquerdaPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
        }
        if (dir == dcima) {
            g.drawImage(cimaPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

        }
        if (dir == dbaixo) {
            g.drawImage(baixoPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

        }

    }

}
