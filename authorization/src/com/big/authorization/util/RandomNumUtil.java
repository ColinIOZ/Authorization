package com.big.authorization.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class RandomNumUtil {
	private ByteArrayInputStream image;// 图像
	private String str;// 验证码

	private RandomNumUtil() {
		init();// 初始化属性
	}

	/*
	 * 取得RandomNumUtil实例
	 */
	public static RandomNumUtil Instance() {
		return new RandomNumUtil();
	}

	/*
	 * 取得验证码图片
	 */
	public ByteArrayInputStream getImage() {
		return this.image;
	}

	/*
	 * 取得图片的验证码
	 */
	public String getString() {
		return this.str;
	}

	private void init() {
		// 在内存中创建图象
		int width = 100, height = 24;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		// 填充矩形
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		// 画矩形
		g.drawRect(0, 0, width - 1, height - 1);
		
		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < 80; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}
		g.setColor(getRandColor(160, 200));

		for (int i = 0; i < 80; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(12) + 1;
			int yl = random.nextInt(6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}
		// 设定字体
		g.setFont(new Font("Arial Black", Font.PLAIN, 16));

		// 取随机产生的认证码(6位)
		String sRand = "";
		for (int i = 0; i < 6; i++) {
			
			String tmp = getRandomChar();
			sRand += tmp;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成,后面两个参数决定图片生成的位子
			g.drawString(tmp, 15 * i + 10, 15);
		}
		// 赋值验证码
		this.str = sRand;

		// 图象生效
		g.dispose();
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			ImageOutputStream imageOut = ImageIO
					.createImageOutputStream(output);
			ImageIO.write(image, "JPEG", imageOut);
			imageOut.close();
			input = new ByteArrayInputStream(output.toByteArray());
		} catch (Exception e) {
			System.out.println("验证码图片产生出现错误：" + e.toString());
		}

		this.image = input;/* 赋值图像 */
	}

	/*
	 * 给定范围获得随机颜色
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	private String getRandomChar() {

		int rand = (int) Math.round(Math.random() * 2);
		long itmp = 0;
		char ctmp = '\u0000';
		switch (rand) {

		case 1:
			itmp = Math.round(Math.random() * 25 + 65);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);

		case 2:
			itmp = Math.round(Math.random() * 25 + 97);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);

		default:
			itmp = Math.round(Math.random() * 9);
			return itmp + "";
		}
	}
	
}
