package service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.AdminMapper;
import dao.BarrageMapper;
import dao.SubmissionMapper;
import dao.UserMapper;
import dao.UsercollectionMapper;
import dao.VideoMapper;
import dao.VideocommentMapper;
import entity.Admin;
import entity.Barrage;
import entity.BarrageExample;
import entity.Submission;
import entity.SubmissionExample;
import entity.User;
import entity.UserExample;
import entity.UsercollectionExample;
import entity.Video;
import entity.VideoExample;
import entity.Videocomment;
import entity.VideocommentExample;
import service.IAdminService;

@Service
public class AdminService implements IAdminService {

	private AdminMapper adminMapper;
	private UserMapper userMapper;
	private VideoMapper videoMapper;
	private VideocommentMapper commentMapper;
	private BarrageMapper barrageMapper;
	private SubmissionMapper submissionMapper;
	private UsercollectionMapper collectionMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public AdminMapper getAdminMapper() {
		return adminMapper;
	}

	@Resource
	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}

	public VideoMapper getVideoMapper() {
		return videoMapper;
	}

	@Resource
	public void setVideoMapper(VideoMapper videoMapper) {
		this.videoMapper = videoMapper;
	}

	public VideocommentMapper getCommentMapper() {
		return commentMapper;
	}

	@Resource
	public void setCommentMapper(VideocommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	public BarrageMapper getBarrageMapper() {
		return barrageMapper;
	}

	@Resource
	public void setBarrageMapper(BarrageMapper barrageMapper) {
		this.barrageMapper = barrageMapper;
	}

	public SubmissionMapper getSubmissionMapper() {
		return submissionMapper;
	}

	@Resource
	public void setSubmissionMapper(SubmissionMapper submissionMapper) {
		this.submissionMapper = submissionMapper;
	}

	public UsercollectionMapper getCollectionMapper() {
		return collectionMapper;
	}

	@Resource
	public void setCollectionMapper(UsercollectionMapper collectionMapper) {
		this.collectionMapper = collectionMapper;
	}

	@Override
	public Admin Login(String adminid, String password) {
		Admin admin = adminMapper.selectByPrimaryKey(adminid);
		if (admin != null) {
			if (admin.getPassword().equals(password)) {
				return admin;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	@Override
	public List<User> findAllUser() {
		return userMapper.selectByExample(null);
	}

	@Override
	public boolean deleteUser(String userid) {
		// 删除用户评论
		VideocommentExample se1 = new VideocommentExample();
		VideocommentExample.Criteria sec1 = se1.createCriteria();
		sec1.andUseridEqualTo(userid);
		commentMapper.deleteByExample(se1);
		// 删除用户收藏
		UsercollectionExample se2 = new UsercollectionExample();
		UsercollectionExample.Criteria sec2 = se2.createCriteria();
		sec2.andUseridEqualTo(userid);
		collectionMapper.deleteByExample(se2);
		// 删除用户视频(文件)
		List<Video> video = this.delvbyuser(userid);
		if (video != null) {
			for (int i = 0; i < video.size(); i++) {
				File file1 = new File("src\\main\\webapp\\"
						+ video.get(i).getFileurl());
				File file2 = new File("src\\main\\webapp\\"
						+ video.get(i).getImgurl());
				System.out.println(file1);
				if (file1.exists() == true && file2.exists() == true) {
					file1.delete();
					file2.delete();
				}
			}
		}
		// 删除用户视频(数据库)
		VideoExample se3 = new VideoExample();
		VideoExample.Criteria sec3 = se3.createCriteria();
		sec3.andAuthorEqualTo("用户"+userid);
		videoMapper.deleteByExample(se3);
		// 删除用户弹幕
		BarrageExample se4 = new BarrageExample();
		BarrageExample.Criteria sec4 = se4.createCriteria();
		sec4.andUseridEqualTo(userid);
		barrageMapper.deleteByExample(se4);
		// 删除用户投稿
		SubmissionExample se5 = new SubmissionExample();
		SubmissionExample.Criteria sec5 = se5.createCriteria();
		sec5.andUseridEqualTo(userid);
		submissionMapper.deleteByExample(se5);
		// 删除用户头像
		User user = this.findUserById(userid);
		if (user.getImgurl()!=null&&!user.getImgurl().equals("user_pic\\user.jpg")) {
			File file3 = new File("src\\main\\webapp\\" + user.getImgurl());
			if (file3.exists() == true) {
				file3.delete();
			}
		}
		return userMapper.deleteByPrimaryKey(userid) > 0;
	}

	@Override
	public List<User> findUserByCondition(String userid, String username) {
		UserExample se = new UserExample();
		UserExample.Criteria sec = se.createCriteria();
		sec.andUseridLike("%" + userid + "%");
		if (userid != null && !"".equals(username)) {
			sec.andUsernameLike("%" + username + "%");
		}
		return userMapper.selectByExample(se);
	}

	@Override
	public User findUserById(String userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		return user;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		return adminMapper.updateByPrimaryKeySelective(admin) > 0;
	}

	@Override
	public boolean addVideo(Video video) {
		return videoMapper.insertVideo(video) > 0;
	}

	@Override
	public Integer countuser() {
		return userMapper.countByExample(null);
	}

	@Override
	public Integer countcomment() {
		// TODO 自动生成的方法存根
		return commentMapper.countByExample(null);
	}

	@Override
	public Integer countbarrage() {
		// TODO 自动生成的方法存根
		return barrageMapper.countByExample(null);
	}

	@Override
	public Integer countvideo() {
		// TODO 自动生成的方法存根
		return videoMapper.countByExample(null);
	}

	@Override
	public List<Video> findAllVideo() {
		// TODO 自动生成的方法存根
		VideoExample se = new VideoExample();
		se.setOrderByClause("uptime desc");
		return videoMapper.selectByExample(se);
	}

	@Override
	public boolean deleteVideo(String videoid) {//删除视频
		Integer id = Integer.parseInt(videoid);
		List<Video> video = this.findvbyid(id);
		//删除文件
		File file1 = new File("src\\main\\webapp\\" + video.get(0).getFileurl());
		File file2 = new File("src\\main\\webapp\\" + video.get(0).getImgurl());
		if (file1.exists() == true && file2.exists() == true) {
			file1.delete();
			file2.delete();
		}
		//删除收藏
		this.delcollectionbyv(videoid);
		//删除弹幕
		List<Barrage> l1=this.findbarrage(id);
		for(int i=0;i<l1.size();i++){
			this.deleteBarrage(l1.get(i).getBarrageid());
		}
		//删除评论
		List<Videocomment> l2=this.findcomment(id);
		for(int i=0;i<l2.size();i++){
			this.deleteComment(l2.get(i).getCommentid().toString());
		}
		//如果用户上传，删除投稿表
		this.deletesbyv(video.get(0).getFileurl());
		return videoMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public List<Videocomment> findcomment(int videoid) {
		VideocommentExample se = new VideocommentExample();
		VideocommentExample.Criteria sec = se.createCriteria();
		sec.andVideoidEqualTo(videoid);
		return commentMapper.selectByExample(se);
	}

	@Override
	public List<Barrage> findbarrage(int videoid) {
		// TODO 自动生成的方法存根
		BarrageExample se = new BarrageExample();
		BarrageExample.Criteria sec = se.createCriteria();
		sec.andVideoidEqualTo(videoid);
		return barrageMapper.selectByExample(se);
	}

	@Override
	public List<Submission> findallsubmission() {
		// TODO 自动生成的方法存根
		SubmissionExample se = new SubmissionExample();
		se.setOrderByClause("submissiontime desc");
		return submissionMapper.selectByExample(se);
	}

	@Override
	public boolean deleteComment(String comid) {
		Integer id = Integer.parseInt(comid);
		System.out.println(id);
		return commentMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public List<Video> findvbytype(int type) {
		VideoExample se = new VideoExample();
		VideoExample.Criteria sec = se.createCriteria();
		sec.andTypeidEqualTo(type);
		return videoMapper.selectByExample(se);
	}

	@Override
	public List<Video> findvbyid(int videoid) {
		System.out.println("videoid=" + videoid);
		VideoExample se = new VideoExample();
		VideoExample.Criteria sec = se.createCriteria();
		sec.andVideoidEqualTo(videoid);
		return videoMapper.selectByExample(se);
	}

	@Override
	public List<Submission> findbytype(String type) {
		SubmissionExample se = new SubmissionExample();
		SubmissionExample.Criteria sec = se.createCriteria();
		sec.andStateEqualTo(type);
		return submissionMapper.selectByExample(se);
	}

	@Override
	public Submission findsubbyid(int id) {
		return submissionMapper.selectByPrimaryKey(id);
	}

	public boolean addsubtovideo(Video video) {
		// TODO 自动生成的方法存根
		return videoMapper.insertVideo(video) > 0;
	}

	@Override
	public boolean updatesubstate(Submission sub) {
		return submissionMapper.updateByPrimaryKeySelective(sub) > 0;
	}

	@Override
	public Integer countsubmission() {
		SubmissionExample se = new SubmissionExample();
		SubmissionExample.Criteria sec = se.createCriteria();
		sec.andStateEqualTo("待审核");
		return submissionMapper.countByExample(se);
	}

	@Override
	public boolean deleteBarrage(int bagid) {
		// TODO 自动生成的方法存根
		return barrageMapper.deleteByPrimaryKey(bagid) > 0;
	}

	@Override
	public Integer countnewuser() {
		return userMapper.countnewuser();
	}

	@Override
	public List<User> findnewuser() {
		return userMapper.selectnew();
	}

	@Override
	public List<Video> delvbyuser(String userid) {
		VideoExample se = new VideoExample();
		VideoExample.Criteria sec = se.createCriteria();
		sec.andAuthorEqualTo(userid);
		return videoMapper.selectByExample(se);
	}

	@Override
	public boolean delcollectionbyv(String vid) {
		UsercollectionExample se = new UsercollectionExample();
		UsercollectionExample.Criteria sec = se.createCriteria();
		sec.andCollectionEqualTo(vid);
		return collectionMapper.deleteByExample(se)>0;
	}

	@Override
	public boolean deletesbyv(String videourl) {
		SubmissionExample se = new SubmissionExample();
		SubmissionExample.Criteria sec = se.createCriteria();
		sec.andFileurlEqualTo(videourl);
		return submissionMapper.deleteByExample(se)>0;
	}

	@Override
	public List<Video> findvbyurl(String url) {
		// TODO 自动生成的方法存根
		VideoExample se = new VideoExample();
		VideoExample.Criteria sec = se.createCriteria();
		sec.andFileurlEqualTo(url);
		return videoMapper.selectByExample(se);
	}
}
