package dao;

import entity.Submission;
import entity.SubmissionExample;
import entity.Video;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubmissionMapper {
    int countByExample(SubmissionExample example);

    int deleteByExample(SubmissionExample example);

    int deleteByPrimaryKey(Integer submissionid);

    int insert(Submission record);

    int insertSelective(Submission record);

    List<Submission> selectByExample(SubmissionExample example);

    Submission selectByPrimaryKey(Integer submissionid);

    int updateByExampleSelective(@Param("record") Submission record, @Param("example") SubmissionExample example);

    int updateByExample(@Param("record") Submission record, @Param("example") SubmissionExample example);

    int updateByPrimaryKeySelective(Submission record);

    int updateByPrimaryKey(Submission record);
    
    int insertSubmission(Submission record);//免seq_id的insert Submission
}