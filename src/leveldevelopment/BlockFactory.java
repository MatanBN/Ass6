package leveldevelopment;

import geometry.Rectangle;
import sprites.Background;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;

/**
 * BlockFactory class creates blocks according to the parameters given to it.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public class BlockFactory implements BlockCreator {
    private int width; // The width of the block to create.
    private int height; // The height of the block to create.
    private ArrayList<Sprite> fillers; // The fillers of the block to create.
    private Color strokeColor; // The frame color of the block to create.
    private int hitPoints; // The hit points of the block to create.

    /**
     * BlockFactory constructor creates a new block factory.
     *
     * @param width       the width of the block to create.
     * @param height      the height of the block to create.
     * @param fillers     the fillers of the block to create.
     * @param strokeColor the frame color of the block to create.
     * @param hitPoints   the hit points of the block to create.
     */
    public BlockFactory(int width, int height, ArrayList<Sprite> fillers, Color strokeColor, int hitPoints) {
        this.width = width;
        this.height = height;
        this.fillers = fillers;
        this.strokeColor = strokeColor;
        this.hitPoints = hitPoints;
    }

    /**
     * create Method creates a new block according to the parameters of the factory in the xpos and ypos coordinates.
     * @param xpos x coordinate to create the block.
     * @param ypos y coordinate to create the block.
     * @return the new block that was created.
     */
    @Override
    public Block create(int xpos, int ypos) {
        ArrayList<Sprite> newSprites = new ArrayList<Sprite>();
        for (Sprite c : fillers) {
            Background cSprite = (Background) c;
            newSprites.add(cSprite.AddRectangle(new Rectangle(xpos, ypos, width, height)));
        }
        Block block = new Block(xpos, ypos, width, height, strokeColor, newSprites);
        block.setHitsNumber(hitPoints);
        return block;
    }

}
