package dao;

import entity.Barrage;
import entity.StanVideoComm;
import entity.Videocomment;
import entity.VideocommentExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface VideocommentMapper {
    int countByExample(VideocommentExample example);

    int deleteByExample(VideocommentExample example);

    int deleteByPrimaryKey(Integer commentid);

    int insert(Videocomment record);

    int insertSelective(Videocomment record);

    List<Videocomment> selectByExample(VideocommentExample example);

    List<Videocomment> selectById(@Param("userid") String userid);

    Videocomment selectByPrimaryKey(Integer commentid);

    int updateByExampleSelective(@Param("record") Videocomment record, @Param("example") VideocommentExample example);

    int updateByExample(@Param("record") Videocomment record, @Param("example") VideocommentExample example);

    int updateByPrimaryKeySelective(Videocomment record);

    int updateByPrimaryKey(Videocomment record);
    
    int insertComment(Videocomment record);//免seq_id的insert Comment
    
    List<StanVideoComm> selectUnite(String userid);
    
}