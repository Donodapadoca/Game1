package Entidades;

import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

import java.awt.Graphics;

public class Entity {

    public static BufferedImage maça = Game.spritesheet.getSprite(6 * 16, 0, 16, 16);
    public static BufferedImage arma = Game.spritesheet.getSprite(7 * 16, 0, 16, 16);
    public static BufferedImage bala = Game.spritesheet.getSprite(6 * 16, 16, 16, 16);
    public static BufferedImage inimigo = Game.spritesheet.getSprite(7 * 16, 16, 16, 16);

    protected double x;
    protected double y;
    protected int width;
    protected int height;

    public BufferedImage sprite;

    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public void setX(int newX) {
        this.x = newX;

    }

    public void setY(int newY) {
        this.y = newY;

    }

    public int getX() {
        return (int) this.x;

    }

    public int getY() {
        return (int) this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null, null);

    }

}