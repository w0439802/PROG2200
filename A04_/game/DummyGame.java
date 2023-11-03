package org.lwjglb.game;

import org.joml.*;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjglb.engine.IGameLogic;
import org.lwjglb.engine.MouseInput;
import org.lwjglb.engine.Scene;
import org.lwjglb.engine.SceneLight;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.graph.Camera;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.graph.Renderer;
import org.lwjglb.engine.graph.lights.DirectionalLight;
import org.lwjglb.engine.graph.lights.PointLight;
import org.lwjglb.engine.graph.weather.Fog;
import org.lwjglb.engine.items.Car;
import org.lwjglb.engine.items.GameItem;
import org.lwjglb.engine.items.SkyBox;
import org.lwjglb.engine.items.Vehicle;
import org.lwjglb.engine.items.Plane;
import org.lwjglb.engine.loaders.assimp.StaticMeshesLoader;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;


/**
 * Russ <RS> changed this game code to put a bunch
 * of moving GameItems on the screen.
 * <p>
 * To understand what's going on, do these steps:
 * - look at IGameLogic ... the game has several key entry points...understand those.
 * <p>
 * - Follow the use of "camera"  the camera is how you see the scene.  Easiest way to
 * to understand it is put the camera away a bit and pointing back towards the scene, and leave it alone.
 * <p>
 * - Follow the creation of a mesh, being added to the scene.  You can make your own mesh with Blender.
 * <p>
 * - Blender: save your mesh as an OBJ file with cube projection including Normals,
 * UVs, Materials, and Triangulate Faces.
 * <p>
 * - public void input(Window window, MouseInput mouseInput) => process keystrokes sent to the Graphics window.
 * <p>
 * - public void update(float interval, MouseInput mouseInput, Window window) called many times per second.  Use
 * this method for synced game logic. Processing within events is sloppy, and pron to threading
 * errors (and done a lot in this sample).
 */
public class DummyGame implements IGameLogic {

    private static final float MOUSE_SENSITIVITY = 0.2f;

    private final Vector3f cameraInc;

    private final Renderer renderer;

    private final Camera camera;

    private Scene scene;

    private static final float CAMERA_POS_STEP = 0.40f;

    private float angleInc;

    private float lightAngle;

    private boolean firstTime;

    private boolean sceneChanged;

    private Vector3f pointLightPos;

    private GameItem t;

    private GameItem terrain;

    ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public DummyGame() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
        angleInc = 0;
        lightAngle = 90;
        firstTime = true;
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);

        scene = new Scene();

        /// Make a mesh using blender (or whatever utility),
        /// save as an OBJ file with cube projection including Normals,
        /// UVs, Materials, and Triangulate Faces.  The Mesh can then be
        /// loaded into this game, with position, velocity, rotation, rotation velocity and scale
        /// Also note that the same mesh can be loaded more than once with different parameters, say
        /// a different scale.
        /// The mesh alone can't move...so we make it a GameItem, which can move/rotate/scale
        /// Then we add all the GameItems to the "scene"
        Mesh[] houseMesh = StaticMeshesLoader.load("src/main/resources/models/house/house.obj", "src/main/resources/models/house");
        GameItem house = new GameItem(houseMesh);
        house.setPosition(0.0f, 0.0f, 3.0f);
        house.setScale(0.150f);
        t = house;

        Mesh[] terrainMesh = StaticMeshesLoader.load("src/main/resources/models/terrain/terrain.obj", "src/main/resources/models/terrain");
        terrain = new GameItem(terrainMesh);
        terrain.setPosition(0.00f, -15.000f, 0.000f); // TODO set background to -15.000
        terrain.setScale(100.0f);

        //scene.setGameItems(new GameItem[]{house, cube, russ_shape_01, russ_shape_02, russ_shape_03, terrain});
        scene.setGameItems(new GameItem[]{house, terrain});

        // populate the vehicles arraylist
        vehicles.add(new Car());
        vehicles.add(new Car());
//        vehicles.add(new Car());
        vehicles.add(new Plane());
        vehicles.add(new Plane());

        // add all the vehicles
        for (Vehicle v: vehicles) {
            scene.addGameItem(v);
        }


        // Shadows
        scene.setRenderShadows(false); // TODO change to false - Does it help with performance?

        // Fog
