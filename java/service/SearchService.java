package service;
import java.util.List;

import entity.Video;
public interface SearchService {
	List<Video> findBytype(String MorA,Integer typeid,String namelike);
	List<Video> findByName(String name);
	List<Video> findByAuthor(String Author);
}
