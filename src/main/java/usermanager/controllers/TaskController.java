package usermanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import usermanager.models.TaskEntity;
import usermanager.services.impl.TaskServiceImpl;

@RestController
@RequestMapping("/manager")
@Api
public class TaskController {

	@Autowired
	private TaskServiceImpl taskService;

	@GetMapping("/task")
	@PreAuthorize("hasRole('ADMIN') or hasRole('LEADER')")
	public List<TaskEntity> findAll() {
		return taskService.findAll();
	}
	
	@PostMapping("/task")
	@PreAuthorize("hasRole('ADMIN') or hasRole('LEADER')")
	public TaskEntity create(@RequestBody TaskEntity task) {
		return taskService.save(task);
	}

	@PutMapping("/task")
	@PreAuthorize("hasRole('ADMIN') or hasRole('LEADER')")
	public TaskEntity update(@RequestBody TaskEntity task) {
		return taskService.save(task);
	}
	
	@DeleteMapping("/task")
	@PreAuthorize("hasRole('ADMIN') or hasRole('LEADER')")
	public void delete(@RequestBody long[] ids) {
		
		taskService.delete(ids);
	}
}
