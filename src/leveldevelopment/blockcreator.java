package leveldevelopment;

import sprites.Block;

/**
 * BlockCreator is an interface of a factory-object that is used for creating blocks.
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     *
     * @param xpos x coordinate to create the block.
     * @param ypos y coordinate to create the block.
     * @return the new block that was created.
     */
    Block create(int xpos, int ypos);
}
