/**
 * The Collidable holds 2 statements of any collidable object.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public interface Collidable {
    // Return the "collision shape" of the object.
    public Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    public Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
