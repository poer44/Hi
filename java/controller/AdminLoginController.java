package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import entity.Admin;
import service.IAdminService;
import utils.MD5Util;

@Controller
@SessionAttributes({ "adminid" })
public class AdminLoginController {

	private IAdminService adminService;

	public IAdminService getAdminService() {
		return adminService;
	}

	@Resource
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping("/adminLogin")
	public String login(String adminid, String password, Model model) {
		String ckm=password;
		password = MD5Util.MD5Encode(password, null);
		Admin admin = adminService.Login(adminid, password);
		if (admin != null) {
			model.addAttribute("admin", admin);
			model.addAttribute("adminid", adminid);
			model.addAttribute("password", ckm);
			return "manage/index";
		} else {
			return "manage/pwderror";
		}
	}
	@RequestMapping("/adminhome")
	public String home(Model model) {
		Integer countuser=adminService.countuser();
		model.addAttribute("countuser",countuser);
		Integer countcomment=adminService.countcomment();
		model.addAttribute("countcomment",countcomment);
		Integer countvideo=adminService.countvideo();
		model.addAttribute("countvideo",countvideo);
		Integer countbarrage=adminService.countbarrage();
		model.addAttribute("countbarrage",countbarrage);
		Integer countsubmission=adminService.countsubmission();
		model.addAttribute("countsubmission",countsubmission);
		Integer countnewuser=adminService.countnewuser();
		model.addAttribute("countnewuser",countnewuser);
			return "manage/home";
	}
}
