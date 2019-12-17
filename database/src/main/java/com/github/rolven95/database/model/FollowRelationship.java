package com.github.rolven95.database.model;


import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@Builder
@RelationshipEntity(type = "follow")
public class FollowRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private UserGraph fan;

    @EndNode
    private UserGraph idol;

    private String followTime;
}