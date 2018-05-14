package com.soecode.lyf.service;

import com.soecode.lyf.dao.DaoSupport;
import com.soecode.lyf.entity.Page;
import com.soecode.lyf.utils.LanguageUnit;
import com.soecode.lyf.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description : 页面翻译. <br />
 * Create Time : 2016年12月23日 上午9:56:56 <br />
 * Copyright : Copyright (c) 2010 - 2016 All rights reserved. <br />
 * 
 * @author Yanshaodong
 * @version 1.0
 */
@Service("pageTranslationService")
public class PageTranslationService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, String>> getAllPageTranslation() {
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		try {
			List<PageData> list = (List<PageData>) dao.findForList("TranslationMapper.listAll", null);
			for (PageData pageData : list) {
				if (!map.containsKey(pageData.get("KEYWORD").toString())) {
					Map<String, String> tempMap = new HashMap<String, String>();
					tempMap.put(LanguageUnit.LANGUAGE.CHINESE.toString(), pageData.get("CH_TRANS") == null ? "" : pageData.get("CH_TRANS").toString());
					tempMap.put(LanguageUnit.LANGUAGE.ENGLISH.toString(), pageData.get("EN_TRANS") == null ? "" : pageData.get("EN_TRANS").toString());

					map.put(pageData.get("KEYWORD").toString(), tempMap);
				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public void edit(PageData pd) throws Exception {
		dao.update("TranslationMapper.edit", pd);
	}

	public void save(PageData pd) throws Exception {
		dao.save("TranslationMapper.save", pd);
	}

	public void delete(PageData pd) throws Exception {
		dao.delete("TranslationMapper.delete", pd);
	}
	
	public void deleteAll(PageData pd) throws Exception {
		dao.delete("TranslationMapper.deleteAll", pd);
	}

	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList("TranslationMapper.listPage",
				page);
	}

	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("TranslationMapper.findById", pd);
	}
}
