package com.siteflow.treeView.services;

import com.siteflow.treeView.entities.Node;
import com.siteflow.treeView.exceptions.ResourceNotFoundException;
import com.siteflow.treeView.playload.requests.CreateNodeRequest;
import com.siteflow.treeView.playload.requests.UpdateNodeRequest;
import com.siteflow.treeView.repositories.NodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NodeServiceImpl implements NodeService{

    @Autowired
    NodeRepository nodeRepository;

    @Override
    public List<Node> findAll() {
        return nodeRepository.findAllByParentNodeIsNullOrderByPositionAsc();
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
        Node parentNode = null;
        if(createNodeRequest.getPosition() != -1){
            this.nodeRepository.findAllByParentNodeIsNullAndPositionAfter(createNodeRequest.getPosition()-1)
                    .forEach(nodeFound -> {
                        nodeFound.setPosition(nodeFound.getPosition()+1);
                        this.nodeRepository.save(nodeFound);
                    });
            node.setPosition(createNodeRequest.getPosition());
        }else{
            if(createNodeRequest.getParentNode() != -1){
                parentNode = this.findById(createNodeRequest.getParentNode());
                node.setParentNode(parentNode);
                node.setPosition(this.nodeRepository.countAllByParentNode(parentNode) + 1);
            }
            else{
                node.setPosition(this.nodeRepository.countAllByParentNodeIsNull() + 1);
            }

        }
        Node createNode = this.nodeRepository.save(node);
        if(parentNode != null){
            parentNode.getChildren().add(node);
            this.nodeRepository.save(parentNode);
        }
        return createNode;
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
            Node parentNode = nodeData.get().getParentNode();
            if(parentNode != null)
            {
                this.nodeRepository.findAllByParentNodeAndPositionAfter(parentNode, nodeData.get().getPosition())
                        .forEach(node -> {
                            node.setPosition(node.getPosition()-1);
                            this.nodeRepository.save(node);
                        });
            }else{
                this.nodeRepository.findAllByParentNodeIsNullAndPositionAfter(nodeData.get().getPosition())
                        .forEach(node -> {
                            node.setPosition(node.getPosition()-1);
                            this.nodeRepository.save(node);
                        });
            }
            this.nodeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Node not found.");
        }
    }

    @Override
    public List<Node> saveOrder(int id, int position,int targetIndex,int sourceIndex){
        List<Node> nodeList = this.findAll();
        Node sourceNode = nodeList.get(sourceIndex);
        Node targetNode = nodeList.get(targetIndex);
        if(sourceNode!=null && targetNode!=null)
        {
            if(Math.abs(sourceIndex - targetIndex)> 1){
                List<Node> updateList = null;
                if(sourceIndex < targetIndex){
                    int newTargetPosition = sourceNode.getPosition();
                    targetNode.setPosition(newTargetPosition);
                    this.nodeRepository.save(targetNode);
                    updateList = nodeList.subList(sourceIndex, targetIndex);
                    updateList.forEach(node -> {
                        log.info(String.valueOf(node.getId()));
                        node.setPosition(node.getPosition() + 1);
                        this.nodeRepository.save(node);
                    });
                }else{
                    int newTargetPosition = sourceNode.getPosition();
                    targetNode.setPosition(newTargetPosition);
                    this.nodeRepository.save(targetNode);
                    updateList = nodeList.subList(targetIndex+1, sourceIndex+1);
                    updateList.forEach(node -> {
                        log.info(String.valueOf(node.getId()));
                        node.setPosition(node.getPosition() - 1);
                        this.nodeRepository.save(node);
                    });
                }
            }else {
                int newTargetPosition = sourceNode.getPosition();
                targetNode.setPosition(newTargetPosition);
                this.nodeRepository.save(targetNode);
                sourceNode.setPosition(position);
                this.nodeRepository.save(sourceNode);
            }
        }
        return this.findAll();
    }
}
