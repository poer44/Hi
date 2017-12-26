package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import service.IAdminService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import entity.Admin;
import entity.Barrage;
import entity.JsonResult;
import entity.Submission;
import entity.User;
import entity.Video;
import utils.DateUtil;
import entity.Videocomment;
import utils.GetImgUrl;
import utils.HtmlResourceView;
import utils.MD5Util;
import utils.StringUtil;

@Controller
public class AdminopController {

	private IAdminService adminService;

	public IAdminService getAdminService() {
		return adminService;
	}

	@Resource
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping("/*")
	public String doForward(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		String url = servletPath.replace(".htm", "");
		System.out.println("调用doforward");
		System.out.println(url);
		return url;
	}

	@RequestMapping(value = "/manage/restUserJson", method = RequestMethod.GET)
	public @ResponseBody JsonResult<User> showUser(int pageSize,int pageNum, String sort, String order, String userid,String username) {
		int pn=pageNum/5+1;
		PageHelper.startPage(pn, pageSize, sort + " " + order);
		PageInfo<User> page = new PageInfo<User>(adminService.findUserByCondition(userid, username));
		return new JsonResult<User>(page);
	}
	
	@RequestMapping("/adminlogout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "admin_login";
	}


	@RequestMapping("/{sid}/getOne")
	public @ResponseBody JsonResult<User> getOneDemo(@PathVariable String userid) {
		User user = adminService.findUserById(userid);
		return new JsonResult<User>(user);
	}

	@RequestMapping("/getPage")
	public @ResponseBody JsonResult<User> getPage() {
		PageHelper.startPage(1, 3);
		List<User> user = adminService.findAllUser();
		PageInfo<User> page = new PageInfo<User>(user);
		return new JsonResult<User>(page);
	}

	@RequestMapping(value = "/manage/{userid}/restUserJson", method = RequestMethod.DELETE)
	public @ResponseBody JsonResult stuDelete(@PathVariable String userid) {
		boolean res = adminService.deleteUser(userid);
		if (res) {
			return new JsonResult("删除成功！~");
		} else {
			return new JsonResult(JsonResult.ERROR, "删除失败！~");
		}
	}

