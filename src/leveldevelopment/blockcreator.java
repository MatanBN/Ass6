package leveldevelopment;

import sprites.Block;

/**
 * BlockCreator interface is an interface for block factory.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public interface BlockCreator {
    /**
     * Create a new block in xpos and ypos coordinates.
     *
     * @param xpos x coordinate to create the block.
     * @param ypos y coordinate to create the block.
     * @return the new block that was created.
     */
    Block create(int xpos, int ypos);
}
