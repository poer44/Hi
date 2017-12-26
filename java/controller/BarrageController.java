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

import entity.Barrage;
import entity.JsonResult;
import entity.User;
import service.IBarrageService;

@Controller
public class BarrageController {
private IBarrageService barrageService;

public IBarrageService getBarrageService() {
	return barrageService;
}
@Resource
public void setBarrageService(IBarrageService barrageService) {
	this.barrageService = barrageService;
}
@RequestMapping(value="/customer/restBarrageJson", method = RequestMethod.GET)
public @ResponseBody JsonResult<Barrage> showBarrage(int pageSize,int pageNum,
		String sort,String order,HttpServletRequest req) {
	int pn=pageNum/10+1;
	User user=(User) req.getSession().getAttribute("user");
	PageHelper.startPage(pn,pageSize,sort+" "+order);		
	PageInfo<Barrage> page = new PageInfo<Barrage>(barrageService.uniteBarrage(user.getUserid()));
	return new JsonResult<Barrage>(page);
}
@RequestMapping(value = "/customer/{barrageid}/restBarrageJson", method = RequestMethod.DELETE)
public @ResponseBody
JsonResult barrageidDelete(@PathVariable int barrageid) {
	boolean res = barrageService.deleteBarrage(barrageid);
	if (res) {
		return new JsonResult("删除成功");
	} else {
		return new JsonResult(JsonResult.ERROR, "删除失败！");
	}
}
@RequestMapping("/{userid}/getBarragePage")
public @ResponseBody JsonResult<Barrage> getPage(@PathVariable String userid){
	PageHelper.startPage(1,3);
	List<Barrage> barrage = barrageService.findAllBarrage(userid);
	PageInfo<Barrage> page = new PageInfo<Barrage>(barrage);
	return new JsonResult<Barrage>(page);
}
}
