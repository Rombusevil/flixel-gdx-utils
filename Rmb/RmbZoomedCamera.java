package com.rombosaur.???.Rmb;

import org.flixel.FlxCamera;
import org.flixel.FlxG;
import org.flixel.FlxPoint;

/**
 * Extends FlxCamera and maintains an offset variable.
 *   FlxPoint zoomOffset;
 * This variable holds the camera offset (always negative) that the screen coordinates
 * need to apply in order to get the expected results.
 *
 * Use this class instead of a FlxCamera when you need to apply zoom to it.
 */
public class RmbZoomedCamera extends FlxCamera {
    public FlxPoint zoomOffset;

    public RmbZoomedCamera(int x, int y, int width, int height, float zoom, int scaleMode) {
        super(x, y, width, height, zoom, scaleMode);
        this.setZoom(zoom);
    }

    public RmbZoomedCamera(int x, int y, int width, int height, float zoom) {
        super(x, y, width, height, 1);
        this.setZoom(zoom);
    }

    public RmbZoomedCamera(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    @Override
    public void setZoom(float zoom) {
        super.setZoom(zoom);

        if(this.zoomOffset == null){
            this.zoomOffset = new FlxPoint();
        }

        this.zoomOffset.x = (((this.width/zoom) /2)  - this.width /2);
        this.zoomOffset.y = (((this.height/zoom)/2)  - this.height/2);

        if(FlxG.debug) System.out.println("Creating zoomOffset: "+zoomOffset.x+", "+zoomOffset.y);
    }

    /**
     * Applies camera offset to given FlxPoint
     * @param raw
     * @return
     */
    public FlxPoint xxyy(FlxPoint raw){
        raw.x = raw.x + (-1* this.zoomOffset.x);
        raw.y = raw.y + (-1* this.zoomOffset.y);

        return raw;
    }

    /**
     * Applies camera offset to an X coordinate
     *
     * @param x
     * @return
     */
    public float xx(float x){
        return x+(-1* this.zoomOffset.x);
    }

    /**
     * Applies camera offset to an Y coordinate
     * @param y
     * @return
     */
    public float yy(float y){
        return y+(-1* this.zoomOffset.y);
    }
}
