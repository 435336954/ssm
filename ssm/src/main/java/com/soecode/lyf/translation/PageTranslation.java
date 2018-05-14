package com.soecode.lyf.translation;

import com.soecode.lyf.service.PageTranslationService;
import com.soecode.lyf.utils.LanguageUnit;
import com.soecode.lyf.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description : PageTranslation. <br />
 * Create Time : 2016年11月22日 下午1:25:38 <br />
 * Copyright : Copyright (c) 2010 - 2016 All rights reserved. <br />
 * 
 * @author Yanshaodong
 * @version 1.0
 */
@Component
public class PageTranslation {
	
	/**
	 * 翻译map
	 */
	private static Map<String, Map<String, String>> translationMap;

	private static PageTranslationService transService;

	public static PageTranslationService getTransService() {
		return transService;
	}	

	@Autowired(required = true)
	public void setTransService(
			@Qualifier("pageTranslationService") PageTranslationService transService) {
		PageTranslation.transService = transService;
	}

	/**
	 * 翻译
	 */
	public static String T(String str) {
		Map<String, Map<String, String>> map=getTranslationMap();
		if(map.containsKey(str))
		{
			Map<String, String> m=map.get(str);
			String languageString= LanguageUnit.getNowLanguge().toString();
			if(m.containsKey(languageString)&&!m.get(languageString).equals(""))				
				return m.get(languageString);			
		}else {//如果没有，就自动添加一条
			PageData pData=new PageData();
			pData.put("KEYWORD", str);
			try {
				transService.save(pData);
				refresh();
			} catch (Exception e) {
				e.printStackTrace();
			}				
		}
		return str;
	}

	private static Map<String, Map<String, String>> getTranslationMap() {
		if(translationMap!=null)
			return translationMap;
		synchronized (PageTranslation.class) {
			if (translationMap == null) {
				translationMap = transService
						.getAllPageTranslation();
			}
		}
		return translationMap;
	}

	public static void refresh() {
		translationMap = null;
	}
}
