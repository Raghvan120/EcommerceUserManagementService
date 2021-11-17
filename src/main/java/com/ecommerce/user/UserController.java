package com.ecommerce.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.response.ApiResponse;

@RestController
@RequestMapping("/v1/api/")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("user")
	public ResponseEntity<ApiResponse> addUser(@RequestBody UserVO userVO){
		  return new ResponseEntity<>(new ApiResponse("Account created Successfully", null,  this.userService.addUser(userVO), HttpStatus.CREATED.value(), null), HttpStatus.OK);
	}
	
	@GetMapping("users/verified")
	public ResponseEntity<ApiResponse> getVerifiedUsers(){
		  return new ResponseEntity<>(new ApiResponse("Users fetched Successfully", null,  this.userService.findAllVerifiedUsers(), HttpStatus.OK.value(), null), HttpStatus.OK);
	}
	
	@PutMapping("/user/{id}/change/password")
	public ResponseEntity<ApiResponse> changePassword(@PathVariable("id") Long userId,
			@RequestBody ChangePasswordVO changePasswordVO) throws Exception {
		this.userService.changePassword(userId, changePasswordVO);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Password changed Successfully",null, null,HttpStatus.OK.value(), null), HttpStatus.OK);
	}

//	@PostMapping("/user/{to}/mail")
//	public ResponseEntity<ApiResponse> sendMail(@PathVariable("to") String to) throws MessagingException {
//		new MailSender().sendPlainMail(javaMailSender, to, "Testing mail configuration of Java Stack");
//		return new ResponseEntity<ApiResponse>(
//				new ApiResponse(HttpStatus.OK.value(), "Mail sent Successfully", null, null), HttpStatus.OK);
//	}

}
