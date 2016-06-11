package leveldevelopment;

import sprites.ColorSprite;
import sprites.ImageSprite;
import sprites.Sprite;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Matan on 6/9/2016.
 */
public class BlocksDefinitionReader {
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader buffer = new BufferedReader(reader);
        Map<String, BlockCreator> blockCreatorMap = new HashMap<String, BlockCreator>();
        Map<String, Integer> spaceCreatorMap = new HashMap<String, Integer>();
        Map<String, String> defaultMap = new HashMap<String, String>();
        try {
            String s;
            while ((s = buffer.readLine()) != null) {
                if (s.equals("") || s.charAt(0) == '#') {
                    continue;
                }
                String[] info = s.split(" ");
                if (info[0].equals("default")) {
                    readDefaults(info, defaultMap);
                } else if (info[0].equals("bdef")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.putAll(defaultMap);
                    BlockCreator blockFactory = getBlockCreator(info, map);
                    blockCreatorMap.put(map.get("symbol"), blockFactory);
                } else {
                    readSpaces(info, spaceCreatorMap);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read line from file");
        }
        return new BlocksFromSymbolsFactory(spaceCreatorMap, blockCreatorMap);
    }

    private static void readSpaces(String[] s, Map<String, Integer> spaceMap) {
        Map<String, String> readMap = new HashMap<String, String>();
        for (int i = 1; i < s.length; ++i) {
            String[] spaceInfo = s[i].split(":");
            readMap.put(spaceInfo[0], spaceInfo[1]);
        }
        spaceMap.put(readMap.get("symbol"), Integer.parseInt(readMap.get("width")));
    }

    private static void readDefaults(String[] info, Map<String, String> map) {
        for (int i = 1; i < info.length; ++i) {
            String[] attribute = info[i].split(":");
            map.put(attribute[0], attribute[1]);
        }
    }


    private static BlockCreator getBlockCreator(String[] info, Map<String, String> map) {
        BlockCreator blockCreator;
        for (int i = 1; i < info.length; ++i) {
            String[] attribute = info[i].split(":");
            map.put(attribute[0], attribute[1]);
        }

        ArrayList<Sprite> sprites = getFillings(map.entrySet());
        ColorParser colorParser = new ColorParser();
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

    private static ArrayList<Sprite> getFillings(Set<Map.Entry<String, String>> setMap) {
        ArrayList<Sprite> fillings = new ArrayList<Sprite>();
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
