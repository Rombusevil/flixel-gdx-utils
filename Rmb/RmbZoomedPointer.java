package com.rombosaur.???.Rmb;

import org.flixel.FlxG;
import org.flixel.FlxPoint;

public class RmbZoomedPointer {
    private RmbZoomedCamera screenCam;
    private RmbZoomedCamera worldCam;
    private FlxPoint _point;

    /**
     * With this class you can get accurate mouse pointer coordinates if using RmbZoomedCameras.
     * Use this clase in place of FlxG.mouse.
     *
     * @param screenCam Hud camera, where the cursor is portrayed
     * @param worldCam  World camera, where hero is portrayed
     */
    public RmbZoomedPointer(RmbZoomedCamera screenCam, RmbZoomedCamera worldCam){
        this._point = new FlxPoint();
        this.screenCam = screenCam;
        this.worldCam = worldCam;
    }

    /**
     * Returns the coordinates of the mouse applying zoomed offset on the screen camera.
     * This camera must be the one displaying the cursor
     *
     * @return
     */
    public FlxPoint getScreenPosition(){
        return screenCam.xxyy(FlxG.mouse.getScreenPosition(screenCam, _point));
    }

    /**
     * Returns the coordinates of the mouse applying zoomed offset on the world camera
     *
     * @return
     */
    public FlxPoint getWorldPosition(){
        return worldCam.xxyy(FlxG.mouse.getWorldPosition(worldCam, _point));
    }
}
