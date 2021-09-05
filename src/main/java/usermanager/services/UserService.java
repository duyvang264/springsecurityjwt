package usermanager.services;

import java.util.List;

import usermanager.models.UserEntity;

public interface UserService {

	UserEntity save(UserEntity userEntity);
	//UserEntity update(UserEntity userEntity);
	void delete(long[] ids);
	List<UserEntity> findAll();
	UserEntity findByUserName(String userName);
	
}
