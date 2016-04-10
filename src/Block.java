
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Block is a Collidable and a Sprie object that can block moving objects.
 * The class has methods to draw the block, and a method hit which will change the velocity of the object that hits
 * the block.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Block implements Collidable, Sprite {
    private Rectangle rectangle; // The rectangle shape of the block.
    private int hitsNumber; // The number of hits of the block.

    /**
     * Block creates a new rectangle block by a given rectangle.
     * @param r is a given rectangle.
     */
    public Block(Rectangle r) {
        this.rectangle = r;
    }

    /**
     * Block creates a new rectangle block by it's width and height.
     * @param width  is the rectangle's width.
     * @param height is the rectangle's height.
     * @param c the color of the block/rectangle.
     */
    public Block(int width, int height, Color c) {
        this.rectangle = new Rectangle(width, height, c);
    }

    /**
     * Block creates a new rectangle block by it's left corner coordinates,
     * width and height.
     * @param x is the x coordinate of left corner.
     * @param y is the y coordinate of left corner.
     * @param width is the rectangle's width.
     * @param height is the rectangle's height.
     * @param c the color of the block/rectangle.
     */
    public Block(int x, int y, int width, int height, Color c) {
        this.rectangle = new Rectangle(x, y, width, height, c);
    }

    /**
     * drawOn method draws the block and the block's number of hits on a given
     * surface.
     * @param d is the surface to draw the blocks on.
     */
    public void drawOn(DrawSurface d) {
        // Draw the rectangle shape of the block.
        rectangle.drawOn(d);
        // Draw the numbers of hits of the block.
        String s;
        if (this.hitsNumber > 0) {
            s = Integer.toString(this.hitsNumber);
        } else {
            s = "X";
        }
        d.drawText(this.rectangle.getX() + this.rectangle.getWidth() / 2,
                this.rectangle.getY() + this.rectangle.getHeight() / 2, s, 10);
    }

    /**
     * getCollisionRectangle return the block's shape.
     * @return the given rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit return the new velocity after the hit based on force the object
     * inflicted on us.
     * @param collisionPoint is the collision point of an object with the block.
     * @param currentVelocity is the current velocity of the object that will collide with the block.
     * @return the new velocity after the hit.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (this.hitsNumber > 0) {
            --this.hitsNumber;
        }
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // Checks whether the collision point is on the right or left edges.
        if (checkCollisionSide(collisionPoint, rectangle.getLeftEdge())) {
            newVelocity.setDx(-currentVelocity.getDx());
        } else if (checkCollisionSide(collisionPoint, rectangle.getRightEdge())) {
            newVelocity.setDx(-currentVelocity.getDx());
        }
        // Checks whether the collision point is on the top or bottom edges.
        if (checkCollisionSide(collisionPoint, rectangle.getTopEdge())) {
            newVelocity.setDy(-currentVelocity.getDy());
        } else if (checkCollisionSide(collisionPoint, rectangle.getBottomEdge())) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * checkCollisionSide gets a collision point and an edge and checks. whether
     * the collision point is inside the given edge.
     * @param collisionPoint is the collision point of the ball with the object.
     * @param edge is a given edge of an object.
     * @return true if the collision point is inside the edge and false otherwise.
     */
    public boolean checkCollisionSide(Point collisionPoint, Line edge) {
        if (edge.inSegment(collisionPoint.getX(), collisionPoint.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Current doesn't do anything.
     */
    public void timePassed() {

    }

    /**
     * addToGame is in charge of adding the block as a Sprite and as a
     * Collidable to the game's suitable lists.
     * @param g is the game object we created.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * getHitsNumber returns block's number of hits.
     * @return the block's number of hits.
     */
    public int getHitsNumber() {
        return hitsNumber;
    }

    /**
     * setHitsNumber sets the block's number of hits.
     * @param hits the adjusted number of hits.
     */
    public void setHitsNumber(int hits) {
        hitsNumber = hits;
    }

}
