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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import service.IUserService;
import service.impl.UserCollectionService;
import utils.GetImgUrl;
import utils.MD5Util;
import entity.Barrage;
import entity.Submission;
import entity.User;
import entity.Usercollection;
import entity.Video;
import entity.Videocomment;

@Controller
public class UseropController {
	
	private IUserService userService;
	private UserCollectionService collectionService;

	public UserCollectionService getCollectionService() {
		return collectionService;
	}
	@Resource
	public void setCollectionService(UserCollectionService collectionService) {
		this.collectionService = collectionService;
	}
	public IUserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/customer/useruploadvideo", method = RequestMethod.POST)
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
					String userid =  items.get(0).getString("utf-8");
					String videoname = items.get(1).getString("utf-8");
					int typeid = Integer.parseInt(items.get(2).getString("utf-8"));
					String filesize = items.get(4).getString("utf-8");
					String format = items.get(5).getString("utf-8");
					String videolength = items.get(6).getString("utf-8");					
					String fileUrl = "";
					String imgUrl = "";
					String fileName = "";
					if (!userid.equals("null")) {
						for (FileItem item : items) {
							if (!item.isFormField()) {
								fileName = item.getName();
								InputStream in = item.getInputStream();
								byte[] buffer = new byte[1024];
								int len = 0;
								fileUrl = request.getSession().getServletContext()
										.getRealPath("/")
										+ "user_uploadfiles\\" + fileName;// 文件最终上传的位置
								OutputStream out = new FileOutputStream(fileUrl);						
								while ((len = in.read(buffer)) != -1) {
									out.write(buffer, 0, len);
								}
								out.close();
								in.close();
							}
						}
						SimpleDateFormat df = new SimpleDateFormat(
								"yyyyMMddHHmmss");// 设置日期格式
						String uptime = df.format(new Date()).toString();
						String hou=fileName.substring(fileName.lastIndexOf("."),fileName.length());

						File file=new File(fileUrl); //指定文件名及路径
						  String filename=file.getAbsolutePath();
						  if(filename.indexOf(".")>=0){
						   filename = filename.substring(0,filename.lastIndexOf("\\"));//文件上传路径
						  }
						  file.renameTo(new File(filename+"\\"+userid+uptime+hou)); //改名
						  GetImgUrl.processImg(new File(filename+"\\"+userid+uptime+hou).getAbsolutePath(),
									"src/main/java/utils/ffmpeg.exe");
						Submission submission = new Submission(userid,videoname,
								"user_uploadfiles\\"+ userid+uptime+ ".jpg", 
								"user_uploadfiles\\" + userid+uptime+hou,
								filesize, format,
								uptime, "待审核", videolength,typeid);
						if (userService.addSubmission(submission)) {
							model.addAttribute("msg", "上传成功，请耐心等待管理员审核~");
						} else {
							model.addAttribute("msg", "上传失败");
						}
					} else {
						model.addAttribute("msg", "上传失败,尚未登录");
					}
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
				return "customer/user_uploadnote";
	}
	@RequestMapping(value="/customer/getvideo",method=RequestMethod.GET) /*获取video*/
	public @ResponseBody Video getvideo(int videoid) {   
		Video video=userService.getVideo(videoid);
		if(video!=null){
			video.setMsg("success");
			return video;
		}else{
			Video video1=new Video();
			video1.setMsg("fail");
			return video1;
		}
	}
	@RequestMapping(value="/customer/getvideocomment",method=RequestMethod.GET) /*获取评论*/
	public @ResponseBody List<Videocomment> getvideocomment(int videoid,Integer pagenum) {
		List<Videocomment> list;
		if(pagenum==null){
			list=userService.getVideocomment(videoid);
			}
		else{
			Integer p=pagenum/5;
			if(pagenum%5>0)
				p=p+1;
			PageHelper.startPage(p,5);
			list=userService.getVideocomment(videoid);
			PageInfo<Videocomment> page=new PageInfo<Videocomment>(list);
		}
	/*	if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				User user=userService.getUsername(list.get(i).getUserid());
				list.get(i).setUserid(user.getUsername());
			}
		}*/
		return list;
	}
	@RequestMapping(value="/customer/getbarrage",method=RequestMethod.GET) /*获取弹幕*/
	public @ResponseBody List<Barrage> getbarrage(int videoid) {
		return userService.getBarrage(videoid);
	}
	@RequestMapping(value="/customer/reportbarrage",method=RequestMethod.GET) //发弹幕
	public @ResponseBody Barrage reportBarrage(int videoid,String userid,String content,int videotime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String uptime = df.format(new Date()).toString();
		Barrage barrage=new Barrage(videoid,userid,content,videotime+"",uptime);
		if(userid!=""&&userid!=null){
			userService.addBarrage(barrage);
			barrage.setMsg("success");
			return barrage;
		}else{
			barrage.setMsg("fail");
			return barrage;
		}
	}
	@RequestMapping(value="/customer/commentvideo",method=RequestMethod.GET) //评论视频
	public @ResponseBody Videocomment commentvideo(int videoid,String userid,String content) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String uptime = df.format(new Date()).toString();
		Videocomment comment=new Videocomment(videoid,userid,content,uptime);
		userService.addComment(comment);
		return comment;
	}
	@RequestMapping(value="/customer/getcollection",method=RequestMethod.GET) /*获取收藏*/
	public @ResponseBody List<Usercollection> getcollection(String userid) {
		return collectionService.findAllUsercollection(userid);
	}
	@RequestMapping(value="/customer/cancelcollection",method=RequestMethod.GET) /*取消收藏*/
	public @ResponseBody String cancelcollection(String userid,String videoid) {
		Video video=userService.getVideo(Integer.parseInt(videoid));	
		video.setCollection(video.getCollection()-1);
		userService.updateCollection(video);
		if(collectionService.deleteUsercollection(userid,videoid))
			return "success";
		else
			return "fail";
	}
	@RequestMapping(value="/customer/videocollection",method=RequestMethod.GET) /*收藏*/
	public @ResponseBody String videocollection(String userid,String videoid) {
		Video video=userService.getVideo(Integer.parseInt(videoid));	
		video.setCollection(video.getCollection()+1);
		userService.updateCollection(video);
		if(collectionService.addUsercollection(new Usercollection(userid,videoid)))
			return "success";
		else
			return "fail";
	}
	@RequestMapping(value="/customer/modifyuserpic",method=RequestMethod.POST) /*修改头像*/
	public @ResponseBody User videocollection(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userid=request.getParameter("userid");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		factory.setSizeThreshold(1024 * 1024 * 500);
		File linshi = new File("E:\\linshi");
		factory.setRepository(linshi);
		upload.setSizeMax(1024 * 1024 * 500);
		User user=new User();
		try {
			List<FileItem> items = upload.parseRequest(request);				
			String fileUrl = "";
			String imgUrl = "";
			String fileName = "";
			if (!userid.equals("null")) {
				for (FileItem item : items) {
					if (!item.isFormField()) {
						fileName = item.getName();
						InputStream in = item.getInputStream();
						byte[] buffer = new byte[1024];
						int len = 0;
						fileUrl = request.getSession().getServletContext()
								.getRealPath("/")
								+ "user_pic\\" + fileName;// 文件最终上传的位置
						OutputStream out = new FileOutputStream(fileUrl);						
						while ((len = in.read(buffer)) != -1) {
							out.write(buffer, 0, len);
						}
						out.close();
						in.close();
					}
				}
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyyMMddHHmmss");// 设置日期格式
				String uptime = df.format(new Date()).toString();
				String hou=fileName.substring(fileName.lastIndexOf("."),fileName.length());

				File file=new File(fileUrl); //指定文件名及路径
				  String filename=file.getAbsolutePath();
				  if(filename.indexOf(".")>=0){
				   filename = filename.substring(0,filename.lastIndexOf("\\"));//文件上传路径
				  }
				  file.renameTo(new File(filename+"\\"+userid+uptime+hou)); //改名
				  user=userService.getUser(userid);
					  if (user.getImgurl()!=null&&!user.getImgurl().equals("user_pic\\user.jpg")) {
							File file3 = new File("src\\main\\webapp\\" + user.getImgurl());
							if (file3.exists() == true) {
								file3.delete();
							}
						}
				  user.setImgurl("user_pic\\"+userid+uptime+hou);
				  userService.updateUserpic(user);
				  user.setMsg("success");
				  request.getSession().setAttribute("user", user);
			} else {
				user.setMsg("fail");
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return user;
	}
	@RequestMapping(value="/customer/modifyuserinfo",method=RequestMethod.POST) //修改用户信息
	public @ResponseBody User modifyuserinfo(String userid,String username,String sex,String birthday,String address,String emotion,String email,String sign,HttpServletRequest request) {
		System.out.println(userid+username+sex+birthday+address+emotion+email+sign);
		User user=userService.getUser(userid);
		user.setUsername(username);
		user.setSex(sex);
		user.setBirthday(birthday);
		user.setAddress(address);
		user.setEmotion(emotion);
		user.setEmail(email);
		user.setSign(sign);
		if(userid!=null){
			userService.updateUserpic(user);
			User newuser=userService.getUser(userid);
			newuser.setMsg("success");
			request.getSession().setAttribute("user", newuser);
			System.out.println(newuser);
			return newuser;
		}else {
			user.setMsg("fail");
			System.out.println(user);
			return user;
		}
	}
	@RequestMapping(value="/customer/modifyuserpwd",method=RequestMethod.POST) //修改用户信息
	public @ResponseBody User modifyuserpwd(String userid,String oldpwd,String newpwd,HttpServletRequest request) {
		User user=userService.getUser(userid);
		if(userid!=null){
			if(MD5Util.MD5Encode(oldpwd, null).equals(user.getPassword())){
				user.setPassword(MD5Util.MD5Encode(newpwd, null));
				userService.updateUserpic(user);
				user.setMsg("success");
				request.getSession().setAttribute("user", user);
			}else{
				user.setMsg("errorpwd");
			}	
		}else {
			user.setMsg("nologin");
		}
		return user;
	}
}
