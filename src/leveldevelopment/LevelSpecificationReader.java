package leveldevelopment;

import game.Velocity;
import gamelevels.Level;
import gamelevels.LevelInformation;
import geometry.Rectangle;
import sprites.Block;
import sprites.ColorSprite;
import sprites.ImageSprite;
import sprites.Sprite;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matan on 6/2/2016.
 */
public class LevelSpecificationReader {
    private Rectangle frame;
    private BlocksFromSymbolsFactory blockFactory;
    private int rowHeight;
    private int startX;
    private int startY;

    public LevelSpecificationReader(Rectangle frame) {
        this.frame = frame;
    }

    public List<LevelInformation> fromReader(java.io.Reader reader) {
        BufferedReader buffer = new BufferedReader(reader);
        List<LevelInformation> myLevels = new ArrayList<LevelInformation>();
        try {
            String s;
            while ((s = buffer.readLine()) != null) {
                if (s.equals("START_LEVEL")) {
                    myLevels.add(getLevel(buffer));
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read line from file");
        }

        return myLevels;
    }

    private LevelInformation getLevel(BufferedReader buffer) {
        Level newLevel = new Level();

        try {
            String s;
            do {
                s = buffer.readLine();
                if (s.equals("START_BLOCKS")) {
                    newLevel.setLevelBlocks(createListOfBlocks(buffer));
                } else if (s.equals("") || s.charAt(0) == '#') {
                    continue;
                } else {
                    String[] info = s.split(":");
                    setAttribute((Level) newLevel, info);
                }

            } while (!s.equals("END_LEVEL"));
        } catch (IOException e) {
            System.out.println("Unable to read line from file");
        }
        return newLevel;
    }

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
                ;
                break;
            case "blocks_start_y":
                this.startY = Integer.parseInt(info[1]);
                ;
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

    private List<Block> createListOfBlocks(BufferedReader reader) {
        int currentX = startX;
        int currentY = startY;
        List<Block> levelBlocks = new ArrayList<Block>();
        try {
            String s;
            while (!(s = reader.readLine()).equals("END_BLOCKS")) {
                if (s.equals("") || s.charAt(0) == '#') {
                    continue;
                }
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

    private void setBlockFactory(String fileName) {
        BufferedReader blockBuff = null;
        try {
            blockBuff = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            blockFactory = BlocksDefinitionReader.fromReader(blockBuff);
        } catch (IOException e) {
            System.out.println("Error loading block file");
        } finally {
            if (blockBuff != null) {
                try {
                    blockBuff.close();
                } catch (IOException e) {
                    System.out.println("Error closing block file");
                }
            }
        }
    }

    private List<Velocity> getListOfVelocities(String velocities) {
        List<Velocity> myVelocities = new ArrayList<Velocity>();
        for (String velocity : velocities.split("\\s+")) {
            String xy[] = velocity.split(",");
            myVelocities.add(new Velocity(Double.parseDouble(xy[0]), Double.parseDouble(xy[1])));
        }
        return myVelocities;
    }

    private Sprite getBackground(String backgroundInfo) {
        String[] info = backgroundInfo.split("\\(");
        Sprite background = null;
        if (info[0].equals("image")) {
            ImageParser imageParser = new ImageParser();
            background = new ImageSprite(this.frame, imageParser.imageFromString(info[1]));
        }
        if (info[0].equals("color")) {
            String color;
            if (info[1].equals("RGB")) {
                color = info[2].substring(0, info[2].length() - 2);
            } else {
                color = info[1].substring(0, info[1].length() - 1);
            }
            ColorParser colorParser = new ColorParser();
            background = new ColorSprite(this.frame, colorParser.colorFromString(color));
        }
        return background;
    }


}
