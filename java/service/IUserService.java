package service;

import java.util.List;

import entity.Barrage;
import entity.Submission;
import entity.User;
import entity.Video;
import entity.Videocomment;

public interface IUserService {

	public User Login1(String tel,String password); //用户登录
	public boolean Register(User user);//用户注册
	public User UseridExist(String tel);//用户手机号是否存在
	public User getUser(String tel);//获取User对象
	boolean updateUser(String userid,String password,String username,String sex,String birthday,String address,String emotion,String email,String sign);//更新用户信息
	public boolean addSubmission(Submission video);//用户上传视频
	public Video getVideo(int videoid);//获取视频信息
	public List<Videocomment> getVideocomment(int videoid);//获取评论
	public List<Barrage> getBarrage(int videoid);//获取弹幕
	public boolean addBarrage(Barrage barrage);//发表弹幕
	public boolean addComment(Videocomment comment);//评论
	public User getUsername(String userid);//通过手机号获取昵称
	public boolean updateCollection(Video video);//更新收藏数量
	public boolean updateUserpic(User user);//用户修改信息或头像
	
}
