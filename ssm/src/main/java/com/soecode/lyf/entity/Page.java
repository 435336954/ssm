package com.soecode.lyf.entity;

import com.soecode.lyf.translation.PageTranslation;
import com.soecode.lyf.utils.Const;
import com.soecode.lyf.utils.PageData;

import java.util.List;

/**
 * Description : Page. <br />
 * Create Time : 2016年11月22日 下午1:23:34 <br />
 * Copyright : Copyright (c) 2010 - 2016 All rights reserved. <br />
 * 
 * @author Yanshaodong
 * @version 1.0
 */
public class Page {
	
	private int showCount = Const.PAGE_SIZE;; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentPage;	//当前页
	private int currentResult;	//当前记录起始索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();
	private PageData pd = new PageData();
	private List<String> arrayBrand;
	private List<String> arrayClass;
	private String[] arrayStr;
	private List<PageData> list;

	
	public int getTotalPage() {
		if (totalResult % showCount == 0) {
			totalPage = totalResult / showCount;
		} else {
			totalPage = totalResult / showCount + 1;
		}
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	
	public int getCurrentPage() {
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage > getTotalPage()) {
			currentPage = getTotalPage();
		}
		return currentPage;
	}
	
	public int getFixCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("<div class=\"pagination\">	<ul>\n");
			if(currentPage==1){
				sb.append("	<li><a>"+ PageTranslation.T("共")+"<font color=red>"+totalResult+"</font>"+PageTranslation.T("条")+"</a></li>\n");
				sb.append("	<li><input type=\"number\" min=\"1\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\""+PageTranslation.T("页码")+"\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn-mini btn-success\">"+PageTranslation.T("跳转")+"</a></li>\n");
				sb.append("	<li><a>"+PageTranslation.T("首页")+"</a></li>\n");
				sb.append("	<li><a>"+PageTranslation.T("上页")+"</a></li>\n");
			}else{
				sb.append("	<li><a>"+PageTranslation.T("共")+"<font color=red>"+totalResult+"</font>"+PageTranslation.T("条")+"</a></li>\n");
				sb.append("	<li><input type=\"number\" min=\"1\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\""+PageTranslation.T("页码")+"\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn-mini btn-success\">"+PageTranslation.T("跳转")+"</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">"+PageTranslation.T("首页")+"</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(currentPage-1)+")\">"+PageTranslation.T("上页")+"</a></li>\n");
			}
			int showTag = 5;//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<li><a><font color='#808080'>"+i+"</font></a></li>\n");
				else
					sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+i+")\">"+i+"</a></li>\n");
			}
			if(currentPage==totalPage){
				sb.append("	<li><a>"+PageTranslation.T("下页")+"</a></li>\n");
				sb.append("	<li><a>"+PageTranslation.T("尾页")+"</a></li>\n");
			}else{
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(currentPage+1)+")\">"+PageTranslation.T("下页")+"</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+totalPage+")\">"+PageTranslation.T("尾页")+"</a></li>\n");
			}
			sb.append("	<li><a>"+PageTranslation.T("第")+""+currentPage+""+PageTranslation.T("页")+"</a></li>\n");
			sb.append("	<li><a>"+PageTranslation.T("共")+""+totalPage+""+PageTranslation.T("页")+"</a></li>\n");
			
			sb.append("</ul></div>\n");
			sb.append("<script type=\"text/javascript\">\n");
			
			//换页函数
			sb.append("function nextPage(page){");
			sb.append(" try{ top.jzts();} catch(e){}");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage='+page);\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");
			
			//调整每页显示条数
			sb.append("function changeCount(value){");
			sb.append("  try{ top.jzts();} catch(e){}");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		else{url += \"?"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('"+(entityOrField?"showCount":"page.showCount")+"')>-1){\n");
			sb.append("				var reg = /"+(entityOrField?"showCount":"page.showCount")+"=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'"+(entityOrField?"showCount":"page.showCount")+"='+value);\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"showCount":"page.showCount")+"=\"+value;\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");
			
			//跳转函数 
			sb.append("function toTZ(){");
			sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
			sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("nextPage(toPaggeVlue);");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	public String getPageStrNofunction(){
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("<div class=\"pagination\">	<ul>\n");
			if(currentPage==1){
				sb.append("	<li><a>"+PageTranslation.T("共")+"<font color=red>"+totalResult+"</font>"+PageTranslation.T("条")+"</a></li>\n");
				sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\""+PageTranslation.T("页码")+"\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn-mini btn-success\">"+PageTranslation.T("跳转")+"</a></li>\n");
				sb.append("	<li><a>"+PageTranslation.T("首页")+"</a></li>\n");
				sb.append("	<li><a>"+PageTranslation.T("上页")+"</a></li>\n");
			}else{
				sb.append("	<li><a>"+PageTranslation.T("共")+"<font color=red>"+totalResult+"</font>"+PageTranslation.T("条")+"</a></li>\n");
				sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\""+PageTranslation.T("页码")+"\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn-mini btn-success\">"+PageTranslation.T("跳转")+"</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">"+PageTranslation.T("首页")+"</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(currentPage-1)+")\">"+PageTranslation.T("上页")+"</a></li>\n");
			}
			int showTag = 5;//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<li><a><font color='#808080'>"+i+"</font></a></li>\n");
				else
					sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+i+")\">"+i+"</a></li>\n");
			}
			if(currentPage==totalPage){
				sb.append("	<li><a>"+PageTranslation.T("下页")+"</a></li>\n");
				sb.append("	<li><a>"+PageTranslation.T("尾页")+"</a></li>\n");
			}else{
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(currentPage+1)+")\">"+PageTranslation.T("下页")+"</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+totalPage+")\">"+PageTranslation.T("尾页")+"</a></li>\n");
			}
			sb.append("	<li><a>"+PageTranslation.T("第")+""+currentPage+""+PageTranslation.T("页")+"</a></li>\n");
			sb.append("	<li><a>"+PageTranslation.T("共")+""+totalPage+""+PageTranslation.T("页")+"</a></li>\n");
			sb.append("</ul></div>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	
	/**
	 * 网站二次改版升级的分页
	 * 描述
	 *  {
	 *   主要是对网站的前台样式和显示形式的修改,和getPageStr()方法区别前后台的代码拆分。
	 *  }
	 * @version 1.1
	 * @author haiyangWang
	 * @date 2015-12-31 AM 9:31
	 */
	public String getPageUpgrade() {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("<div class=\"slpage\">\n");
			if(currentPage==1){
				sb.append("	<a>"+PageTranslation.T("共")+"<font color=red>"+totalResult+"</font>"+PageTranslation.T("条")+"</a>\n");
				sb.append("	<a>"+PageTranslation.T("首页")+"</a>\n");
				sb.append("	<a>"+PageTranslation.T("上页")+"</a>\n");
			}else{
				sb.append("	<a>"+PageTranslation.T("共")+"<font color=red>"+totalResult+"</font>"+PageTranslation.T("条")+"</a>\n");
				sb.append("	<a onclick=\"nextPage(1)\">"+PageTranslation.T("首页")+"</a>\n");
				sb.append("	<a onclick=\"nextPage("+(currentPage-1)+")\">"+PageTranslation.T("上页")+"</a>\n");
			}
			int showTag = 5;//分页标签显示数量
			int startTag = 1;
			if(currentPage>=showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<a class='on'>"+i+"</a>\n");
				else
					sb.append("<a onclick=\"nextPage("+i+")\">"+i+"</a>\n");
			}
			if(currentPage==totalPage){
				sb.append("	<a>"+PageTranslation.T("下页")+"</a>\n");
				sb.append("	<a>"+PageTranslation.T("尾页")+"</a>\n");
			}else{
				sb.append("	<a onclick=\"nextPage("+(currentPage+1)+")\">"+PageTranslation.T("下页")+"</a>\n");
				sb.append("	<a onclick=\"nextPage("+totalPage+")\">"+PageTranslation.T("尾页")+"</a>\n");
			}
			sb.append("	<a>"+PageTranslation.T("共")+""+totalPage+""+PageTranslation.T("页")+"</a>\n");
			sb.append("</div>\n");
			sb.append("<script type=\"text/javascript\">\n");
			//换页函数
			sb.append("function nextPage(page){");
			sb.append(" try{ top.jzts();} catch(e){}");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage='+page);\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\"+page;}\n");
			sb.append("		\n");
			sb.append("url = url.replace(\"#Anchor\",'');");
			sb.append("url+=\"#Anchor\";");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");
			
			//调整每页显示条数
			sb.append("function changeCount(value){");
			sb.append("  try{ top.jzts();} catch(e){}");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		else{url += \"?"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('"+(entityOrField?"showCount":"page.showCount")+"')>-1){\n");
			sb.append("				var reg = /"+(entityOrField?"showCount":"page.showCount")+"=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'"+(entityOrField?"showCount":"page.showCount")+"='+value);\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"showCount":"page.showCount")+"=\"+value;\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"showCount":"page.showCount")+"=\"+value;}\n");
			sb.append("		\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");
			
			//跳转函数 
			sb.append("function toTZ(){");
			sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
			sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("nextPage(toPaggeVlue);");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	
	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	
	public int getShowCount() {
		return showCount;
	}
	
	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}
	
	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0) {
			currentResult = 0;
		}
		return currentResult;
	}
	
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	
	public boolean isEntityOrField() {
		return entityOrField;
	}
	
	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
	
	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}

	public List<String> getArrayClass() {
		return arrayClass;
	}

	public void setArrayClass(List<String> arrayClass) {
		this.arrayClass = arrayClass;
	}

	public List<String> getArrayBrand() {
		return arrayBrand;
	}

	public void setArrayBrand(List<String> arrayBrand) {
		this.arrayBrand = arrayBrand;
	}

	/**
	 * @return the arrayStr
	 */
	public String[] getArrayStr() {
		return arrayStr;
	}

	/**
	 * @param arrayStr the arrayStr to set
	 */
	public void setArrayStr(String[] arrayStr) {
		this.arrayStr = arrayStr;
	}

	public List<PageData> getList() {
		return list;
	}

	public void setList(List<PageData> list) {
		this.list = list;
	}

}