package leveldevelopment;

import sprites.Block;

import java.util.Map;
/**
 * BlockFromSymbolsFactory class has 2 hash maps that each key will give us a block creator or a space width.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths; // The map for the space width.
    private Map<String, BlockCreator> blockCreators; // The map for the block creator.

    /**
     * BlocksFromSymbolsFactory Constructor.
     *
     * @param spacerWidths  the map for the space width.
     * @param blockCreators the map for the block creators.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * isSpaceSymbol method checks if a given symbol for a space exists in this factory.
     * @param s the symbol to check.
     * @return true it exists in the factory, false otherwise.
     */
    public boolean isSpaceSymbol(String s) {
        if (spacerWidths.get(s) == null) {
            return false;
        }
        return true;
    }

    /**
     * isBlockSymbol method checks if a given symbol for a block factory exists in this factory.
     * @param s the symbol to check.
     * @return true it exists in the factory, false otherwise.
     */
    public boolean isBlockSymbol(String s) {
        if (blockCreators.get(s) == null) {
            return false;
        }
        return true;
    }

    /**
     * getBlock returns the block according to the block factory symbol.
     * @param s the block factory symbol.
     * @param xpos the x coordinate of the new block.
     * @param ypos the y coordinate of the new block.
     * @return the new block created by the block factory.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * getSpaceWidth returns the space according to the space symbol.
     * @param s the space symbol.
     * @return the space width of that symbol.
     */
    public int getSpaceWidth(String s) {
        return spacerWidths.get(s);
    }
}
