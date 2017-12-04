import java.util.*;
import java.awt.*;
import java.awt.event.*;

import processing.core.*;

public class DrawingSurface extends PApplet {
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private boolean clicked;
	private float rotX, rotY;
	private float rate = .015f;
	private float xLoc = width / 6f;
	private float yLoc = height / 6f;
	private float zLoc = 0;

	// The 7 colors on a cube. nonVisible is the color facing the inside of the cube
	// that people can't see
	private Color nonVisible = Color.darkGray.brighter();
	private Color red = Color.red.darker();
	private Color blue = Color.blue.brighter();
	private Color green = Color.green.darker();
	private Color yellow = Color.yellow.darker();
	private Color orange = Color.orange.darker().darker();
	private Color white = Color.white;

	private HashMap<Integer, Cublet> cubes = new HashMap<Integer, Cublet>();
	private Cublet[] rotations = new Cublet[4];

	private float rotationX, rotationY, rotationZ;

	private Cublet cube0 = new Cublet(red, nonVisible, nonVisible, blue, nonVisible, green);
	private Cublet cube1 = new Cublet(red, nonVisible, nonVisible, blue, yellow, nonVisible);
	private Cublet cube2 = new Cublet(red, nonVisible, orange, nonVisible, nonVisible, green);
	private Cublet cube3 = new Cublet(red, nonVisible, orange, nonVisible, yellow, nonVisible);
	private Cublet cube4 = new Cublet(nonVisible, white, nonVisible, blue, nonVisible, green);
	private Cublet cube5 = new Cublet(nonVisible, white, nonVisible, blue, yellow, nonVisible);
	private Cublet cube6 = new Cublet(nonVisible, white, orange, nonVisible, nonVisible, green);
	private Cublet cube7 = new Cublet(nonVisible, white, orange, nonVisible, yellow, nonVisible);

	private float loc[][] = { { -xLoc * 5, -yLoc * 5, 0 }, { 10, -yLoc * 5, 0 }, { -xLoc * 5, 10, 0 }, { 10, 10, 0 },
			{ -xLoc * 5, -yLoc * 5, -95 }, { 10, -yLoc * 5, -95 }, { -xLoc * 5, 10, -95 }, { 10, 10, -95 } };

	public static void main(String[] args) {
		// Makes the window using DrawingSurface as the PApplet
		PApplet.main("DrawingSurface");
	}

	public void settings() {
		// Sets the size of the window, and specifies that its 3D
		size(500, 500, P3D);
		cubes.put(0, cube0);
		cubes.put(1, cube1);
		cubes.put(2, cube2);
		cubes.put(3, cube3);
		cubes.put(4, cube4);
		cubes.put(5, cube5);
		cubes.put(6, cube6);
		cubes.put(7, cube7);
	}

	public void draw() {
		clear();
		background(128);
		lights();
		directionalLight(170, 170, 170, 0, 0, -1);
		ambientLight(20, 20, 20);
		lightSpecular(204, 204, 204);

		// Set the "center" of the sketch - used for all movements like additional
		// translations or rotations
		translate(xLoc * 17 + 5, yLoc * 16 + 5, zLoc);

		for (Cublet c : rotations) {
			if (c != null) {
				c.draw(this);
			}
		}

		for (Cublet c : rotations) {
			if (c != null) {
				c.setRotation(rotationX, rotationY, rotationZ);
			}
		}

		for (int i = 0; i < rotations.length; i++) {
			rotations[i] = null;
		}
		// Rotate the canvas based on values from either mouse or key input
		rotateX(rotX);
		rotateY(rotY);

		for (int i = 0; i < cubes.size(); i++) {
			cubes.get(i).setLocation(loc[i]);
			cubes.get(i).draw(this);
		}

		// Arrow Key input for changing the perspective on the cube
		if (checkKey(RIGHT))
			rotY += rate;
		else if (checkKey(LEFT))
			rotY -= rate;
		if (checkKey(UP))
			rotX += rate;
		else if (checkKey(DOWN))
			rotX -= rate;
 
		if (checkKey(KeyEvent.VK_A)) {
			if (checkKey(KeyEvent.VK_1)) {
				Cublet temp = cubes.get(4);
				cubes.replace(4, cubes.get(0));
				Cublet temp2 = cubes.get(6);
				cubes.replace(6, temp);
				temp = cubes.get(2);
				cubes.replace(2, temp2);
				cubes.replace(0, temp);

				rotations[0] = cubes.get(4);
				rotations[1] = cubes.get(6);
				rotations[2] = cubes.get(2);
				rotations[3] = cubes.get(0);

				rotationX += (float) Math.PI / 2;
			}
		}
	}

	// Adds the key to the array list
	public void keyPressed() {
		keys.add(keyCode);
	}

	// Removes key from array list
	public void keyReleased() {
		while (checkKey(keyCode))
			keys.remove(new Integer(keyCode));
	}

	// Checks if given key code is in the array list
	private boolean checkKey(int i) {
		return keys.contains(i);
	}

	// Having clicked = true can help control the mouse clicks and events
	public void mousePressed() {
		clicked = true;
	}

	// Changes perspective of cube based on the mouse drags
	public void mouseDragged() {
		if (clicked) {
			rotX += (pmouseY - mouseY) * rate;
			rotY += (mouseX - pmouseX) * rate;
		}
	}

	public void mouseReleased() {
		clicked = false;
	}
}
