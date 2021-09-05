package usermanager.services;

import java.util.List;

import usermanager.models.TaskEntity;

public interface TaskService {
	
	TaskEntity save(TaskEntity task);

	TaskEntity update(TaskEntity task);

	void delete(long[] ids);

	List<TaskEntity> findAll();
}
