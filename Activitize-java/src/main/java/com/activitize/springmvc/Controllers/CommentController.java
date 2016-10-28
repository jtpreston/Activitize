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

import com.activitize.springmvc.Models.Comment;
import com.activitize.springmvc.Models.JsonResponse;
import com.activitize.springmvc.Services.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
    CommentService service;
	
	@RequestMapping(value = "/createComment", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse createComment(@RequestBody Comment comment) {
		service.createComment(comment);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/deleteComment", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteComment(@RequestBody Comment comment) {
		service.deleteComment(comment);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/editComment", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse editComment(@RequestBody Comment comment) {
		service.editComment(comment);
		return new JsonResponse("OK","");
	}

}
