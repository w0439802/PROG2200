package org.lwjglb.engine.items;

import org.joml.Quaternionf;
import org.lwjglb.engine.graph.Mesh;
import java.util.Random;

public abstract class Vehicle extends GameItem {
    Random r = new Random();

    //    public Vehicle(Mesh[] cubeMesh) {
//        super(cubeMesh);
//        this.setPosition(11.00f, 11.000f, 15 * r.nextFloat());
//        this.setVelocity(0.002f, 0.001f, 0.003f);
//        this.setRotation(new Quaternionf(2.6f, 4.7f, 3.9f, 0.0f));
//        this.setRotationVel(new Quaternionf(0.006f, 0.007f, 0.0009f, 0.0f));
//
//        this.setMeshes(cubeMesh);
//    }
    protected void Vehicle() {
    }

    abstract public void setDefaultMesh();
    abstract public void setDefaultPos();
    public float getFloat(float min, float max) {
        Float newFloat = r.nextFloat() * (max -min) - min;
        return newFloat;
    }

}
