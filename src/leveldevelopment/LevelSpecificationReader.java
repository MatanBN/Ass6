package leveldevelopment;

import game.Velocity;
import gamelevels.Level;
import gamelevels.LevelInformation;
import geometry.Rectangle;
import sprites.Block;
import sprites.ColorSprite;
import sprites.ImageSprite;
import sprites.Sprite;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

/**
 * LevelSpecificationReader class reads a file and creates a list of level information of all the levels in the file.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public class LevelSpecificationReader {
    private Rectangle frame; // The borders of the gui.
    private BlocksFromSymbolsFactory blockFactory; // The blockFactory that we will create the blocks with.
    private int rowHeight; // The height of each row of blocks.
    private int startX; // The initial x coordinates of the blocks.
    private int startY; // The initial y coordinates of the blocks.

    /**
     * LevelSpecificationReader constructor.
     *
     * @param frame the borders of the gui.
     */
    public LevelSpecificationReader(Rectangle frame) {
        this.frame = frame;
        rowHeight = -1;
        startX = -1;
        startY = -1;
    }

    /**
     * fromReader method reads the file given and creates a list of level information.
     *
     * @param reader the file to read.
     * @return a list of level information for the game.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        BufferedReader buffer = new BufferedReader(reader);
        List<LevelInformation> myLevels = new ArrayList<LevelInformation>();
        // Read each line and create a new level each time we get a line with START_LEVEL.
        try {
            String s;
            while ((s = buffer.readLine()) != null) {
                if (s.equals("START_LEVEL")) {
                    Level myLevel = getLevel(buffer);
                    if (myLevel.checkLevel()) {
                        myLevels.add(myLevel);
                    } else {
                        throw new RuntimeException("Not enough level details");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read line from file");
        }
        return myLevels;
    }

    /**
     * getLevel method creates a new level according to the information of the file.
     *
     * @param buffer the file to read.
     * @return the new level that was created.
     */
    private Level getLevel(BufferedReader buffer) {
        Level newLevel = new Level();
        // Read line by line until we get a line that contains END_LEVEL.
        try {
            String s;
            do {
                s = buffer.readLine();
                // Add the blocks to the game.
                if (s.equals("START_BLOCKS")) {
                    newLevel.setLevelBlocks(createListOfBlocks(buffer));
                } else if (s.equals("") || s.charAt(0) == '#') { // Skip empty or comment lines.
                    continue;
                } else {
                    String[] info = s.split(":");
                    setAttribute(newLevel, info);
                }

            } while (!s.equals("END_LEVEL"));
        } catch (IOException e) {
            System.out.println("Unable to read line from file");
        }
        return newLevel;
    }

    /**
     * setAttribute method sets the attribute of the level according to the information given.
     *
     * @param newLevel the level that was created.
     * @param info     the information for the level.
     */
    private void setAttribute(Level newLevel, String[] info) {
        switch (info[0]) {
            case "level_name":
                newLevel.setLevelName(info[1]);
                break;
            case "ball_velocities":
                newLevel.setLevelsVelocities(getListOfVelocities(info[1]));
                break;
            case "background":
                newLevel.setBackground(getBackground(info[1]));
                break;
            case "paddle_speed":
                newLevel.setPaddSpeed(Integer.parseInt(info[1]));
                break;
            case "paddle_width":
                newLevel.setPaddWidth(Integer.parseInt(info[1]));
                break;
            case "block_definitions":
                setBlockFactory(info[1]);
                break;
            case "blocks_start_x":
                this.startX = Integer.parseInt(info[1]);
                break;
            case "blocks_start_y":
                this.startY = Integer.parseInt(info[1]);
                break;
            case "row_height":
                this.rowHeight = Integer.parseInt(info[1]);
                break;
            case "num_blocks":
                newLevel.setNumOfBlocksToRemove(Integer.parseInt(info[1]));
                break;
            default:
                break;
        }
    }

    /**
     * createListOfBlocks creates the blocks from the file given and from the BlocksFromSymbolsFactory.
     *
     * @param reader the file to read.
     * @return a list of blocks for the level.
     */
    private List<Block> createListOfBlocks(BufferedReader reader) {
        if (startX < 0 || startY < 0 || rowHeight < 0 || blockFactory == null) {
            throw new RuntimeException("Error in details of level");
        }
        int currentX = startX;
        int currentY = startY;
        List<Block> levelBlocks = new ArrayList<Block>();
        // Read line by line until we get END_BLOCKS line and add each block/space according to its symbol.
        try {
            String s;
            while (!(s = reader.readLine()).equals("END_BLOCKS")) {
                // Skip comment and empty lines.
                if (s.equals("") || s.charAt(0) == '#') {
                    continue;
                }
                // Add a row of blocks/spaces.
                for (int i = 0; i < s.length(); ++i) {
                    String currentChar = s.substring(i, i + 1);
                    if (blockFactory.isSpaceSymbol(currentChar)) {
                        currentX += blockFactory.getSpaceWidth(currentChar);
                    } else if (blockFactory.isBlockSymbol(currentChar)) {
                        Block b = blockFactory.getBlock(currentChar, currentX, currentY);
                        levelBlocks.add(b);
                        currentX += b.getRectangle().getWidth();
                    }
                }
                currentY += rowHeight;
                currentX = startX;
            }
        } catch (IOException e) {
            System.out.println("Error reading level file");
        }
        return levelBlocks;
    }

    /**
     * setBlockFactory calls fromReader method of BlocksDefinitionReader to add the BlocksFromSymbolsFactory.
     *
     * @param fileName the block definition file to read.
     */
    private void setBlockFactory(String fileName) {
        BufferedReader blockBuff = null;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream
                (fileName);
        blockBuff = new BufferedReader(new InputStreamReader(is));
        blockFactory = BlocksDefinitionReader.fromReader(blockBuff);
        try {
            blockBuff.close();
        } catch (IOException e) {
            System.out.println("Error closing block file");
        }
    }

    /**
     * getListOfVelocities creates a list of velocities for the balls of the level.
     *
     * @param velocities information for the velocities.
     * @return a list of velocities.
     */
    private List<Velocity> getListOfVelocities(String velocities) {
        List<Velocity> myVelocities = new ArrayList<Velocity>();
        for (String velocity : velocities.split("\\s+")) {
            String xy[] = velocity.split(",");
            myVelocities.add(new Velocity(Double.parseDouble(xy[0]), Double.parseDouble(xy[1])));
        }
        return myVelocities;
    }

    /**
     * getBackground creates a background for the level.
     *
     * @param backgroundInfo information for the background.
     * @return the background created.
     */
    private Sprite getBackground(String backgroundInfo) {
        String[] info = backgroundInfo.split("\\(");
        Sprite background = null;
        if (info[0].equals("image")) {
            ImageParser imageParser = new ImageParser();
            background = new ImageSprite(this.frame, imageParser.imageFromString(info[1]));
        }
        if (info[0].equals("color")) {
            String color = info[1];
            if (info[1].equals("RGB")) {
                color = info[2].substring(0, info[2].length() - 2);
            }
            ColorParser colorParser = new ColorParser();
            background = new ColorSprite(this.frame, colorParser.colorFromString(color));
        }
        return background;
    }
}
