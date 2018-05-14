package com.soecode.lyf.web;

import com.soecode.lyf.service.BookService;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/code") // url:/模块/资源/{id}/细分 /seckill/list
public class CodeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/index",method = RequestMethod.GET)
	private String code(){
		return "/code/code";
	}


	/**
	 * 跳转index页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String index(Model model) {
		return "index";
	}
}
