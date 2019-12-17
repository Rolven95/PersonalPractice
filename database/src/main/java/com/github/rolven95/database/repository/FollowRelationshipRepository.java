package com.github.rolven95.database.repository;

import com.github.rolven95.database.model.FollowRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;

// 节点Repository
public interface FollowRelationshipRepository extends Neo4jRepository<FollowRelationship, Long> {
}
