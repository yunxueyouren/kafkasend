package com.changyin58.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.changyin58.entity.Chan;
import com.changyin58.service.LogConfigService;

@Controller
@RequestMapping("/URLPrefix")
public class LogConfigController {
	
	@Autowired
	LogConfigService logConfigService;

	@RequestMapping("/logconfig")
	@ResponseBody
	public JSONObject doLogin(Chan chan, HttpSession session) {

		String cha = chan.getChannel();
		Integer channel = Integer.valueOf(cha);
		/*
		 * System.out.println(channel); Integer channel = 1;
		 */

		Integer active = logConfigService.getActiveByChannel(channel);
		/* System.out.println(active); */

		List<String> list = logConfigService.getFingerprintByChannel(channel);
		/*
		 * for(int i = 0;i < list.size();i++){
		 * System.out.print(list.get(i).getFingerprint()); }
		 */

		List<String> list1 = logConfigService.getStatusByChannel(channel);
		/*
		 * for(int i = 0;i < list1.size();i++){
		 * System.out.print(list1.get(i).getStatus()); }
		 */

		Map<String, Object> map = new HashMap<String, Object>();

		/*
		 * map.put("active", active); map.put("content",list ); list.addAll(list1);
		 * JSONObject jsonObj = new JSONObject(); jsonObj.put("active", active);
		 * jsonObj.put("content", list); return jsonObj;
		 */
		map.put("fingerprint", list);
		map.put("status", list1);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("active", active);
		jsonObj.put("content", map);
		return jsonObj;
	}

}
