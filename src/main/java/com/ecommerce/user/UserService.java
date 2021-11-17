package com.ecommerce.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.ResourceNotFound;
import com.ecommerce.role.Role;
import com.ecommerce.role.RoleRepository;

@Service
public class UserService implements IUserService {
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserVO addUser(UserVO userVO) {
		Role role = this.roleRepository.findById(userVO.getRoleVO().getId()).orElseThrow(() -> new ResourceNotFound("Role Not found"));
		User user = userVO.convertToUser();
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.addRole(role);
		return this.userRepository.save(user).convertToUserVO();
	}

	@Override
	public List<UserVO> findAllVerifiedUsers() {
		return this.userListToUserVOList(this.userRepository.findByIsVerifiedTrue());
	}

	@Override
	public UserVO findById(Long userId) {
		return null;
	}

	@Override
	public UserVO updateUser(UserVO user) {
		return null;
	}

	@Override
	public void updateUserRole(Integer roleId) {

	}

	@Override
	public void lockUser(Long userId) {

	}

	@Override
	public void disableUser(Long userId) {

	}

	@Override
	public void verifiyUser(Long userId) {
	
	}
	
	private User findByUserId(Long userId) {
		return this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFound("User not found"));
	}
	
	@Override
	public void changePassword(Long userId, ChangePasswordVO changePasswordVO) throws Exception {
		User user = this.findByUserId(userId);
		if (this.passwordEncoder.matches(changePasswordVO.getOldPassword(), user.getPassword())) {
			user.setPassword(this.passwordEncoder.encode(changePasswordVO.getNewPassword()));
			this.userRepository.save(user);
		} else
			throw new ResourceNotFound("Old password is not correct");
	}
	
	private List<UserVO> userListToUserVOList(List<User> users){
		return users.stream().map(User::convertToUserVO).collect(Collectors.toList());
	}
}
