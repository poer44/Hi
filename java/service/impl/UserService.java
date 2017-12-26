package service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import dao.BarrageMapper;
import dao.SubmissionMapper;
import dao.UserMapper;
import dao.VideoMapper;
import dao.VideocommentMapper;
import entity.Barrage;
import entity.BarrageExample;
import entity.Submission;
import entity.User;
import entity.Video;
import entity.Videocomment;
import entity.VideocommentExample;
import service.IUserService;

@Service
public class UserService implements IUserService{
	
	private UserMapper userMapper;
	private VideoMapper videoMapper;
	private VideocommentMapper videocommentMapper;
	private SubmissionMapper submissionMapper;
	private BarrageMapper barrageMapper;
	
	public BarrageMapper getBarrageMapper() {
		return barrageMapper;
	}
	@Resource
	public void setBarrageMapper(BarrageMapper barrageMapper) {
		this.barrageMapper = barrageMapper;
	}
	public VideocommentMapper getVideocommentMapper() {
		return videocommentMapper;
	}
	@Resource
	public void setVideocommentMapper(VideocommentMapper videocommentMapper) {
		this.videocommentMapper = videocommentMapper;
	}
	public VideoMapper getVideoMapper() {
		return videoMapper;
	}
	@Resource
	public void setVideoMapper(VideoMapper videoMapper) {
		this.videoMapper = videoMapper;
	}
	public SubmissionMapper getSubmissionMapper() {
		return submissionMapper;
	}
	@Resource
	public void setSubmissionMapper(SubmissionMapper submissionMapper) {
		this.submissionMapper = submissionMapper;
	}
	public UserMapper getUserMapper() {
		return userMapper;
	}
	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public User Login1(String tel,String password) {
		User user=userMapper.selectByPrimaryKey(tel);
		if(user.getPassword()!=null&&user.getPassword().equals(password)){
			return user;
		}else{
			return null;
		}
	}
	@Override
	public User getUser(String tel) {
		User user=userMapper.selectByPrimaryKey(tel);
		return user;
	}
	@Override
	public boolean Register(User user) {
		user.setSign("这个人很懒...");
		return userMapper.insert(user)>0;
	}
	@Override
	public User UseridExist(String tel) {
		User user=userMapper.selectByPrimaryKey(tel);
		return user;
	}
	
	@Override
	public boolean addSubmission(Submission submission) {
		return submissionMapper.insertSubmission(submission)>0;
	}
	@Override
	public Video getVideo(int videoid) {
		return videoMapper.selectByPrimaryKey(videoid);
	}
	@Override
	public List<Videocomment> getVideocomment(int videoid) {
		VideocommentExample ve=new VideocommentExample();
		VideocommentExample.Criteria vec=ve.createCriteria();
		vec.andVideoidEqualTo(videoid);
		ve.setOrderByClause("commenttime DESC");
		return videocommentMapper.selectByExample(ve);
	}
	@Override
	public List<Barrage> getBarrage(int videoid) {
		BarrageExample be=new BarrageExample();
		BarrageExample.Criteria bec=be.createCriteria();
		bec.andVideoidEqualTo(videoid);
		return barrageMapper.selectByExample(be);
	}
	@Override
	public boolean addBarrage(Barrage barrage) {
		return barrageMapper.insertBarrage(barrage)>0;
	}
	@Override
	public boolean addComment(Videocomment comment) {
		return videocommentMapper.insertComment(comment)>0;
	}
	@Override
	public User getUsername(String userid) {
		return userMapper.selectByPrimaryKey(userid);
	}
	@Override
	public boolean updateUser(String userid, String password, String username,
			String sex, String birthday, String address, String emotion,
			String email, String sign) {
			return userMapper.updateByPrimaryKeySelective(userid,password,username,sex,birthday,address,emotion,email,sign)>0;
	
	}
	@Override
	public boolean updateCollection(Video video) {
		return videoMapper.updateByPrimaryKey(video)>0;
	}
	@Override
	public boolean updateUserpic(User user) {
		return userMapper.updateByPrimaryKey(user)>0;
	}
}
