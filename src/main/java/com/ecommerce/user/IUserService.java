package com.ecommerce.user;

import java.util.List;

public interface IUserService {
	
	UserVO addUser(UserVO user);
	List<UserVO> findAllVerifiedUsers();
	UserVO findById(Long userId);
	UserVO updateUser(UserVO user);
	void updateUserRole(Integer roleId);
	void lockUser(Long userId);
	void disableUser(Long userId);
	void verifiyUser(Long userId);
	void changePassword(Long userId, ChangePasswordVO changePasswordVO) throws Exception;

}
