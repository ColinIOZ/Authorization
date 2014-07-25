package com.big.authorization.util;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailUtil {

	private static boolean flag = true;

	public static boolean sendEmail(String toAddress, String subject, String content) {
		
		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.qq.com");

		props.put("mail.smtp.auth", "true");
		// 允许smtp校验

		Session sendMailSession = Session.getInstance(props, null);

		try {

			Transport transport = sendMailSession.getTransport("smtp");

			// 连接你的服务器，注意用户名和密码必须填写正确，否则权限得不到

			transport.connect("smtp.qq.com", "594653799@qq.com", "(ChenJiaJia)");

			Message newMessage = new MimeMessage(sendMailSession);

			// 设置mail主题

			String mail_subject = subject;

			newMessage.setSubject(mail_subject);

			// 设置发信人地址

			String strFrom = "594653799@qq.com";

			strFrom = new String(strFrom.getBytes(), "iso-8859-1");

			newMessage.setFrom(new InternetAddress(strFrom));

			// 改变发件人地址

			String newFrom = "admin";
			
			newFrom = new String(newFrom.getBytes(), "iso-8859-1");
			
			Address addressFrom[] = { new InternetAddress(newFrom) };

			newMessage.addFrom(addressFrom);

			// 设置收件人地址

			Address addressTo[] = { new InternetAddress(toAddress) };

			newMessage.setRecipients(Message.RecipientType.TO, addressTo);

			// 设置mail正文

			newMessage.setSentDate(new java.util.Date());

			String mail_text = content;

			newMessage.setText(mail_text);

			newMessage.saveChanges(); // 保存发送信息

			transport.sendMessage(newMessage,
					newMessage.getRecipients(Message.RecipientType.TO)); // 发送邮件

			transport.close();

			System.out.println("发送成功!");
		} catch (Exception e) {

			System.out.println("发送失败！");

			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public static void main(String args[]) throws Exception {

		/**
		 * 3、注意：
		 * 
		 * 1 ）在初次进行发送的时候，必须将你的邮箱设置为允许 smtp 校验，否则邮件发送不出去
		 * 
		 * 2 ）填写正确的邮件权限、邮件名称和密码，否则会抛出异常
		 */

	}
}
