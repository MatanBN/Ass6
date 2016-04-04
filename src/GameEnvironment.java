import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matan on 03/04/2016.
 */
public class GameEnvironment {
    List<Object> collidables;

    public GameEnvironment() {
        collidables = new ArrayList<Object>();
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle r = ((Collidable) collidables.get(0)).getCollisionRectangle();
        CollisionInfo min = new CollisionInfo((Collidable) collidables.get(0),
                trajectory.closestIntersectionToStartOfLine(r));
        for (Object o: collidables) {
            r = ((Collidable) o).getCollisionRectangle();
            if (min.collisionPoint() == null) {
                min = new CollisionInfo((Collidable) o, trajectory.closestIntersectionToStartOfLine(r));
                continue;
            }
            CollisionInfo coll = new CollisionInfo((Collidable) o, trajectory.closestIntersectionToStartOfLine(r));
            if (coll.collisionPoint() == null)
                continue;
            if (coll.collisionPoint().distance(trajectory.start()) <
                    min.collisionPoint().distance(trajectory.start())) {
                min = coll;
            }
        }
        return min;
    }

    public void drawOn(DrawSurface d) {
        for (Object o: collidables) {
            ((Block) o).drawOn(d);
        }
    }


}