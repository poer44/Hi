package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import entity.JsonResult;
import entity.Video;
import service.SearchService;
@Controller
public class SearchController {
	private SearchService searchService;

	public SearchService getSearchService() {
		return searchService;
	}
	@Resource
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	/*	@RequestMapping(value="/searchViewdemo",method = RequestMethod.POST)
	public JsonResult<Video> showVideos(Model model){
		System.out.println("****");
		List<Video> videos=searchService.findBytype(3);
		JSONArray ja = new JSONArray();
		for(Video vi:videos){
		JSONObject jo = JSONObject.fromObject(vi);
		ja.add(jo);
		}
		System.out.println(ja);
		//model.addAttribute("videos",ja);
		//return "searchView";
		return new JsonResult(videos);
	}*/

	@RequestMapping(value = "/searchViewtype", method = RequestMethod.POST)
	public @ResponseBody void findByTypeid(String pagenu,String MorA,String typeid,String namelike,HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		namelike="%"+namelike+"%";
		PageHelper.startPage(Integer.parseInt(pagenu), 8);
		List<Video> videos;
		if(MorA.equals("m1")){
			if(typeid.equals("0"))
				videos=searchService.findByName(namelike);
			else
				videos=searchService.findBytype(MorA,Integer.parseInt(typeid),namelike);
		}else{
			if(typeid.equals("0"))
				videos=searchService.findByAuthor(namelike);
			else
				videos=searchService.findBytype(MorA,Integer.parseInt(typeid),namelike);
		}
		PageInfo<Video> page=new PageInfo<Video>(videos);
		
		int total=(int)page.getTotal();//总记录数
		int videopage=page.getPages();//页数
		JSONObject VideoMes=new JSONObject();
		VideoMes.put("total", total);
		VideoMes.put("videopage", videopage);
		
		JSONArray ja = new JSONArray();
		for(Video vi:videos){
			JSONObject jo = JSONObject.fromObject(vi);
			ja.add(jo);
		}
		ja.add(VideoMes);
		resp.getWriter().print(ja);
	}
	@RequestMapping(value = "/searchViewname", method = RequestMethod.POST)
	public @ResponseBody void findByNames(String name,HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		name="%"+name+"%";
		PageHelper.startPage(1, 8);
		List<Video> videos=searchService.findByName(name);
		PageInfo<Video> page=new PageInfo<Video>(videos);
		
		int total=(int)page.getTotal();//总记录数
		int videopage=page.getPages();//页数
		JSONObject VideoMes=new JSONObject();
		VideoMes.put("total", total);
		VideoMes.put("videopage", videopage);
		
		JSONArray ja = new JSONArray();
		for(Video vi:videos){
			JSONObject jo = JSONObject.fromObject(vi);
			ja.add(jo);
		}
		ja.add(VideoMes);
		//System.out.println(ja.get(ja.size()-1));
		//ja.remove(ja.size()-1);
		//System.out.println(ja);
		resp.getWriter().print(ja);
	}

	@RequestMapping(value = "/searchMorA", method = RequestMethod.POST)
	public @ResponseBody void findByMorA(String MorA,String name,HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		name="%"+name+"%";
		PageHelper.startPage(1, 8);
		List<Video> videos;
		if(MorA.equals("m1"))
			videos=searchService.findByName(name);
		else
			videos=searchService.findByAuthor(name);
		
		PageInfo<Video> page=new PageInfo<Video>(videos);
		
		int total=(int)page.getTotal();//总记录数
		int videopage=page.getPages();//页数
		JSONObject VideoMes=new JSONObject();
		VideoMes.put("total", total);
		VideoMes.put("videopage", videopage);
		
		JSONArray ja = new JSONArray();
		for(Video vi:videos){
			JSONObject jo = JSONObject.fromObject(vi);
			ja.add(jo);
		}
		ja.add(VideoMes);
		resp.getWriter().print(ja);
	}
}