	@RequestMapping(value = "/manage/uploadvideo", method = RequestMethod.POST)
	public String uploadvideo(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		// 1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决上传文件名的中文乱码
		upload.setHeaderEncoding("UTF-8");
		factory.setSizeThreshold(1024 * 1024 * 500);// 设置内存的临界值为500K
		File linshi = new File("E:\\linshi");// 当超过500K的时候，存到一个临时文件夹中
		factory.setRepository(linshi);
		upload.setSizeMax(1024 * 1024 * 500);// 设置上传的文件总的大小不能超过500M
		try {
			// 1. 得到 FileItem 的集合 items
			List<FileItem> /* FileItem */items = upload.parseRequest(request);
			// 2. 遍历 items:
			String mid = items.get(0).getString("utf-8");
			String videoname = items.get(1).getString("utf-8");
			int typeid = Integer.parseInt(items.get(2).getString("utf-8"));
			String filesize = items.get(3).getString("utf-8");
			String format = items.get(4).getString("utf-8");
			String videolength = items.get(5).getString("utf-8");
			String fileUrl = "";
			String imgUrl = "";
			String fileName = "";
			System.out.println(mid);
			if (!mid.equals("null")) {
				String manageid = "管理员" + mid;
				System.out.println(manageid);
				for (FileItem item : items) {
					if (!item.isFormField()) {
						fileName = item.getName();
						InputStream in = item.getInputStream();
						byte[] buffer = new byte[1024];
						int len = 0;
						fileUrl = request.getSession().getServletContext()
								.getRealPath("/")
								+ "uploadfiles\\" + fileName;// 文件最终上传的位置
						OutputStream out = new FileOutputStream(fileUrl);
						while ((len = in.read(buffer)) != -1) {
							out.write(buffer, 0, len);
						}
						out.close();
						in.close();
					}
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
				String uptime = df.format(new Date()).toString();
				String hou = fileName.substring(fileName.lastIndexOf("."),
						fileName.length());

				File file = new File(fileUrl); // 指定文件名及路径
				String filename = file.getAbsolutePath();
				if (filename.indexOf(".") >= 0) {
					filename = filename
							.substring(0, filename.lastIndexOf("\\"));// 文件上传路径
				}
				file.renameTo(new File(filename + "\\" + mid + uptime + hou)); // 改名
				GetImgUrl.processImg(new File(filename + "\\" + mid + uptime
						+ hou).getAbsolutePath(),
						"src/main/java/utils/ffmpeg.exe");
				Video video = new Video(videoname, manageid, "uploadfiles\\"
						+ mid + uptime + ".jpg", "uploadfiles\\" + mid + uptime
						+ hou, filesize, format, 0, 0, uptime, typeid,
						videolength);
				if (adminService.addVideo(video)) {
					model.addAttribute("msg", "上传成功");
				} else {
					model.addAttribute("msg", "上传失败");
				}
			} else {
				model.addAttribute("msg", "上传失败,尚未登录");
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return "manage/upload";
	}

	@RequestMapping("/manage/videocomment/{page}")
	public String videocomment(@PathVariable int page, Model model) {
		PageHelper.startPage(page, 4);
		List<Video> video = adminService.findAllVideo();
		PageInfo<Video> pageInfo = new PageInfo<Video>(video);
		System.out.println("manage********************");
		System.out.println(pageInfo);
		System.out.println("********************");
		int pages = pageInfo.getPages();
		boolean isfirst = pageInfo.isIsFirstPage();
		boolean islast = pageInfo.isIsLastPage();
		model.addAttribute("video", video);
		model.addAttribute("length", video.size());
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", page);
		model.addAttribute("isfirst", isfirst);
		model.addAttribute("islast", islast);
		return "manage/videocomment";
	}

	@RequestMapping("/manage/contribute/{page}/{typeid}")
	public String contribute(@PathVariable int page, @PathVariable int typeid,
			Model model) {
		if (typeid == 0) {
			PageHelper.startPage(page, 4);
			List<Submission> sub = adminService.findallsubmission();
			PageInfo<Submission> pageInfo = new PageInfo<Submission>(sub);
			int pages = pageInfo.getPages();
			boolean isfirst = pageInfo.isIsFirstPage();
			boolean islast = pageInfo.isIsLastPage();
			model.addAttribute("submission", sub);
			model.addAttribute("length", sub.size());
			model.addAttribute("pages", pages);
			model.addAttribute("pageNum", page);
			model.addAttribute("isfirst", isfirst);
			model.addAttribute("islast", islast);
			model.addAttribute("typeid", 0);
		} else if (typeid == 1) {
			PageHelper.startPage(page, 4);
			List<Submission> sub = adminService.findbytype("待审核");
			PageInfo<Submission> pageInfo = new PageInfo<Submission>(sub);
			int pages = pageInfo.getPages();
			boolean isfirst = pageInfo.isIsFirstPage();
			boolean islast = pageInfo.isIsLastPage();
			model.addAttribute("submission", sub);
			model.addAttribute("length", sub.size());
			model.addAttribute("pages", pages);
			model.addAttribute("pageNum", page);
			model.addAttribute("isfirst", isfirst);
			model.addAttribute("islast", islast);
			model.addAttribute("typeid", 1);
		} else if (typeid == 2) {
			PageHelper.startPage(page, 4);
			List<Submission> sub = adminService.findbytype("已通过");
			PageInfo<Submission> pageInfo = new PageInfo<Submission>(sub);
			int pages = pageInfo.getPages();
			boolean isfirst = pageInfo.isIsFirstPage();
			boolean islast = pageInfo.isIsLastPage();
			model.addAttribute("submission", sub);
			model.addAttribute("length", sub.size());
			model.addAttribute("pages", pages);
			model.addAttribute("pageNum", page);
			model.addAttribute("isfirst", isfirst);
			model.addAttribute("islast", islast);
			model.addAttribute("typeid", 2);
		} else if (typeid == 3) {
			PageHelper.startPage(page, 10);
			List<Submission> sub = adminService.findbytype("未通过");
			PageInfo<Submission> pageInfo = new PageInfo<Submission>(sub);
			int pages = pageInfo.getPages();
			boolean isfirst = pageInfo.isIsFirstPage();
			boolean islast = pageInfo.isIsLastPage();
			model.addAttribute("submission", sub);
			model.addAttribute("length", sub.size());
			model.addAttribute("pages", pages);
			model.addAttribute("pageNum", page);
			model.addAttribute("isfirst", isfirst);
			model.addAttribute("islast", islast);
			model.addAttribute("typeid", 3);
		}
		return "manage/contribute";
	}

	@RequestMapping("/manage/admin_cpwd")
	public String adminCpwd(String oldpwd, String newpwd, String adminid,
			Model model) {
		Admin admin = new Admin(adminid, newpwd);
		admin.setPassword(MD5Util.MD5Encode(newpwd, null));
		adminService.updateAdmin(admin);
		return "manage/cpwdnote";
	}

	@RequestMapping("/manage/{videoid}/delvideo")
	public String delvideo(@PathVariable String videoid, Model model) {
		int id = Integer.parseInt(videoid);
		boolean res = adminService.deleteVideo(videoid);
		return "redirect:/manage/videocomment/1.htm";
	}

	@RequestMapping("/manage/{state}/{subid}/subpass")
	public String subpass(@PathVariable int state, @PathVariable int subid,
			Model model) {
		Submission sub = adminService.findsubbyid(subid);
		if (state == 1) {// 通过
			sub.setState("已通过");
			adminService.updatesubstate(sub);
			Video v = new Video(sub.getVideoname(), "用户" + sub.getUserid(),
					sub.getImgurl(), sub.getFileurl(), sub.getFilesize(),
					sub.getFormat(), 0, 0, sub.getSubmissiontime(),
					sub.getTypeid(), sub.getVideolength());
			adminService.addsubtovideo(v);
		} else if (state == 0) {// 未通过
			sub.setState("未通过");
			adminService.updatesubstate(sub);
			File file1 = new File("src\\main\\webapp\\" + sub.getFileurl());
			File file2 = new File("src\\main\\webapp\\" + sub.getImgurl());
			if (file1.exists() == true && file2.exists() == true) {
				file1.delete();
				file2.delete();
			}
		}
		return "redirect:/manage/videocomment/1.htm";
	}

	@RequestMapping("/manage/{videoid}/commentview")
	public String commentview(@PathVariable int videoid, Model model) {
		List<Videocomment> list = adminService.findcomment(videoid);
		model.addAttribute("comment", list);
		model.addAttribute("length", list.size());
		return "manage/commentview";
	}

	@RequestMapping("/manage/{videoid}/barrageview")
	public String barrageview(@PathVariable int videoid, Model model) {
		List<Barrage> list = adminService.findbarrage(videoid);
		model.addAttribute("barrage", list);
		model.addAttribute("length", list.size());
		return "manage/barrageview";
	}

	@RequestMapping("/manage/{comid}/delcom")
	public String delcom(@PathVariable String comid, Model model) {
		boolean res = adminService.deleteComment(comid);
		return "redirect:/manage/videocomment/1.htm";
	}

	@RequestMapping("/manage/{bagid}/delbag")
	public String delbag(@PathVariable int bagid, Model model) {
		boolean res = adminService.deleteBarrage(bagid);
		return "redirect:/manage/videocomment/1.htm";
	}

	@RequestMapping("/manage/videoinfo/{page}/{typeid}")
	public String videoinfo(@PathVariable int page, @PathVariable int typeid,
			Model model) {
		if (typeid != 0) {
			PageHelper.startPage(page, 4);
			List<Video> video = adminService.findvbytype(typeid);
			PageInfo<Video> pageInfo = new PageInfo<Video>(video);
			int pages = pageInfo.getPages();
			boolean isfirst = pageInfo.isIsFirstPage();
			boolean islast = pageInfo.isIsLastPage();
			model.addAttribute("video", video);
			model.addAttribute("length", video.size());
			model.addAttribute("pages", pages);
			model.addAttribute("pageNum", page);
			model.addAttribute("isfirst", isfirst);
			model.addAttribute("islast", islast);
			model.addAttribute("typeid", typeid);
		} else if (typeid == 0) {
			PageHelper.startPage(page, 4);
			List<Video> video = adminService.findAllVideo();
			PageInfo<Video> pageInfo = new PageInfo<Video>(video);
			int pages = pageInfo.getPages();
			System.out.println(pageInfo);
			boolean isfirst = pageInfo.isIsFirstPage();
			boolean islast = pageInfo.isIsLastPage();
			model.addAttribute("video", video);
			model.addAttribute("length", video.size());
			model.addAttribute("pages", pages);
			model.addAttribute("pageNum", page);
			model.addAttribute("isfirst", isfirst);
			model.addAttribute("islast", islast);
			model.addAttribute("typeid", 0);
		}
		return "manage/videoinfo";
	}
	
	
	@RequestMapping("/manage/{sid}/delvbys")
	public String delvbys( @PathVariable int sid,Model model) {
		Submission sub = adminService.findsubbyid(sid);
		String url=sub.getFileurl();
		List<Video> v=adminService.findvbyurl(url);
		adminService.deleteVideo(v.get(0).getVideoid().toString());
		return "redirect:/manage/videocomment/1.htm";
	}
}
