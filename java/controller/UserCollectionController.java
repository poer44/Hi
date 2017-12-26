package controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import entity.Video;
import service.IUserCollectionService;

@Controller
public class UserCollectionController {

	private IUserCollectionService usercollectionService;

	public IUserCollectionService getAdminService() {
		return usercollectionService;
	}

	@Resource
	public void setUserCollectionService(IUserCollectionService usercollectionService) {
		this.usercollectionService = usercollectionService;
	}

	@RequestMapping("/customer/{page}/{userid}/collection")
	public String contribute(@PathVariable int page,@PathVariable String userid,
			Model model) {
		    PageHelper.startPage(page, 100);//这个分页pages永远是1？
			List<Video> v = usercollectionService.findbyvid(userid);
			PageInfo<Video> pageInfo = new PageInfo<Video>(v);
			int pages = pageInfo.getPages();
			boolean isfirst = pageInfo.isIsFirstPage();
			boolean islast = pageInfo.isIsLastPage();
			model.addAttribute("video", v);
			model.addAttribute("length",v.size());
			model.addAttribute("pages", pages);
			model.addAttribute("pageNum", page);
			model.addAttribute("isfirst", isfirst);
			model.addAttribute("islast", islast);
			model.addAttribute("typeid", 0);
			model.addAttribute("userid", userid);
			return "customer/CollectionIframe";
	}
	@RequestMapping("/customer/{vid}/{uid}/dislike")
	public String dislike(@PathVariable String vid, @PathVariable String uid,Model model) {
		boolean res = usercollectionService.delUsercollection(vid,uid);
		return "redirect:/customer/templ.jsp";
	}
}
