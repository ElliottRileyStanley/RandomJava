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


	public static void API() {
		try {
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

	public static void main(String[] args) {
		IMediaWriter writer = ToolFactory.makeWriter("maven testing\\test.mp4");
		writer.addVideoStream(0, 0, 1080, 1920);
		BufferedImage image = new BufferedImage(1080, 1920, BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = image.getGraphics();
		graphics.setFont(new Font("TimesRoman", Font.PLAIN, 50));

		API();

		System.out.println(StringEscapeUtils.unescapeHtml4(type));
		System.out.println(StringEscapeUtils.unescapeHtml4(question));
		System.out.println(StringEscapeUtils.unescapeHtml4(correct));
		System.out.println(StringEscapeUtils.unescapeHtml4(incorrect1));
		System.out.println(StringEscapeUtils.unescapeHtml4(incorrect2));
		System.out.println(StringEscapeUtils.unescapeHtml4(incorrect3));


		for (int i = 0; i < 10; i++) {
			graphics.setColor(new Color(255, 255, 255));
			graphics.fillRect(0, 0, 1080, 1920);
			graphics.setColor(new Color(0, 0, 0));
			graphics.drawString("AAAA", 100, 100);
			writer.encodeVideo(0, image, i, TimeUnit.SECONDS);
		}
		writer.close();
	}
}