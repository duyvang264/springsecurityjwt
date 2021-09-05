package usermanager.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usermanager.models.TaskEntity;
import usermanager.repository.TaskRepository;
import usermanager.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskEntity save(TaskEntity task) {
		TaskEntity result;
		if (task.getId() != null) {
			result = taskRepository.findById(task.getId())
					.orElseThrow(() -> new RuntimeException("Error: Task is not found."));
			result = new TaskEntity(task.getId(),task.getName(), task.getStartDate(), task.getEndDate(), task.getDescription(),
					task.getStatus());
			result = taskRepository.save(result);
		} else {
			result = taskRepository.save(task);
		}
		return result;
	}

	@Override
	public TaskEntity update(TaskEntity task) {
		return null;
	}

	@Override
	public void delete(long[] ids) {
		for (Long items : ids) {
			taskRepository.deleteById(items);
		}
	}

	@Override
	public List<TaskEntity> findAll() {
		return taskRepository.findAll();
	}

}
