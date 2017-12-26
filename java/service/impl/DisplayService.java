package service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.UserMapper;
import dao.VideoMapper;
import entity.User;
import entity.Video;
import entity.VideoExample;
import entity.VideoExample.Criteria;
import service.IDisplayService;
import service.IUserService;

@Service
public class DisplayService implements IDisplayService{
	
	private VideoMapper videoMapper;
	
	public VideoMapper getVideoMapper() {
		return videoMapper;
	}
	@Resource
	public void setVideoMapper(VideoMapper videoMapper) {
		this.videoMapper = videoMapper;
	}

	@Override
	public List<Video> PlayDesc() {
		VideoExample example = new VideoExample();
		example.setOrderByClause("'play' ASC,'videoid' ASC");
	    List<Video> list = videoMapper.selectByExample(example);
	    return list;
	}
	@Override
	public List<Video> PlayDescLimit() {
		return videoMapper.selectByPlayDeslimit();
	}
	@Override
	public List<Video> CollDescLimit() {
		return videoMapper.selectByCollDeslimit();
	}
	@Override
	public List<Video> TypeRanLimit(String typeid) {
		return videoMapper.selectByTypeRanlimit(typeid);
	}
	@Override
	public List<Video> TypeTopLimit(String typeid) {
		return videoMapper.selectByTypeToplimit(typeid);
	}
	@Override
	public List<Video> TopPlayByType() {
		return videoMapper.selectByTypeFir();
	}
	@Override
	public String updateVideoPlay(String videoid) {
		Video vi=videoMapper.selectByPrimaryKey(Integer.parseInt(videoid));
		vi.setPlay(vi.getPlay()+1);
		try {
			videoMapper.updateByPrimaryKey(vi);
			String newPlay=String.valueOf(videoMapper.selectByPrimaryKey(Integer.parseInt(videoid)).getPlay());
			return newPlay;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
	}

	

}
