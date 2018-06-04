package com.eyeieye.melody.demo.web.action.download;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author fish
 * 
 */
@Controller
@RequestMapping("/download")
public class DownLoadAction {

	private @Value("${web.encoding}")
	String encoding;

	private String exportContentType = "application/vnd.ms-excel";

	@RequestMapping(value = "/excel.htm", method = RequestMethod.GET)
	public void excel(HttpServletResponse response) throws IOException {
		// ��Ϊһ��demo,excelʹ��html����,������ʽʹ����poi�ȹ�������
		response.setCharacterEncoding(encoding);
		response.setContentType(exportContentType);
		String fileName = "demo_down_load.xls";
		response.setHeader("Content-Disposition", "attachment;Filename="
				+ fileName);
		response
				.getWriter()
				.print(
						"<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encoding+"\"></head><body><table><tr><th>ͷ1</th><th>ͷ2</th></tr><tr><td>��1</td><td>��2</td></tr></table></body></html>");
	}
}
