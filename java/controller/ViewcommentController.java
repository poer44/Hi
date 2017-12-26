package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import entity.JsonResult;
import entity.StanVideoComm;
import entity.User;
import entity.Usercollection;
import entity.Videocomment;
import service.IVideocommentService;
@Controller
public class ViewcommentController {
private IVideocommentService videocommentService;

public IVideocommentService getVideocommentService() {
	return videocommentService;
}
@Resource
public void setVideocommentService(IVideocommentService videocommentService) {
	this.videocommentService = videocommentService;
}
@RequestMapping(value="/customer/restCommentJson", method = RequestMethod.GET)
public @ResponseBody JsonResult<StanVideoComm> showVideocomment(int pageSize,int pageNum,
		String sort,String order,HttpServletRequest req) {
	int pn=pageNum/10+1;
	User user=(User)req.getSession().getAttribute("user");
	PageHelper.startPage(pn,pageSize,sort+" "+order);		
	PageInfo<StanVideoComm> page = new PageInfo<StanVideoComm>(videocommentService.uniteVideoComm(user.getUserid()));
	return new JsonResult<StanVideoComm>(page);
}
@RequestMapping(value = "/customer/{commentid}/restCommentJson", method = RequestMethod.DELETE)
public @ResponseBody
JsonResult useridDelete(@PathVariable int commentid) {
	boolean res = videocommentService.deleteVideocomment(commentid);
	if (res) {
		return new JsonResult("删除成功");
	} else {
		return new JsonResult(JsonResult.ERROR, "删除失败！");
	}
}
@RequestMapping("/{userid}/getCommentPage")
public @ResponseBody JsonResult<Videocomment> getPage(@PathVariable String userid){
	PageHelper.startPage(1,3);
	List<Videocomment> videocomment = videocommentService.findAllVideocomment(userid);
	PageInfo<Videocomment> page = new PageInfo<Videocomment>(videocomment);
	return new JsonResult<Videocomment>(page);
}
}

