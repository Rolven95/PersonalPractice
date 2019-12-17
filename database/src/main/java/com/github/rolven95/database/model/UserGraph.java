package com.github.rolven95.database.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@Builder
public class UserGraph {

    @Id
    @GeneratedValue
    private Long id;

    private Long uuid;
    private String mobile;
    private String countryCode;
    private String token;
    private Integer gender;
    private String nickname;

    private Long createTime;
    private Long updateTime;
}
