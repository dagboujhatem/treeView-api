package com.siteflow.treeView.repositories;

import com.siteflow.treeView.entities.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<Node,Integer> {
    List<Node> findAllByParentNodeIsNull();
}
