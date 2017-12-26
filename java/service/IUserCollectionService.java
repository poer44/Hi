package service;

import java.util.List;
import entity.User;
import entity.Usercollection;
import entity.Video;

public interface IUserCollectionService {
	List<Integer> videoidlist(String userid);
	List<Video> findbyvid(String userid);
	Video findvbyi(String vid);
	List<Usercollection> findAllUsercollection(String userid);
	boolean deleteUsercollection(String userid,String collection);
	boolean addUsercollection(Usercollection ucollection);
	boolean delUsercollection(String userid,String videoid);
}
