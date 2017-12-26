package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.User;
import entity.Video;
import service.IDisplayService;
import service.IUserService;

@Controller
public class DisplayController {

	private IUserService userService;
	private IDisplayService displayService;

	public IUserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public IDisplayService getDisplayService() {
		return displayService;
	}

	@Resource
	public void setIDisplayService(IDisplayService displayService) {
		this.displayService = displayService;
	}
	
	@RequestMapping(value = "/addPlayOne", method = RequestMethod.POST)
	public void addPlayOne(@RequestParam String videoid,HttpServletRequest req,HttpServletResponse resp) throws IOException {
			req.setCharacterEncoding("utf-8");
			PrintWriter out=resp.getWriter();
				out.print(displayService.updateVideoPlay(videoid));
	}
	
	@RequestMapping(value = "/typeTop", method = RequestMethod.POST)
	public @ResponseBody void typeTop(String typeid,HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		List<Video> videos=displayService.TypeTopLimit(typeid);
		JSONArray ja = new JSONArray();
		for(Video vi:videos){
		JSONObject jo = JSONObject.fromObject(vi);
		ja.add(jo);
		}
		resp.getWriter().print(ja);
	}
	
	@RequestMapping(value = "/topFirst", method = RequestMethod.POST)
	public @ResponseBody void topFirst(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		List<Video> videos=displayService.TopPlayByType();
		JSONArray ja = new JSONArray();
		for(Video vi:videos){
		JSONObject jo = JSONObject.fromObject(vi);
		ja.add(jo);
		}
		resp.getWriter().print(ja);
	}
	
	@RequestMapping(value = "/ranLimit", method = RequestMethod.POST)
	public @ResponseBody void ranLimit(String typeid,HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		List<Video> videos=displayService.TypeRanLimit(typeid);
		JSONArray ja = new JSONArray();
		for(Video vi:videos){
		JSONObject jo = JSONObject.fromObject(vi);
		ja.add(jo);
		}
		resp.getWriter().print(ja);
	}

	@RequestMapping(value = "/searchHot", method = RequestMethod.POST)
	public @ResponseBody void hotPlay(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		List<Video> videos=displayService.PlayDescLimit();
		JSONArray ja = new JSONArray();
		for(Video vi:videos){
		JSONObject jo = JSONObject.fromObject(vi);
		ja.add(jo);
		}
		resp.getWriter().print(ja);
	}
	
	@RequestMapping(value = "/playTop", method = RequestMethod.POST)
	public @ResponseBody void playTop(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		List<Video> videos=displayService.PlayDescLimit();
		JSONArray ja = new JSONArray();
		for(Video vi:videos){
		JSONObject jo = JSONObject.fromObject(vi);
		ja.add(jo);
		}
		resp.getWriter().print(ja);
	}
	
	@RequestMapping(value = "/collTop", method = RequestMethod.POST)
	public @ResponseBody void collTop(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		List<Video> videos=displayService.CollDescLimit();
		JSONArray ja = new JSONArray();
		for(Video vi:videos){
		JSONObject jo = JSONObject.fromObject(vi);
		ja.add(jo);
		}
		resp.getWriter().print(ja);
	}
	
	@RequestMapping(value = "/playVideoAuthor", method = RequestMethod.POST)
	public @ResponseBody User playVideoAuthor(String userid,HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		User user = userService.getUser(userid);
		return user;
	}
	
	@RequestMapping(value = "/whereareufrom", method = RequestMethod.POST)
	public void whereareufrom(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Properties props=System.getProperties();
		resp.getWriter().print(props.getProperty("os.name"));
	}
	
	@RequestMapping(value = "/userinfotop", method = RequestMethod.POST)
	public @ResponseBody User userinfotop(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		User user = (User) req.getSession().getAttribute("user");
		return user;
	}

	@RequestMapping(value = "/userlogout", method = RequestMethod.GET)
	public String userlogout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		return "redirect:/index.html";
	}
	
	@RequestMapping(value = "/userindex", method = RequestMethod.GET)
	public String userindex(HttpServletRequest req, HttpServletResponse resp) {
		return "redirect:/index.html";
	}
	
	@RequestMapping(value = "/userLoginindex", method = RequestMethod.GET)
	public String userLoginindex(HttpServletRequest req, HttpServletResponse resp) {
		return "redirect:/user_login.html";
	}
	
}