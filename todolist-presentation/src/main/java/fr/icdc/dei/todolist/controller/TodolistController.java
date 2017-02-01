package fr.icdc.dei.todolist.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.icdc.dei.todolist.service.TodolistService;

@Controller
@RequestMapping("/")
public class TodolistController {
	
	private static final String TASKS_HTTP_ATTR = "tasks";
	
	@Autowired
	private TodolistService todolistService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView page = new ModelAndView("Home");
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		 
		page.addObject(TASKS_HTTP_ATTR, todolistService.listTasks());
		page.addObject("now", dateFormat.format(date));
		return page;
	}
	
	@RequestMapping(value = "create", method=RequestMethod.GET)
	public ModelAndView renderCreateTaskForm() {
		ModelAndView page = new ModelAndView("TaskForm");
		page.addObject("status", todolistService.listTaskStatus());
		return page;
	}
	
	@RequestMapping(value = "create", method=RequestMethod.POST)
	public ModelAndView createTask(@RequestParam String taskName, @RequestParam("status") int statusId) {
		ModelAndView page = new ModelAndView("Home");
		todolistService.addTask(taskName, statusId);
		page.addObject(TASKS_HTTP_ATTR, todolistService.listTasks());
		return page;
	}
	
	@RequestMapping(value = "finish", method=RequestMethod.POST)
	public String finishTask(@RequestParam int taskId) {
		todolistService.finishTask(taskId);
		return "redirect:/";
	}
}
