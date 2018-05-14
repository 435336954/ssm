package com.soecode.lyf.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description : ImageUtil. <br />
 * Create Time : 2016年11月22日 下午1:24:24 <br />
 * Copyright : Copyright (c) 2010 - 2016 All rights reserved. <br />
 * 
 * @author Yanshaodong
 * @version 1.0
 */
@Component
public class ImageUtil {
	
	/**
	 * 图片上传服务器
	 */
	private static String IMG_UPLOAD_SERVER;

	/**
	 * 图片访问服务器
	 */
	private static String[] IMG_ACCESS_SERVERS;

	/**
	 * 图片计数器
	 */
	private static Integer IMG_ACCESS_INDEX = 0;

	@Autowired(required = true)
	public void setImgUploadServer(@Value("#{configProperties['img_upload_server']}") String server) {
		ImageUtil.IMG_UPLOAD_SERVER = server;

	}

	@Autowired(required = true)
	public void setImgAccessServer(@Value("#{configProperties['img_access_server']}") String server) {
		ImageUtil.IMG_ACCESS_SERVERS = server.split(",");

	}
	
	/**
	 * 获取图片上传服务器
	 */
	public static String getImgUploadServer() {
		return ImageUtil.IMG_UPLOAD_SERVER;
	}

	/**
	 * 获取一个图片服务器地址
	 */
	private static String getOneImgAccessServer() {
		if (IMG_ACCESS_SERVERS.length == 0) {
			return "";
		} else {
			ImageUtil.IMG_ACCESS_INDEX++;
			if (ImageUtil.IMG_ACCESS_INDEX > 100000)
				IMG_ACCESS_INDEX = 0;
			return ImageUtil.IMG_ACCESS_SERVERS[ImageUtil.IMG_ACCESS_INDEX % ImageUtil.IMG_ACCESS_SERVERS.length];
		}
	}

	/**
	 * 获取图片显示地址
	 */
	public static String getImgUrl(String img) {
		if (img.startsWith("http")) {
			return img;
		} else {
			if(img.equals(""))
				return img;
			return getOneImgAccessServer() + img;
		}
	}
}
