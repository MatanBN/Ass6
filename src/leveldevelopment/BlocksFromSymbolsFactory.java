package leveldevelopment;

import sprites.Block;

import java.util.Map;

/**
 * Created by Matan on 6/9/2016.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    // returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        if (spacerWidths.get(s) == null) {
            return false;
        }
        return true;
    }

    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        if (blockCreators.get(s) == null) {
            return false;
        }
        return true;
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
        return blockCreators.get(s).create(xpos, ypos);
    }

    // Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s) {
        return spacerWidths.get(s);
    }
}
