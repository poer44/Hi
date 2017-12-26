package service;

import java.util.List;

import entity.Admin;
import entity.Barrage;
import entity.Submission;
import entity.User;
import entity.Video;
import entity.Videocomment;
public interface IAdminService {
	public Admin Login(String adminid,String password);
	List<User> findAllUser();
	boolean deleteUser(String userid);
	List<User> findUserByCondition(String username,String userid);
	User findUserById(String userid);
	boolean updateAdmin(Admin admin);
	public boolean addVideo(Video video);//上传视频
	Integer countuser();
	Integer countcomment();
	Integer countbarrage();
	Integer countvideo();
	Integer countsubmission();
	Integer countnewuser();
	List<Video> findAllVideo();
	List<Video> findvbytype(int type);
	List<Video> findvbyid(int videoid);
	boolean deleteVideo(String videoid);
	List<Videocomment> findcomment(int videoid);
	List<Barrage> findbarrage(int videoid);
	List<Submission> findallsubmission();
	List<Submission> findbytype(String type);
	Submission findsubbyid(int id);
	boolean deleteComment(String comid);
	boolean deleteBarrage(int bagid);
	boolean addsubtovideo(Video video);
	boolean updatesubstate(Submission sub);
	boolean delcollectionbyv(String vid);
	List<User> findnewuser();
	List<Video> delvbyuser(String userid);
	boolean deletesbyv(String videourl);
	List<Video> findvbyurl(String url);
}
