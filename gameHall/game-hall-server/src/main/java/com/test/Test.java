package com.test;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {
	 public static void main(String[] args) throws MessagingException {  
	        Properties props = new Properties();  
	        props.setProperty("mail.smtp.auth", "true");//设置访问smtp服务器需要认证  
	        props.setProperty("mail.transport.protocol", "smtp"); //设置访问服务器的协议  
	          
	        Session session = Session.getDefaultInstance(props);  
	        session.setDebug(true); //打开debug功能  
	          
	        Message msg = new MimeMessage(session);  
	        msg.setFrom(new InternetAddress("a494525932@163.com")); //设置发件人，163邮箱要求发件人与登录用户必须一致（必填），其它邮箱不了解  
	        msg.setText("验证码是"+(int)((Math.random()*9+1)*100000)); //设置邮件内容  
	        msg.setSubject("请查看您的验证码"); //设置邮件主题  
	          
	        Transport trans = session.getTransport();  
	        trans.connect("smtp.163.com", 25, "a494525932", "a8279546"); //连接邮箱smtp服务器，25为默认端口  
	        trans.sendMessage(msg, new Address[]{new InternetAddress("3022867011@qq.com")}); //发送邮件  
	          
	        trans.close(); //关闭连接  
	    }  
}
