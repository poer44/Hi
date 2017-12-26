package service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;




import dao.UserMapper;
import dao.UsercollectionMapper;
import dao.VideoMapper;
import entity.Usercollection;
import entity.UsercollectionExample;
import entity.Video;
import entity.VideoExample;
import entity.VideocommentExample;
import service.IUserCollectionService;

@Service
public class UserCollectionService implements IUserCollectionService {

	private UserMapper userMapper;
	private VideoMapper videoMapper;
	private UsercollectionMapper usercollectionMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public VideoMapper getVideoMapper() {
		return videoMapper;
	}

	@Resource
	public void setVideoMapper(VideoMapper videoMapper) {
		this.videoMapper = videoMapper;
	}

	public UsercollectionMapper getCollectionMapper() {
		return usercollectionMapper;
	}

	@Resource
	public void setCollectionMapper(UsercollectionMapper collectionMapper) {
		this.usercollectionMapper = collectionMapper;
	}

	@Override
	public List<Integer> videoidlist(String userid) {//将用户收藏的videoid封装成一个list
		UsercollectionExample se1 = new UsercollectionExample();
		UsercollectionExample.Criteria sec1 = se1.createCriteria();
		sec1.andUseridEqualTo(userid);
		List<Usercollection> list = usercollectionMapper.selectByExample(se1);
		List<Integer> kkk = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			kkk.add(Integer.parseInt(list.get(i).getCollection()));
		}
		return kkk;
	}

	@Override
	public List<Video> findbyvid(String userid) { //根据videoid的list去查video对象集合
		List<Integer> list = videoidlist(userid);
		List<Video> vlist = new ArrayList<Video>();
		for(int i=0;i<list.size();i++){
			VideoExample se = new VideoExample();
			VideoExample.Criteria sec = se.createCriteria();
			sec.andVideoidEqualTo(list.get(i));
			vlist.addAll(videoMapper.selectByExample(se));
		}
		return vlist;
	}

	@Override
	public Video findvbyi(String vid) {
		// TODO 自动生成的方法存根
		return videoMapper.selectByPrimaryKey(Integer.parseInt(vid));
	}

	@Override
	public List<Usercollection> findAllUsercollection(String userid) {
		return usercollectionMapper.selectById(userid);
	}

	@Override
	public boolean deleteUsercollection(String userid, String collection) {
		return usercollectionMapper.deleteByidcoll(userid, collection) > 0;
	}

	@Override
	public boolean addUsercollection(Usercollection ucollection) {
		return usercollectionMapper.insertSelective(ucollection) > 0;
	}

	@Override
	public boolean delUsercollection(String userid, String videoid) {
		// TODO 自动生成的方法存根
		return usercollectionMapper.deleteByidcoll(videoid, userid)>0;
	}

}
