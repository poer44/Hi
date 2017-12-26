package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UserMapper;
import entity.JsonResult;
import entity.User;
import service.IUserService;
import service.impl.UserService;

@Controller
public class UserUpdateController {
private IUserService userService;

public IUserService getUserService() {
	return userService;
}
@Resource
public void setUserService(IUserService userService) {
	this.userService = userService;
}
@RequestMapping("/customer/{userid}/{password}/{username}/{sex}/{birthday}/{address}/{emotion}/{email}/{sign}/userupdate")
public @ResponseBody String userUpdate(@PathVariable String userid,@PathVariable String password,@PathVariable String username,
		@PathVariable String sex,@PathVariable String birthday,@PathVariable String address,@PathVariable String emotion
		,@PathVariable String email,@PathVariable String sign){
	boolean res = userService.updateUser(userid, password, username, sex, birthday, address, emotion, email, sign);
	return "redirect:/customer/1.htm";
}

}
