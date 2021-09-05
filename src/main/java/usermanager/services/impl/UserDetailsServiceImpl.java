package usermanager.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import usermanager.models.ERole;
import usermanager.models.RoleEntity;
import usermanager.models.UserEntity;
import usermanager.repository.RoleRepository;
import usermanager.repository.UserRepository;
import usermanager.services.UserDetailsImpl;
import usermanager.services.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	public Set<RoleEntity> setRoles(Set<String> roles) {

		Set<RoleEntity> roleEntity = new HashSet<>();

		if (roles == null) {
			RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roleEntity.add(userRole);
		} else {
			roles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roleEntity.add(adminRole);
					break;
				case "mod":
					RoleEntity modRole = roleRepository.findByName(ERole.ROLE_LEADER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roleEntity.add(modRole);

					break;
				default:
					RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roleEntity.add(userRole);
				}
			});
		}
		return roleEntity;
	}

	@Override
	public UserEntity save(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	@Override
	public void delete(long[] ids) {
		for (long items : ids) {
			userRepository.deleteById(items);
		}
	}

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity findByUserName(String username) {
		return userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with username: " + username));
	}

}
