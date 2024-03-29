package com.example;

import org.apache.commons.text.StringEscapeUtils;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferedImage;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;

import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONObject;

public class Main {

	static String question = "";
	static String correct = "";
	static String incorrect1 = "";
	static String incorrect2 = "";
	static String incorrect3 = "";
	static String type = "";

	static Font font = new Font("TimesRoman", Font.PLAIN, 50);

	static BufferedImage image = new BufferedImage(1080, 1920, BufferedImage.TYPE_3BYTE_BGR);
	static Graphics graphics = image.getGraphics();

	public static void API() {
		try {
		@SuppressWarnings("deprecation")
		URL url = new URL("https://opentdb.com/api.php?amount=1");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		JSONObject response = new JSONObject(reader.readLine());
		question = response.getJSONArray("results").getJSONObject(0).getString("question");
		type = response.getJSONArray("results").getJSONObject(0).getString("type");
		correct = response.getJSONArray("results").getJSONObject(0).getString("correct_answer");
		incorrect1 = response.getJSONArray("results").getJSONObject(0).getJSONArray("incorrect_answers").getString(0);
		if (type.equals("multiple")) {
			incorrect2 = response.getJSONArray("results").getJSONObject(0).getJSONArray("incorrect_answers").getString(1);
			incorrect3 = response.getJSONArray("results").getJSONObject(0).getJSONArray("incorrect_answers").getString(2);
		}
		} catch (IOException e) {}
	}

	public static void drawText(String text, int x1, int y1, int x2, int y2, int spacing) {
		FontMetrics metrics = graphics.getFontMetrics(font);
		int current_x = x1 + spacing;
		int current_y = y1 + spacing + metrics.getHeight();

		graphics.setColor(new Color(0, 0, 0));
		graphics.fillRect(x1, y1, x2, y2);
		graphics.setColor(new Color(255, 255, 255));
		graphics.drawString("AAAA", current_x, current_y);
	}
	public static void main(String[] args) {
		IMediaWriter writer = ToolFactory.makeWriter("maven testing\\test.mp4");
		writer.addVideoStream(0, 0, 1080, 1920);
		graphics.setFont(font);


		System.out.println(StringEscapeUtils.unescapeHtml4(type));
		System.out.println(StringEscapeUtils.unescapeHtml4(question));
		System.out.println(StringEscapeUtils.unescapeHtml4(correct));
		System.out.println(StringEscapeUtils.unescapeHtml4(incorrect1));
		System.out.println(StringEscapeUtils.unescapeHtml4(incorrect2));
		System.out.println(StringEscapeUtils.unescapeHtml4(incorrect3));


		for (int i = 0; i < 10; i++) {
			graphics.setColor(new Color(255, 255, 255));
			graphics.fillRect(0, 0, 1080, 1920);
			drawText("AAAA BBBB CCCC", 0, 0, 500, 500, 5);
			writer.encodeVideo(0, image, i, TimeUnit.SECONDS);
		}
		writer.close();
	}
}