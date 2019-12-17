package com.github.rolven95.database.repository;

import com.github.rolven95.database.model.UserGraph;
import org.springframework.data.neo4j.repository.Neo4jRepository;

// 节点Repository
public interface UserGraphRepository extends Neo4jRepository<UserGraph, Long> {
}
