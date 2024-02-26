package com.example;

import java.awt.Graphics;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferedImage;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;

public class Main {
	public static void main(String[] args) {
		IMediaWriter writer = ToolFactory.makeWriter("maven testing\\test.mp4");
		writer.addVideoStream(0, 0, 200, 200);
		BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = image.getGraphics();

		int x = 10;
		int y = 40;
		int xspeed = 1;
		int yspeed = 1;
		for (int i = 0; i < 10000; i++) {
			graphics.setColor(new Color(0, 0, 0));
			graphics.fillRect(0, 0, 200, 200);
			graphics.setColor(new Color(255, 255, 255));
			graphics.fillRect(x-10, y-10, 20, 20);
			x += xspeed;
			y += yspeed;
			if (x == 10 || x == 190) {
				xspeed *= -1;
			}
			if (y == 10 || y == 190) {
				yspeed *= -1;
			}
			writer.encodeVideo(0, image, i*5, TimeUnit.MILLISECONDS);
		}
		writer.close();
	}
}