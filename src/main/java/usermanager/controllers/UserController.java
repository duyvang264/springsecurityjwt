package usermanager.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import usermanager.models.RoleEntity;
import usermanager.models.TaskEntity;
import usermanager.models.UserEntity;
import usermanager.payload.request.UserRequest;
import usermanager.repository.TaskRepository;
import usermanager.repository.UserRepository;
import usermanager.services.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/manager")
@Api
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserDetailsServiceImpl userService;

	@PutMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public UserEntity updateUser(@Valid @RequestBody UserRequest user) {

		UserEntity result = userRepository.findById(user.getId()).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with username: " + user.getUsername()));
		
		TaskEntity taskEntity = taskRepository.findById(user.getTaskId())
				.orElseThrow(() -> new RuntimeException("Error: Task is not found."));
		
		Set<RoleEntity> roles = userService.setRoles(user.getRole());

		result = new UserEntity(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), roles,
				taskEntity);

		return userService.save(result);
	}

	@GetMapping("/user")
	//@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserEntity> getAllUser() {
		return userService.findAll();
	}
	
	@GetMapping("/user-info")
	@PreAuthorize("hasRole('USER')")
	public UserEntity findByUserName(@RequestBody String userName) {
		return userService.findByUserName(userName);
	}
	
	@DeleteMapping("/user")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteUser(@RequestBody long[] ids) {
		userService.delete(ids);
	}
}