//        Vector3f fogColour = new Vector3f(0.5f, 0.5f, 0.5f);
//        scene.setFog(new Fog(true, fogColour, 0.02f));

        // Setup  SkyBox
        float skyBoxScale = 100.0f;
        SkyBox skyBox = new SkyBox("src/main/resources/models/skybox.obj", new Vector4f(0.65f, 0.65f, 0.65f, 1.0f));
        skyBox.setScale(skyBoxScale);
        scene.setSkyBox(skyBox);

        // Setup Lights
        setupLights();
        resetCamera();

//        // Set camera position and rotation to look back at our scene
//        camera.setPosition(-17.0f, 17.0f, -30.0f);
//        camera.setRotation(20.0f, 140.0f, 0.0f);

    }

    private void resetCamera() {
        // Set camera position and rotation to look back at our scene
        camera.setPosition(-17.0f, 17.0f, -30.0f);
        camera.setRotation(20.0f, 140.0f, 0.0f);
    }


    private void setupLights() {
        SceneLight sceneLight = new SceneLight();
        scene.setSceneLight(sceneLight);

        // Ambient Light
        sceneLight.setAmbientLight(new Vector3f(0.3f, 0.3f, 0.3f));
        sceneLight.setSkyBoxLight(new Vector3f(1.0f, 1.0f, 1.0f));

        // Directional Light
        float lightIntensity = 1.0f;
        Vector3f lightDirection = new Vector3f(0, 1, 1);
        DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), lightDirection, lightIntensity);
        sceneLight.setDirectionalLight(directionalLight);

        pointLightPos = new Vector3f(0.0f, 25.0f, 0.0f);
        Vector3f pointLightColour = new Vector3f(0.0f, 1.0f, 0.0f);
        PointLight.Attenuation attenuation = new PointLight.Attenuation(1, 0.0f, 0);
        PointLight pointLight = new PointLight(pointLightColour, pointLightPos, lightIntensity, attenuation);
        sceneLight.setPointLightList(new PointLight[]{pointLight});
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        sceneChanged = false;
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            sceneChanged = true;
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            sceneChanged = true;
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            sceneChanged = true;
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            sceneChanged = true;
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            sceneChanged = true;
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            sceneChanged = true;
            cameraInc.y = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            sceneChanged = true;
            angleInc -= 0.05f;
        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            sceneChanged = true;
            angleInc += 0.05f;
        } else {
            angleInc = 0;
        }
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            sceneChanged = true;
            pointLightPos.y += 0.5f;
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            sceneChanged = true;
            pointLightPos.y -= 0.5f;
        }
        if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            sceneChanged = true;
            this.addMeshOnScreen();
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            sceneChanged = true;
            this.removeAll = true;
        }
        if (window.isKeyPressed(GLFW_KEY_R)) {
            System.out.println("R is pressed!");
            resetCamera();
        }
    }

    java.util.Random r = new java.util.Random();

    @Override
    public void update(float interval, MouseInput mouseInput, Window window) {

        // Since we move gameItems in the background (with their own
        // thread, all the time, so cause the lighting/shadows to be recomputed
        sceneChanged = true;

        // Clear screen?
        if (GameGUI.getClearCommand()) {
            removeAll = true;
        }

        // If reset, set this gameItem back to a location.
        if (GameGUI.getResetCommand()) {
            t.setPosition(11.00f, 11.000f, 15 * r.nextFloat());
            t.setVelocity(0.002f, 0.001f, 0.003f);
            t.setRotation(new Quaternionf(2.6f, 4.7f, 3.9f, 0.0f));
            t.setRotationVel(new Quaternionf(0.006f, 0.007f, 0.0009f, 0.0f));
        }

        // If adding, add a gameItem
        if (GameGUI.getAddCommand()) {
            addMeshOnScreen();
        }

        if (GameGUI.getAddPlaneCommand()) {
            try {
                Mesh[] planeMesh = StaticMeshesLoader.load("src/main/resources/models/russ/toyPlane.obj", "src/main/resources/models/");
                GameItem shape = new GameItem(planeMesh);
                shape.setScale(1f);  //house needs to be shrunk
                shape.setPosition(5.00f * r.nextFloat(), 5.000f * r.nextFloat(), 15 * r.nextFloat());
                shape.setVelocity(0.05f * r.nextFloat(), 0.04f * r.nextFloat(), 0.03f * r.nextFloat());
                shape.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
                scene.addGameItem(shape);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (GameGUI.getAddCarCommand()) {
            try {
                Mesh[] carMesh = StaticMeshesLoader.load("src/main/resources/models/Chevrolet_Camaro_SS_Low.obj", "src/main/resources/models/");
                GameItem shape = new GameItem(carMesh);
                shape.setScale(1f);  //house needs to be shrunk
                shape.setPosition(10f, 0, 15);
                shape.setVelocity(0.05f * r.nextFloat(), 0.04f * r.nextFloat(), 0.03f * r.nextFloat());
                shape.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
                scene.addGameItem(shape);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Get location of each GameItem
        List<GameItem> gameItems = scene.getgameItems();
        System.out.println();
        for (GameItem gameItem1 : gameItems) {
            System.out.println();
            for (GameItem gameItem2 : gameItems) {
                gameItem1.doesItCollide(gameItem2);
            }
        }

        if (mouseInput.isRightButtonPressed()) {
            // Update camera based on mouse
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
            sceneChanged = true;
        }

        // Update camera position
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);

        lightAngle += angleInc;
        if (lightAngle < 0) {
            lightAngle = 0;
        } else if (lightAngle > 180) {
            lightAngle = 180;
        }
        float zValue = (float) Math.cos(Math.toRadians(lightAngle));
        float yValue = (float) Math.sin(Math.toRadians(lightAngle));
        Vector3f lightDirection = this.scene.getSceneLight().getDirectionalLight().getDirection();
        lightDirection.x = 0;
        lightDirection.y = yValue;
        lightDirection.z = zValue;
        lightDirection.normalize();

        // Update view matrix
        camera.updateViewMatrix();
    }

    public void addMeshOnScreen() {
        // TODO Fix this to use our classes - Factory pattern?!
        try {
            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/house/house.obj", "src/main/resources/models/house");
//            Mesh[] addMesh = StaticMeshesLoader.load("src/main/resources/models/russ/russ9.obj", "src/main/resources/models/russ");
            GameItem shape = new GameItem(addMesh);
            shape.setScale(0.10f);  //house needs to be shrunk
            shape.setPosition(5.00f * r.nextFloat(), 5.000f * r.nextFloat(), 15 * r.nextFloat());
            shape.setVelocity(0.05f * r.nextFloat(), 0.04f * r.nextFloat(), 0.03f * r.nextFloat());
            shape.setRotationVel(new Quaternionf(0.06f * r.nextFloat(), 0.08f * r.nextFloat(), 0.09f * r.nextFloat(), 0.0f));
            scene.addGameItem(shape);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearScreen() {
        try {
            // Remove everything, then put back the basics
            scene.removeAll();

            Mesh[] terrainMesh = StaticMeshesLoader.load("src/main/resources/models/terrain/terrain.obj", "src/main/resources/models/terrain");
            GameItem terrain = new GameItem(terrainMesh);
            terrain.setPosition(0.00f, -15.000f, 0.000f);
            terrain.setScale(100.0f);

            scene.setGameItems(new GameItem[]{terrain});

            // Setup  SkyBox
            float skyBoxScale = 100.0f;
            SkyBox skyBox = new SkyBox("src/main/resources/models/skybox.obj", new Vector4f(0.65f, 0.65f, 0.65f, 1.0f));
            skyBox.setScale(skyBoxScale);
            scene.setSkyBox(skyBox);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // set flag to indicate to remove all meshes.
    boolean removeAll = false;

    @Override
    public void render(Window window) {
        if (this.removeAll) {
            this.clearScreen();
            this.removeAll = false;  //toggle
        }
        if (firstTime) {
            sceneChanged = true;
            firstTime = false;
        }
        renderer.render(window, camera, scene, sceneChanged);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        scene.cleanup();
    }
}
