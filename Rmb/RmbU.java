package com.rombosaur.???.Rmb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.flixel.FlxCamera;
import org.flixel.FlxGroup;
import org.flixel.FlxTilemap;


/**
 * Static method UTILITIES
 */
public class RmbU {

    /**
     * Returns an Array<FlxCamera> with the arg camera in it
     *
     * @param cam
     * @return
     */
    public static Array<FlxCamera> createCamArray(FlxCamera cam){
        Array<FlxCamera> arr = new Array<FlxCamera>();
        arr.add(cam);

        return arr;
    }

    /**
     * Returns an Array<FlxCamera> with the arg cameras in it
     *
     * @param cam1
     * @param cam2
     * @return
     */
    public static Array<FlxCamera> createCamArray(FlxCamera cam1, FlxCamera cam2){
        Array<FlxCamera> arr = new Array<FlxCamera>();
        arr.add(cam1);
        arr.add(cam2);

        return arr;
    }

    /**
     * Loads the specified map into the specified group.
     * Each layer of the map is created as a single FlxTilemap.
     * These layers are accessible via name on the returned array
     *
     * @param jsonMapPath   Path to the JSON tiled map
     * @param tilesetPath   Path to the tileset used in the map
     * @param tileSize  Asumes square tiles
     * @param mapWidthInTiles   Width of map in tiles
     * @param x X coordinate
     * @param y Y coordinate
     * @param dst Destination group, where all the layers are going to be added
     * @param objParser Parser that will handle tiled object layers
     */
    public static ArrayMap<String, FlxTilemap> loadTiledJsonMap(String jsonMapPath, String tilesetPath, int tileSize, int mapWidthInTiles, float x, float y, FlxGroup dst, RmbTiledObjParser objParser){
        JsonValue value = new JsonReader().parse(Gdx.files.internal(jsonMapPath));
        JsonValue layer = value.get("layers").child();
        int cantLayers  = value.get("layers").size;

        ArrayMap<String, FlxTilemap> layersMap = new ArrayMap<String, FlxTilemap>();

        for(int i=0; i<cantLayers; i++){
            String curLayerName = layer.getString("name");
            System.out.println("Reading layer "+curLayerName);

            if(layer.has("data")) {
                int[] data = layer.get("data").asIntArray();

                FlxTilemap t = new FlxTilemap();
                t.x = x;
                t.y = y;

                t.loadMap(FlxTilemap.arrayToCSV(data, mapWidthInTiles),
                        tilesetPath,
                        tileSize, tileSize,
                        0, 1
                );

                layersMap.put(curLayerName, t);
                dst.add(t);
            }

            if(layer.has("objects") && objParser != null){
                objParser.parseLayer(layer.get("objects"));
            }

            try {
                layer = layer.next();
            } catch (NullPointerException e) {	break;	} // No more layers left
        }

        return layersMap;
    }
}
