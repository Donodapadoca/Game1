package Entidades;

import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;
import World.Mundo;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Entity {

    private double speed = 0.6;
    private int maskX = 8, maskY = 8, maskW = 10, maskH = 10;

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public void tick() {

        if ((int) x < Game.player.getX() && Mundo.estalivre((int) (x + speed), this.getY())
                && !colisaoini((int) (x + speed), this.getY())) {
            x += speed;
        } else if ((int) x > Game.player.getX() && Mundo.estalivre((int) (x - speed), this.getY())
                && !colisaoini((int) (x - speed), this.getY())) {
            x -= speed;

        }
        if ((int) y < Game.player.getY() && Mundo.estalivre(this.getX(), (int) (y + speed))
                && !colisaoini(this.getX(), (int) (y + speed))) {
            y += speed;
        } else if ((int) y > Game.player.getY() && Mundo.estalivre(this.getX(), (int) (y - speed))
                && !colisaoini(this.getX(), (int) (y - speed))) {
            y -= speed;

        }

    }

    public boolean colisaoini(int nextx, int nexty) {
        Rectangle enemyagr = new Rectangle(nextx + maskX, nexty + maskY, maskW, maskH);
        for (int i = 0; i < Game.inimigos.size(); i++) {
            Enemy e = Game.inimigos.get(i);
            if (e == this) {
                continue;
            }
            Rectangle targetEnemy = new Rectangle(e.getX() + maskX, e.getY() + maskY, maskW, maskH);
            if (targetEnemy.intersects(enemyagr)) {
                return true;
            }
        }
        return false;
    }

    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.blue);
        g.fillRect(this.getX() + maskX - Camera.x, this.getY() + maskY - Camera.y, maskW, maskH);
    }

}
