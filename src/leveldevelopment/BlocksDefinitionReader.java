package leveldevelopment;

import sprites.ColorSprite;
import sprites.ImageSprite;
import sprites.Sprite;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * BlocksDefinitionReader class reads a file that was given in the reader and creates a BlockFromSymbolsFactory
 * according to the file given.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public class BlocksDefinitionReader {
    /**
     * fromReader function reads a file given and creates a BlocksFromSymbolsFactory according to the input of the file.
     *
     * @param reader the file to read to get the block's info.
     * @return BlocksFromSymbolsFactory that was created.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader buffer = new BufferedReader(reader);
        ArrayList<String[]> fileInput = new ArrayList<String[]>();
        Map<String, BlockCreator> blockCreatorMap = new HashMap<String, BlockCreator>();
        Map<String, Integer> spaceCreatorMap = new HashMap<String, Integer>();
        Map<String, String> defaultMap = new HashMap<String, String>();

        // Read a line by line, create the default map and add the non default values to an array list.
        try {
            String s;
            while ((s = buffer.readLine()) != null) {
                // Skip comments and empty lines.
                if (s.equals("") || s.charAt(0) == '#') {
                    continue;
                }
                String[] info = s.split(" ");
                // Read the default values.
                if (info[0].equals("default")) {
                    readDefaults(info, defaultMap);
                } else {
                    fileInput.add(info);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read line from file");
        }

        // Create the block creators.
        for (int i = 0; i < fileInput.size(); ++i) {
            String[] info = fileInput.get(i);
            if (info[0].equals("bdef")) {
                Map<String, String> map = new HashMap<String, String>();
                map.putAll(defaultMap);
                BlockCreator blockFactory = getBlockCreator(info, map);
                blockCreatorMap.put(map.get("symbol"), blockFactory);
            } else if (info[0].equals("sdef")) {
                readSpaces(info, spaceCreatorMap);
            }
        }
        return new BlocksFromSymbolsFactory(spaceCreatorMap, blockCreatorMap);
    }

    /**
     * readSpaces method reads spaces information from the file.
     *
     * @param s        the line of the space information.
     * @param spaceMap the map of spaces to add the space to.
     */
    private static void readSpaces(String[] s, Map<String, Integer> spaceMap) {
        Map<String, String> readMap = new HashMap<String, String>();
        for (int i = 1; i < s.length; ++i) {
            String[] spaceInfo = s[i].split(":");
            if (spaceInfo.length >= 2) {
                readMap.put(spaceInfo[0], spaceInfo[1]);
            }
        }
        spaceMap.put(readMap.get("symbol"), Integer.parseInt(readMap.get("width")));
    }

    /**
     * readDefaults method reads the default values of the block and adds them to a map that will contain the values
     * for the block creator.
     *
     * @param info the line of the default values.
     * @param map  for the information of the default values.
     */
    private static void readDefaults(String[] info, Map<String, String> map) {
        for (int i = 1; i < info.length; ++i) {

            String[] attribute = info[i].split(":");
            if (attribute.length >= 2) {
                map.put(attribute[0], attribute[1]);
            }
        }
    }

    /**
     * getBlockCreator method reads the info for the block creator and adds it to the default values if needed.
     *
     * @param info the line of the block information.
     * @param map  the map for the information of the block.
     * @return a new BlockCreator according to the information given.
     */
    private static BlockCreator getBlockCreator(String[] info, Map<String, String> map) {
        BlockCreator blockCreator;
        // Add the new information to the default values given.
        for (int i = 1; i < info.length; ++i) {
            String[] attribute = info[i].split(":");
            if (attribute.length >= 2) {
                map.put(attribute[0], attribute[1]);
            }
        }

        // Get all the fillings for the block creator.
        ArrayList<Sprite> sprites = getFillings(map.entrySet());
        ColorParser colorParser = new ColorParser();
        // Creator a new block creator with frame color or without it.
        try {
            if (map.containsKey("stroke")) {
                blockCreator = new BlockFactory(Integer.parseInt(map.get("width")), Integer.parseInt(map.get("height")),
                        sprites, colorParser.colorFromString(map.get("stroke").split("\\(")[1]),
                        Integer.parseInt(map.get("hit_points")));
            } else {
                blockCreator = new BlockFactory(Integer.parseInt(map.get("width")), Integer.parseInt(map.get("height")),
                        sprites, null, Integer.parseInt(map.get("hit_points")));
            }
        } catch (Exception e) {
            throw new RuntimeException("Not enough details in block");
        }
        return blockCreator;
    }

    /**
     * getFillings method creates an array list of fillings for the block.
     *
     * @param setMap is a set that will contain all of the map's keys/values.
     * @return an arraylist with all of the fillings organized by their order.
     */
    private static ArrayList<Sprite> getFillings(Set<Map.Entry<String, String>> setMap) {
        ArrayList<Sprite> fillings = new ArrayList<Sprite>();
        // Find all the fill keys and add them to an arraylist of sprites.
        for (Map.Entry<String, String> currentKeyValue : setMap) {
            String fill = currentKeyValue.getKey();
            if (fill.contains("fill")) {
                String[] fillNum = fill.split("-");
                String value = currentKeyValue.getValue();
                String[] imageOrColor = value.split("\\(");
                if (fillNum.length == 1) {
                    fillings.add(0, getSprite(imageOrColor));
                } else if (fillNum.length > 1) {
                    Sprite sprite = getSprite(imageOrColor);
                    try {
                        fillings.add(Integer.parseInt(fillNum[1]) - 1, sprite);
                    } catch (IndexOutOfBoundsException e) {
                        fillings.add(sprite);
                    }
                }
            }
        }
        return fillings;
    }

    /**
     * getSprite method creates an image or color sprite according to the information given.
     *
     * @param imageOrColor contains information on the background of the block.
     * @return A sprite for the background of the block according to the information given.
     */
    private static Sprite getSprite(String[] imageOrColor) {
        Sprite sprite = null;
        if (imageOrColor[0].equals("image")) {
            ImageParser imageParse = new ImageParser();
            sprite = new ImageSprite(imageParse.imageFromString(imageOrColor[1]));
        } else if (imageOrColor[0].equals("color")) {
            ColorParser colorParser = new ColorParser();
            if (imageOrColor[1].equals("RGB")) {
                imageOrColor[1] = imageOrColor[2].substring(0, imageOrColor[2].length() - 2);
            }
            sprite = new ColorSprite(colorParser.colorFromString(imageOrColor[1]));
        }
        return sprite;
    }
}
