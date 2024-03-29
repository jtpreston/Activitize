package com.activitize.springmvc.Controllers;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activitize.springmvc.Models.JsonResponse;
import com.activitize.springmvc.Models.Reply;
import com.activitize.springmvc.Services.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	ReplyService service;

	@RequestMapping(value = "/createReply", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse createReply(@RequestBody Reply reply) {
		service.createReply(reply);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/deleteReply", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteReply(@RequestBody Reply reply) {
		service.deleteReply(reply);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/editReply", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse editReply(@RequestBody Reply reply) {
		service.editReply(reply);
		return new JsonResponse("OK","");
	}
	
}
