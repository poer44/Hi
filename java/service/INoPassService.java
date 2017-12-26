package service;

import java.util.List;

import entity.Submission;

public interface INoPassService {
	List<Submission> findAllNoPass(String userid,String state);
	boolean deleteNoPass(int submissionid);
}
