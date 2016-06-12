package leveldevelopment;

import sprites.Block;

/**
 * Created by Matan on 6/9/2016.
 */
public interface BlockCreator {
    // Create a block at the specified location.
    Block create(int xpos, int ypos);
}
