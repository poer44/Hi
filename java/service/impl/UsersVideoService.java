package service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.BarrageMapper;
import dao.SubmissionMapper;
import dao.UsercollectionMapper;
import dao.VideoMapper;
import dao.VideocommentMapper;
import entity.Barrage;
import entity.BarrageExample;
import entity.Submission;
import entity.SubmissionExample;
import entity.UsercollectionExample;
import entity.Video;
import entity.VideoExample;
import entity.Videocomment;
import entity.VideocommentExample;
import service.IUsersVideoService;
@Service
public class UsersVideoService implements IUsersVideoService{
	private SubmissionMapper submissionMapper;
	private VideoMapper videoMapper;
	private BarrageMapper barrageMapper;
	private UsercollectionMapper collectionMapper;
	private VideocommentMapper commentMapper;
	public SubmissionMapper getSubmissionMapper() {
		return submissionMapper;
	}

	@Resource
	public void setSubmissionMapper(SubmissionMapper submissionMapper) {
		this.submissionMapper = submissionMapper;
	}
	
	public VideoMapper getVideoMapper() {
		return videoMapper;
	}
	@Resource
	public void setVideoMapper(VideoMapper videoMapper) {
		this.videoMapper = videoMapper;
	}

	public BarrageMapper getBarrageMapper() {
		return barrageMapper;
	}
	@Resource
	public void setBarrageMapper(BarrageMapper barrageMapper) {
		this.barrageMapper = barrageMapper;
	}

	public UsercollectionMapper getCollectionMapper() {
		return collectionMapper;
	}
	@Resource
	public void setCollectionMapper(UsercollectionMapper collectionMapper) {
		this.collectionMapper = collectionMapper;
	}

	public VideocommentMapper getCommentMapper() {
		return commentMapper;
	}
	@Resource
	public void setCommentMapper(VideocommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	@Override
	public List<Submission> findusbytu(String state, String userid) {
		SubmissionExample se = new SubmissionExample();
		SubmissionExample.Criteria sec = se.createCriteria();
		sec.andStateEqualTo(state).andUseridEqualTo(userid);
		return submissionMapper.selectByExample(se);
	}

	@Override
	public List<Video> findvbyu(String userid) {
		VideoExample se = new VideoExample();
		VideoExample.Criteria sec = se.createCriteria();
		sec.andAuthorEqualTo("用户"+userid);
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
	public boolean delcollectionbyv(String vid) {
		UsercollectionExample se = new UsercollectionExample();
		UsercollectionExample.Criteria sec = se.createCriteria();
		sec.andCollectionEqualTo(vid);
		return collectionMapper.deleteByExample(se)>0;
	}
	@Override
	public boolean deleteVideo(String videoid) {
		Integer id = Integer.parseInt(videoid);
		List<Video> video = this.findvbyid(id);
		//删除文件
		File file1 = new File("src\\main\\webapp\\" + video.get(0).getFileurl());
		File file2 = new File("src\\main\\webapp\\" + video.get(0).getImgurl());
		if (file1.exists() == true && file2.exists() == true) {
			file1.delete();
			file2.delete();
		}
		//删除投稿表
		this.deletesbyv(video.get(0).getFileurl());
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
		//删除投稿表
		
		return videoMapper.deleteByPrimaryKey(id) > 0;
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
	public boolean deleteBarrage(int bagid) {
		// TODO 自动生成的方法存根
		return barrageMapper.deleteByPrimaryKey(bagid) > 0;
	}
	@Override
	public List<Videocomment> findcomment(int videoid) {
		VideocommentExample se = new VideocommentExample();
		VideocommentExample.Criteria sec = se.createCriteria();
		sec.andVideoidEqualTo(videoid);
		return commentMapper.selectByExample(se);
	}
	@Override
	public boolean deleteComment(String comid) {
		Integer id = Integer.parseInt(comid);
		System.out.println(id);
		return commentMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public List<Submission> findsbyv(String videourl) {
		SubmissionExample se = new SubmissionExample();
		SubmissionExample.Criteria sec = se.createCriteria();
		sec.andFileurlEqualTo(videourl);
		return submissionMapper.selectByExample(se);
	}

	@Override
	public boolean deletesbyv(String videourl) {
		SubmissionExample se = new SubmissionExample();
		SubmissionExample.Criteria sec = se.createCriteria();
		sec.andFileurlEqualTo(videourl);
		return submissionMapper.deleteByExample(se)>0;
	}

	@Override
	public boolean deleteSubmission(int sid) {
		// TODO 自动生成的方法存根
		return submissionMapper.deleteByPrimaryKey(sid)>0;
	}
}
