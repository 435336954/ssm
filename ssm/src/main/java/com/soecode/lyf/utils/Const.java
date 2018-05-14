package com.soecode.lyf.utils;

import org.springframework.context.ApplicationContext;

/**
 * Description : Const. <br />
 * Create Time : 2016年11月22日 下午1:23:34 <br />
 * Copyright : Copyright (c) 2010 - 2016 All rights reserved. <br />
 * 
 * @author Yanshaodong
 * @version 1.0
 */
public class Const {
	
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_AUSER = "sessionAuser";            //会员SESSION
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_allmenuList = "allmenuList";		//全部菜单
	public static final String SESSION_allmenuListMap = "allmenuListMap";		//全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";			
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String SESSION_LAST_URL="lasturl";
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do";				//登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//系统名称路径
//	public static final String PAGE	= "admin/config/PAGE.txt";			//分页条数配置路径
	public static final String EMAIL = "admin/config/EMAIL.txt";		//邮箱服务器配置路径
	public static final String FWATERM = "admin/config/FWATERM.txt";	//文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt";	//图片水印配置路径
//	public static final String SITESET	= "admin/config/SITESET.txt";	//网站配置路径
	public static final String SITESETEN	= "admin/config/SITESETEN.txt";	//网站配置EN路径
	public static final String SITESETAR	= "admin/config/SITESETAR.txt";	//网站配置AR路径
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHIMG_BRAND = "uploadFiles/uploadImgs/brand/";//品牌图片的路径
	public static final String FILEPATHIMG_MEMBER = "uploadFiles/uploadImgs/member/";//品牌图片的路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String TOKEN = "TOKEN"; // 令牌
	
	/** 分页大小 */
	public static final int PAGE_SIZE = 10;
	
 
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(reg)|(web)|(main)|(websocket)|(plugins)|(uploadFiles)|(index)|(search)|(crossdomain)|(favicon)|(robots)|(sitemap)|(carwler)).*";	//不对匹配该值的访问路径拦截（正则）
	public static final String FRIENDLYLINKIMGS="uploadFiles/uploadImgs/friendlyLinkImgs/";//友情链接图片保存路径
	public static final String EXHIBITIONIMGS="uploadFiles/uploadImgs/exhibitionImgs/";//展会图片保存路径

	public static final String MEMBER_INTERCEPTOR_PATH=".*/(member).*";//会员用户
	
	public static final String MEMBER_LOGIN="/web/login";	
	
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};
	
	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};
	
	public static final String[] URL_FOR_TDK = {"/web/product/search.do", "/web/product/detail.do", "/index/isla.do", "/index/led.do", "/index/ceramic.do", "/index/glass.do","/index/list.do", "/web/buyinfo/purchase/goPurchaseList.do","/web/buyinfo/todet.do","/web/company/search.do","/web/shop/index","/web/business/search","/web/business/detail","/web/articledec/list","/web/articledec/msg"};

	public static final String STR_BY = "由"; 
	public static final String STR_ADJUST= "调整为"; 
	public static final String STR_CLASS_ID= "分类编码"; 
	public static final String STR_BLANK= " "; 
	public static final String STR_THE= "的"; 
	public static final String STR_SET= "设置了"; 
	public static final String STR_E = "E"; // E 代表汇率
	public static final String STR_P = "P"; // P 代表利润率
	public static final String STR_C = "C";  // C 代表成本价
	
	public static final String SEND_EMAIL_CONTROL = "SEND_EMAIL_CONTROL";  // 邮件发送控制器
	public static final String EMAIL_ON = "ON";  // 打开发送邮件
	public static final String EMAIL_OFF = "OFF";  // 关闭发送邮件
	public static final String BILL_MAIL_SERVER_HOST = "BILL_MAIL_SERVER_HOST";  // // 服务器
	public static final String BILL_MAIL_SERVER_PORT = "BILL_MAIL_SERVER_PORT";  // 端口
	public static final String BILL_EMAIL_ADDRESS = "BILL_EMAIL_ADDRESS";  // 发送邮箱
	public static final String BILL_EMAIL_PASSWORD = "BILL_EMAIL_PASSWORD";  // 发送邮箱密码
	public static final String SEND_EMAIL_NICK = "SEND_EMAIL_NICK";  // 发送邮箱人昵称
	public static final String SEND_EMAIL = "SEND_EMAIL";  // 发送邮箱 环境 (测试、生产)
	
	public static final String B2B_P_C_EMAIL = "B2B_P_C_EMAIL";  // 客户端邮件内容占位符
	public static final String B2B_P_C_URL = "B2B_P_C_URL";  // 客户端邮件URL占位符
	
	public static final String PRODUCT_ID = "PRODUCT_ID";  
	
	public static final String URL_REDIRECT = "urlRedirect";  
	public static final String ACTIVE_ID = "activeId";  
	public static final String LOGIN_CAPTCHA_ID = "LOGIN_CAPTCHA_ID";  
	
	
	
}
