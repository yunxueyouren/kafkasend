package com.changyin58.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.changyin58.result.Result;
import com.changyin58.service.KafkaMessageSendService;

@Controller
@RequestMapping("/URLPrefix")
public class KafkaMessageSendController {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageSendController.class);

	@Autowired
	KafkaMessageSendService kafkaMessageSendService;

	@RequestMapping("/log")
	public String say() {
		return "2";
	}

	@Resource
	private KafkaMessageSendService kafkaMessageSendService1;
	@Resource
	protected HttpServletRequest request;

	@RequestMapping(value = "/logtoserver", method = RequestMethod.POST)
	@ResponseBody
	public Result selectANWithSM() throws UnsupportedEncodingException, IOException {
		Result result = new Result();
		InputStreamReader reader = new InputStreamReader(request.getInputStream(), "UTF-8");
		char[] buff = new char[2048];
		int length = 0;
		if (reader != null) {
			while ((length = reader.read(buff)) != -1) {
				String str = new String(buff, 0, length);
				/* System.out.println(str); */
				Map maps = (Map) JSON.parse(str);
				String checksuml = (String) maps.get("checksuml");
				String checksumc = (String) maps.get("checksumc");
				/*
				 * System.out.println(checksuml); System.out.println(checksumc);
				 */
				if ((checksuml.equals("0")) && (checksumc.equals("0"))) {
					kafkaMessageSendService1.send(str);
					result.setRes("0");
				} else if ((checksuml.equals("0")) && (!checksumc.equals("0"))) {
					result.setRes("1");
				} else if ((!checksuml.equals("0")) && (checksumc.equals("0"))) {
					result.setRes("2");
				} else if ((!checksuml.equals("0")) && (!checksumc.equals("0"))) {
					result.setRes("3");
				}
			}
		}
		return result;
	}

}
