package com.siteflow.treeView.services;

import com.siteflow.treeView.entities.Node;
import com.siteflow.treeView.playload.requests.CreateNodeRequest;
import com.siteflow.treeView.playload.requests.UpdateNodeRequest;

import java.util.List;

public interface NodeService {
    List<Node> findAll();
    Node findById(int id);
    Node save(CreateNodeRequest createNodeRequest);
    Node update(int id, UpdateNodeRequest updateNodeRequest);
    void delete(int id);
}
