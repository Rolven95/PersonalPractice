package com.github.rolven95.database.service;

import com.github.rolven95.database.model.UserGraph;
import com.github.rolven95.database.repository.UserGraphRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class UserGraphService {
    @Autowired
    UserGraphRepository userGraphRepository;

    public void createUser(long uuid, String mobile, String countryCode) {
        UserGraph userGraph = UserGraph.builder()
                .createTime(System.currentTimeMillis())
                .countryCode(countryCode)
                .gender(new Random().nextInt(1) + 1)
                .mobile(mobile)
                .uuid(uuid)
                .build();
        userGraphRepository.save(userGraph);
//        log.debug("User added :{}", userGraph);
    }

//    public UserGraph queryUserByMobile(String mobile, String countryCode){
//        return userGraphRepository.
//    }
//
//    public void createFollowRelation(long uuid, String mobile, String countryCode){
//        UserGraph userGraph = UserGraph.builder()
//                .createTime(System.currentTimeMillis())
//                .countryCode(countryCode)
//                .gender(new Random().nextInt(1)+1)
//                .mobile(mobile)
//                .uuid(uuid)
//                .build()
//                ;
//        userGraphRepository.save(userGraph);
//    }

}
