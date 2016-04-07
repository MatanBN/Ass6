/**
 * Created by user on 07/04/2016.
*/
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Paddle is a moving block in the lowest row on the surface.
 * It's members are rectangle, the surface borders and a keyboard sensor.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard; // The keyboard sensor
    private Rectangle rectangle; // The shape of the paddle
    private Rectangle borders; // A rectangle with the borders of the surface

    /**
     * The constructor creats the paddle.
     * @param key is the keyborad sensor in order to move it with the keyboard.
     * @param rec is the rectangle with the parameter for the paddle.
     * @param border is surface border.
     * @param color is the color of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor key, Rectangle rec, Rectangle border, Color color){
        keyboard=key;
        rectangle = new Rectangle(rec.getUpperLeft(),rec.getWidth(),rec.getHeight(), color);
        borders= border;
    }

    /**
     * moveLeft is in charge of moving the paddle left.
     * The method checks whether the next x coordinate is out left edge or not
     * and moves it as it should.
     */
    public void moveLeft(){
        if (rectangle.getX()-5 >= borders.getX()) {
            rectangle.getUpperLeft().setX(rectangle.getX() - 5);
        }else {
            rectangle.getUpperLeft().setX(borders.getX());
        }
    }

    /**
     * moveRight is in charge of moving the paddle right.
     * The method checks whether the next x coordinate is out right edge or not
     * and moves it as it should.
     */
    public void moveRight(){
        if (rectangle.getX()+rectangle.getWidth()+5 <= borders.getWidth()){
            rectangle.getUpperLeft().setX(rectangle.getX() + 5);
        }else {
            rectangle.getUpperLeft().setX(borders.getWidth()-rectangle.getWidth());
        }
    }

    /**
     * timePassed checks whether the key that the user pressed is left key or right
     * key and calls the suitable method in order to move it to the right side.
     */
    public void timePassed(){
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawOn method draws the paddle on a given surface.
     * @param d is the surface to draw the paddle on
     */
    public void drawOn(DrawSurface d){
        rectangle.drawOn(d);
    }

    /**
     * getCollisionRectangle returns the paddle's shape.
     * @return the given rectangle.
     */
    public Rectangle getCollisionRectangle(){
        return this.rectangle;
    }

    /**
     * hit return the new velocity after the hit based on force the object inflicted on us.
     * @param collisionPoint is the collision point of an object with the paddle.
     * @param currentVelocity is the current velocity of the object that will collide with the paddle.
     * @return the new velocity after the hit.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity){
        Block block = new Block(this.getCollisionRectangle());
        return block.hit(collisionPoint, currentVelocity);
    }

    /**
     * addToGame is in charge of adding the paddle as a sprite
     * and as a collidable to the game's suitable lists.
     * @param g is the game object we created.
     */
    public void addToGame(Game g){
        g.addSprite(this);
        g.addCollidable(this);
    }
}
