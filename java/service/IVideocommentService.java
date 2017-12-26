package service;

import java.util.List;

import entity.StanVideoComm;
import entity.Usercollection;
import entity.Videocomment;

public interface IVideocommentService {
	List<Videocomment> findAllVideocomment(String userid);
	boolean deleteVideocomment(int commentid);
	List<StanVideoComm> uniteVideoComm(String userid);
}
