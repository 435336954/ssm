package com.soecode.lyf.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LanguageUnit {

	/*
	 * 当前语言
	 */
	private static LANGUAGE nowLanguge;

	@Autowired(required = true)
	public void setNowLanguage(@Value("#{configProperties['language']}") String languge) {
		int lan = Integer.parseInt(languge);
		LanguageUnit.nowLanguge = LANGUAGE.getLanguage(lan);
	}

	public static LANGUAGE getNowLanguge() {
		return nowLanguge;
	}

	public enum LANGUAGE {
		CHINESE(1), ENGLISH(2);

		private int language;

		private LANGUAGE(int type) {
			this.language = type;
		}

		/*
		 * 获取语言的值
		 */
		public int Value() {
			return language;
		}

		/*
		 * 根据int值获取语言
		 */
		public static LANGUAGE getLanguage(int value) {
			switch (value) {
			case 1:
				return CHINESE;
			case 2:
				return ENGLISH;
			default:
				return null;
			}
		}
	}
}
