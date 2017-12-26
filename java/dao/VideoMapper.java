package dao;

import entity.Video;
import entity.VideoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface VideoMapper {
    int countByExample(VideoExample example);

    int deleteByExample(VideoExample example);

    int deleteByPrimaryKey(Integer videoid);
    
   
    int insert(Video record);

    int insertSelective(Video record);

    List<Video> selectByExample(VideoExample example);

    Video selectByPrimaryKey(Integer videoid);

    int updateByExampleSelective(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByExample(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);
    
    int insertVideo(Video record);//免seq_id的insert video
    
    List<Video> selectByPlayDeslimit();//首页热门播放视频
    
    List<Video> selectByCollDeslimit();//首页热门收藏视频
    
    List<Video> selectByTypeRanlimit(@Param("typeid")String typeid);//首页类型视频随机显示
    
    List<Video> selectByTypeToplimit(@Param("typeid")String typeid);//首页类型视频排行榜
    
    List<Video> selectByTypeFir();
    
}