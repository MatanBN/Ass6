package leveldevelopment;

import geometry.Rectangle;
import sprites.Background;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by Matan on 6/9/2016.
 */
public class BlockFactory implements BlockCreator {
    private int width;
    private int height;
    private ArrayList<Sprite> fillers;
    private Color strokeColor;
    private int hitPoints;

    public BlockFactory(int width, int height, ArrayList<Sprite> fillers, Color strokeColor, int hitPoints) {
        this.width = width;
        this.height = height;
        this.fillers = fillers;
        this.strokeColor = strokeColor;
        this.hitPoints = hitPoints;
    }


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

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
