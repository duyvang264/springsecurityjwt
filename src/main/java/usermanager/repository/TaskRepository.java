package usermanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import usermanager.models.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
	Optional<TaskEntity> findById(Long id);
	
	TaskEntity findTaskById(Long id);
}
