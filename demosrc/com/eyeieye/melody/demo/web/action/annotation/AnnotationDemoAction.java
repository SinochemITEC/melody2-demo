package com.eyeieye.melody.demo.web.action.annotation;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author fish
 * 
 */
@Controller
@RequestMapping("/annotation")
public class AnnotationDemoAction {

	/**
	 * ��������void,�����urlѰ����ͼ���������У�Ѱ������Ϊ"annotation/return/void"�� view
	 * 
	 * @param model
	 */
	@RequestMapping("/return/void.htm")
	public void annotationVoid(ModelMap model) {
		model.addAttribute("currentTime", new Date());
	}

	/**
	 * ��������String,����ݷ���ֵѰ����ͼ���������У�Ѱ������Ϊ"annotation/return/im_string"�� view
	 * 
	 * @param model
	 */
	@RequestMapping("/return/string.htm")
	public String annotationString(Map<String, Object> map) {
		map.put("currentTime", new Date());
		return "annotation/return/im_string";
	}

	/**
	 * ��������view,�����urlѰ����ͼ���������У�Ѱ������Ϊ"annotation/return/model_view"�� view
	 * 
	 * @param model
	 */
	@RequestMapping("/return/view.htm")
	public ModelAndView annotationModelAndView() {
		ModelAndView mv = new ModelAndView("annotation/return/model_view");
		mv.addObject("currentTime", new Date());
		return mv;
	}

	/**
	 * ��������Object(��String,int,long��),��ѷ��ض��������json����
	 * 
	 * @param model
	 */
	@RequestMapping("/return/json.json")
	public @ResponseBody
	West annotationJson() {
		West w = new West();
		w.setAge(500);
		w.setName("���");
		w.setNick("��ʦ��");
		return w;
	}

	@RequestMapping("/param/base.htm")
	public void annotationBase(HttpServletRequest request,
			@RequestParam("name") String name,
			@RequestParam(required = false, defaultValue = "1") int size,
			Model model) {
		String ip = request.getRemoteAddr();
		model.addAttribute("ip", ip);
		model.addAttribute("name", name);
		model.addAttribute("size", size);
	}

	public static class WestJsonSerializer extends JsonSerializer<Integer> {

		@Override
		public void serialize(Integer west, JsonGenerator jg,
				SerializerProvider sp) throws IOException,
				JsonProcessingException {
			jg.writeNumber(west + 19);
		}
	}

	public static class West {
		private String name;
		private String nick;

		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNick() {
			return nick;
		}

		public void setNick(String nick) {
			this.nick = nick;
		}

		@JsonSerialize(using = WestJsonSerializer.class)
		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}

	@RequestMapping("/param/object_bind.htm")
	public void annotationObjectBind(West obj, Model model) {
		model.addAttribute("obj", obj);
	}

	@RequestMapping("/param/json")
	public @ResponseBody
	West annotationJsonBind(@RequestBody West west) {
		west.name = west.name += "~������";
		west.nick = west.nick += "~���Ƿ�����";
		west.age = west.age += 250;
		return west;
	}

	@RequestMapping("/movie/{movieName}/{shipId}.htm")
	public String annotationRestBind(
			@PathVariable("movieName") String moveName,
			@PathVariable("shipId") long shipId, Model model) {
		model.addAttribute("movieName", moveName);
		model.addAttribute("shipId", shipId);
		return "annotation/param/movie";
	}
}
