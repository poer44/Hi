package service;

import java.util.List;

import entity.Barrage;
import entity.Submission;
import entity.Video;
import entity.Videocomment;

public interface IUsersVideoService {
	List<Submission> findusbytu(String userid,String state);
	List<Video> findvbyu(String userid);
	List<Video> findvbyid(int videoid);
	boolean delcollectionbyv(String vid);
	boolean deleteVideo(String videoid);
	List<Barrage> findbarrage(int videoid);
	boolean deleteBarrage(int bagid);
	List<Videocomment> findcomment(int videoid);
	List<Submission> findsbyv(String videourl);
	boolean deletesbyv(String videourl);
	boolean deleteComment(String comid);
	boolean deleteSubmission(int sid);
}
