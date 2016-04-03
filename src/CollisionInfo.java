/**
 * Created by Matan on 03/04/2016.
 */
public class CollisionInfo {
    Collidable myCollision;
    Point collisionPoint;

    public CollisionInfo(Collidable myCollision, Point collisionPoint) {
        this.myCollision = myCollision;
        this.collisionPoint = collisionPoint;
    }
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return collisionPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return (Collidable) myCollision;

    }

}
