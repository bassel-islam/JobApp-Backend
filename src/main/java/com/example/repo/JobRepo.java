package com.example.repo;

import com.example.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//                    CREATE TABLE job_post (
//                            post_id INTEGER PRIMARY KEY,
//                            post_profile VARCHAR(100),                        //code for db table
//                            post_desc TEXT,
//                            req_experience INTEGER,
//                            post_tech_stack TEXT
//                            );


@Repository
public class JobRepo {
    @Autowired
    private JdbcTemplate jdbc;
    public void addJob(JobPost job){
        String sql = "insert into job_post (post_id, post_profile, post_desc, req_experience, post_tech_stack) VALUES (?, ?, ?, ?, ?)";
        String techs = String.join(",",job.getPostTechStack());
        jdbc.update(sql,
                job.getPostId(),
                job.getPostProfile(),
                job.getPostDesc(),
                job.getReqExperience(),
                techs
        );
    }
    public List<JobPost> getAllJobs(){
        String sql = "select * from job_post";
        RowMapper<JobPost> mapper = (rs, rowNum) -> {
            JobPost job = new JobPost();
            List<String> techs = Arrays.asList(rs.getString("post_tech_stack").split(","));
            job.setPostId(rs.getInt("post_id"));
            job.setPostProfile(rs.getString("post_profile"));
            job.setPostDesc(rs.getString("post_desc"));
            job.setReqExperience(rs.getInt("req_experience"));
            job.setPostTechStack(techs);
            return job;
        };
        return jdbc.query(sql,mapper);
    }
}

