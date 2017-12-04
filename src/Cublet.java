import java.awt.*;
import java.util.Arrays;

import processing.core.*;

public class Cublet {
	private Color front, back, bot, top, right, left;
	private float x,y,z, rotX, rotY, rotZ;
	
	public Cublet(Color front, Color back, Color bot, Color top, Color right, Color left) {
		this.front = front;
		this.back = back;
		this.bot = bot;
		this.top = top;
		this.right = right;
		this.left = left;
	}
	
	public void setLocation(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setLocation(float[] loc) {
		this.x = loc[0];
		this.y = loc[1];
		this.z = loc[2];
	}
	
	public void setRotation(float x, float y, float z) {
		this.rotX = x;
		this.rotY = y;
		this.rotZ = z;
	}
	
	public void setRotation(float[] rot) {
		this.rotX = rot[0];
		this.rotY = rot[1];
		this.rotZ = rot[2];
	}
	
	public void draw(PApplet g) {
		// Push style and matrix prevent any processing changes (eg. translate) from
		// affecting shapes made after this method is called
		g.pushStyle();
		g.pushMatrix();

		// Translate the box from the center of sketch (defined in draw())
		g.translate(x, y, z);
		g.noStroke();
		// Make object bigger
		g.scale(45);
		
		g.rotateX(rotX);
		g.rotateY(rotY);
		g.rotateZ(rotZ);

		// Following sections all make the box together using 6 quadrilaterals. Each
		// quadrilateral is made from 4 vertices which the following sections take care
		// of for the commented face

		// Front
		g.beginShape(g.QUADS);
		g.fill(front.getRGB());
		g.vertex(-1, -1, 1);
		g.vertex(1, -1, 1);
		g.vertex(1, 1, 1);
		g.vertex(-1, 1, 1);
		g.endShape();

		// Back
		g.beginShape(g.QUADS);
		g.fill(back.getRGB());
		g.vertex(1, -1, -1);
		g.vertex(-1, -1, -1);
		g.vertex(-1, 1, -1);
		g.vertex(1, 1, -1);
		g.endShape();

		// Bottom
		g.beginShape(g.QUADS);
		g.fill(bot.getRGB());
		g.vertex(-1, 1, 1);
		g.vertex(1, 1, 1);
		g.vertex(1, 1, -1);
		g.vertex(-1, 1, -1);
		g.endShape();

		// Top
		g.beginShape(g.QUADS);
		g.fill(top.getRGB());
		g.vertex(-1, -1, -1);
		g.vertex(1, -1, -1);
		g.vertex(1, -1, 1);
		g.vertex(-1, -1, 1);
		g.endShape();

		// Right
		g.beginShape(g.QUADS);
		g.fill(right.getRGB());
		g.vertex(1, -1, 1);
		g.vertex(1, -1, -1);
		g.vertex(1, 1, -1);
		g.vertex(1, 1, 1);
		g.endShape();

		// Left
		g.beginShape(g.QUADS);
		g.fill(left.getRGB());
		g.vertex(-1, -1, -1);
		g.vertex(-1, -1, 1);
		g.vertex(-1, 1, 1);
		g.vertex(-1, 1, -1);
		g.endShape();

		// Removes all changes made to processing things (eg. translate) so new shapes
		// made after method call aren't affected
		g.popStyle();
		g.popMatrix();
	}
}
