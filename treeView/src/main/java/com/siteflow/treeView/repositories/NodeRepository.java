package com.siteflow.treeView.repositories;

import com.siteflow.treeView.entities.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<Node,Integer> {
}
