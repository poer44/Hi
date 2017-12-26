package service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.SubmissionMapper;
import entity.Submission;
import entity.SubmissionExample;
import service.INoPassService;

@Service
public class NoPassService implements INoPassService{
private SubmissionMapper submissionMapper;
	
	public SubmissionMapper getSubmissionMapper() {
		return submissionMapper;
	}
	@Resource
	public void setSubmissionMapper(SubmissionMapper submissionMapper) {
		this.submissionMapper = submissionMapper;
	}
	@Override
	public List<Submission> findAllNoPass(String userid,String state) {
	SubmissionExample se=new SubmissionExample();
	SubmissionExample.Criteria sec=se.createCriteria().andStateEqualTo(userid).andStateEqualTo(state);
		return submissionMapper.selectByExample(se);
	}
	@Override
	public boolean deleteNoPass(int submissionid) {
		return submissionMapper.deleteByPrimaryKey(submissionid)>0;
	}

}
