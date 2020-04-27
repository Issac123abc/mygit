package com.xinjing.dxg.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.xinjing.dxg.common.utils.StringUtil;

@Component
public class BaseController {
	protected PagerParam getPagerParam(HttpServletRequest req) {
		PagerParam p = new PagerParam();
		Integer pageNumber = 1;
		Integer pageSize = 10;
		String sortName = "";
		String sortOrder = "asc";
		if (!StringUtil.isBlank(req.getParameter("pageNumber"))) {
			try {
				pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
			} catch (Exception ex) {
			}
		}

		if (!StringUtil.isBlank(req.getParameter("pageSize"))) {
			try {
				pageSize = Integer.parseInt(req.getParameter("pageSize"));
			} catch (Exception ex) {

			}
		}
		if (!StringUtil.isBlank(req.getParameter("sortName"))) {
			sortName = req.getParameter("sortName");
		}

		if (!StringUtil.isBlank(req.getParameter("order"))) {
			sortOrder = req.getParameter("order");
		}
		p.setPageNumber(pageNumber);
		p.setPageSize(pageSize);
		p.setSortName(sortName);
		p.setSortOrder(sortOrder);
		return p;
	}
}
