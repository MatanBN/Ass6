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
    private Line region1; // The left-most part of the paddle
    private Line region2; // The left-center part of the paddle
    private Line region3; // The center part of the paddle
    private Line region4; // The right-center part of the paddle
    private Line region5; // The right-most part of the paddle

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
       /** int part = rec.getWidth()/5;
        Point p1 = new Point(rec.getUpperLeft().getX(), rec.getY());
        Point p2 = new Point(rec.getX()+part, rec.getUpperLeft().getY());
        region1= new Line (p1,p2);
        p1.setX(p2.getX()+part);
        region2 = new Line (p2, p1);
        p2.setX(p1.getX()+part);
        region3 = new Line (p1,p2);
        p1.setX(p2.getX()+part);
        region4 = new Line(p2,p1);
        p2.setX(p1.getX()+part);
        region5 = new Line (p1,p2);*/
    }

    /**
     * moveLeft is in charge of moving the paddle left.
     * The method checks whether the next x coordinate is out left edge or not
     * and moves it as it should.
     */
    public void moveLeft(){
        if (rectangle.getX()-5 >= borders.getX()+20) {
            rectangle.getUpperLeft().setX(rectangle.getX()-5);
         /**   region1.start().setX(region1.start().getX()-5);
            region1.end().setX(region1.end().getX()-5);
            region2.start().setX(region2.start().getX()-5);
            region2.end().setX(region2.end().getX()-5);
            region3.start().setX(region3.start().getX()-5);
            region3.end().setX(region3.end().getX()-5);
            region4.start().setX(region4.start().getX()-5);
            region4.end().setX(region4.end().getX()-5);
            region5.start().setX(region5.start().getX()-5);
            region5.end().setX(region5.end().getX()-5);*/
        }else {
            rectangle.getUpperLeft().setX(borders.getX()+20);
        }
    }

    /**
     * moveRight is in charge of moving the paddle right.
     * The method checks whether the next x coordinate is out right edge or not
     * and moves it as it should.
     */
    public void moveRight(){
        if (rectangle.getX()+rectangle.getWidth()+5 <= borders.getWidth()-20){
            rectangle.getUpperLeft().setX(rectangle.getX() + 5);
        }else {
            rectangle.getUpperLeft().setX(borders.getWidth()-rectangle.getWidth()-20);
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
