package com.example.service;

import com.example.model.JobPost;
import com.example.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    public JobRepo repo;
    public void addJob(JobPost jobPost){
        repo.addJob(jobPost);
    }
    public List<JobPost> getAllJobs(){
        return repo.getAllJobs();
    }
}
