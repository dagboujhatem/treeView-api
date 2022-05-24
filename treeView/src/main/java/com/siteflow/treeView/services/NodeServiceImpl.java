package com.siteflow.treeView.services;

import com.siteflow.treeView.entities.Node;
import com.siteflow.treeView.exceptions.ResourceNotFoundException;
import com.siteflow.treeView.playload.requests.CreateNodeRequest;
import com.siteflow.treeView.playload.requests.UpdateNodeRequest;
import com.siteflow.treeView.repositories.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NodeServiceImpl implements NodeService{

    @Autowired
    NodeRepository nodeRepository;

    @Override
    public List<Node> findAll() {
        return nodeRepository.findAll();
    }

    @Override
    public Node findById(int id) {
        Optional<Node> nodeData = this.nodeRepository.findById(id);
        return nodeData.orElseThrow(() -> new ResourceNotFoundException("Node not found."));
    }

    @Override
    public Node save(CreateNodeRequest createNodeRequest) {
        Node node = new Node();
        node.setName(createNodeRequest.getName());
        node.setDescription(createNodeRequest.getDescription());
        return this.nodeRepository.save(node);
    }

    @Override
    public Node update(int id, UpdateNodeRequest updateNodeRequest) {
        Optional<Node> nodeData = this.nodeRepository.findById(id);
        if (nodeData.isPresent()) {
            Node oldNode = this.nodeRepository.findById(id).get();
            oldNode.setName(updateNodeRequest.getName());
            oldNode.setDescription(updateNodeRequest.getDescription());
            return this.nodeRepository.save(oldNode);
        } else {
            throw new ResourceNotFoundException("Node not found.");
        }
    }

    @Override
    public void delete(int id) {
        Optional<Node> nodeData = this.nodeRepository.findById(id);
        if (nodeData.isPresent()) {
            this.nodeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Node not found.");
        }
    }
}