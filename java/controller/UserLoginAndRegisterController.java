package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.User;
import service.IUserService;
import utils.MD5Util;
import utils.PhoneCodeUtils;

@Controller
public class UserLoginAndRegisterController {
	
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/getyzm",method=RequestMethod.GET) /*注册时获取验证码*/
	public @ResponseBody User getyzm(String tel) {    
		User user=new User();
		if (tel != "" && tel != null) {
			if (userService.UseridExist(tel) == null) {
				String yzm = PhoneCodeUtils.getVerifyCode();
				System.out.println(yzm);
				if(yzm==""){
					user.setSign(tel+":aaaaaa");	
				}else{
					user.setSign(tel+":"+yzm);	
				}
				/*try {
					SmsNotificationUtils.sendMessage(tel, yzm);
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				return user;
			} else {
				user.setSign("Existed");
				return user;
			}
		} else {
			user.setSign("notel");
			return user;
		}
	}
	@RequestMapping(value="/logingetyzm",method=RequestMethod.GET) /*登陆获取验证码*/
	public @ResponseBody User logingetyzm(String tel) {    
		User user=new User();
		if (tel != "" && tel != null) {
			if (userService.UseridExist(tel) == null) {
				user.setSign("noExist");
				return user;
			} else {
				String yzm = PhoneCodeUtils.getVerifyCode();
				System.out.println(yzm);
				if(yzm==""){
					user.setSign(tel+":aaaaaa");	
				}else{
					user.setSign(tel+":"+yzm);	
				}
				/*try {
					SmsNotificationUtils.sendMessage(tel, yzm);
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				return user;
			}
		} else {
			user.setSign("notel");
			return user;
		}
	}
	
	@RequestMapping(value="/userLogin1",method=RequestMethod.POST)	/*用户密码登陆*/
	public String login1(String tel1,String password,Model model,HttpServletRequest req){
		if (userService.UseridExist(tel1) == null) {
			model.addAttribute("msg", "账号不存在~！");
			return "customer/loginnote";
		} else {
			User user = userService.Login1(tel1,MD5Util.MD5Encode(password, null));
			if (user != null) {
				String url=req.getParameter("requesturl");
				req.getSession().setAttribute("user", user);
				if(url!=""&&url!=null&&!url.contains("index")){
					return "redirect:"+url.substring(3,url.length());
				}
				else return "redirect:/index.html";
			}
			model.addAttribute("msg", "账号或密码错误~！");
			return "customer/loginnote";
		}
	}
	
	@RequestMapping(value="/userLogin2",method=RequestMethod.POST)	/*用户手机登陆*/
	public String login2(String tel2,String showyzm,String yzm,Model model,HttpServletRequest req){
		System.out.println(showyzm);
		String [] date=showyzm.split(":");
		String tel=date[0];
		String trueyzm=date[1];
		if(tel.equals(tel2)&&trueyzm.equals(yzm)){
			User user=userService.getUser(tel2);
			req.getSession().setAttribute("user", user);
			return "redirect:/index.html";
		}else{
			model.addAttribute("msg", "手机号或获取验有误~！");
			return "customer/loginnote";
		}
	}

	@RequestMapping("/userRegister")	/*用户注册*/
	public  String userRegister(String username, String telephone,String showyzm, String yzm,String email,
			String birthday, String password,Model model){
		String imgurl="user_pic\\user.jpg";
		User user = new User(telephone, password, username, birthday, email,imgurl);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		user.setRegistertime(df.format(new Date()).toString());
		if (userService.UseridExist(telephone) != null) {
			model.addAttribute("msg", "该手机号已被注册！");
			return "customer/registernote";
		} else {
			if (showyzm.split(":")[0].equals(telephone)&&showyzm.split(":")[1].equals(yzm)) {
				user.setPassword(MD5Util.MD5Encode(password, null));
				if (userService.Register(user)) {
					model.addAttribute("msg", "注册成功");
					return "customer/registernote";
				}
			} else {
				model.addAttribute("msg", "手机号或验证码有误!");
				return "customer/registernote";
			}
		}
		return "customer/registernote";
	}
	
}
