package com.siteflow.treeView.controllers;

import com.siteflow.treeView.entities.Node;
import com.siteflow.treeView.playload.requests.CreateNodeRequest;
import com.siteflow.treeView.playload.requests.UpdateNodeRequest;
import com.siteflow.treeView.playload.responses.ApiResponse;
import com.siteflow.treeView.services.NodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class NodeController {

    @Autowired
    NodeServiceImpl nodeService;

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    public ApiResponse<List<Node>> getAllNodes(){
        return new ApiResponse<>(200, "Find all nodes.", nodeService.findAll());
    }

    @RequestMapping(value ="/nodes/{id}", method = RequestMethod.GET)
    public ApiResponse<Node> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Get one node.", nodeService.findById(id));
    }

    @RequestMapping(value ="/nodes", method = RequestMethod.POST)
    public ApiResponse<Node> saveNode(@RequestBody @Valid CreateNodeRequest createNodeRequest) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Save new node.", nodeService.save(createNodeRequest));
    }

    @RequestMapping(value ="/nodes/{id}", method = RequestMethod.PUT)
    public ApiResponse<Node> update(@PathVariable int id, @RequestBody @Valid UpdateNodeRequest updateNodeRequest) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Update one node.", nodeService.update(id, updateNodeRequest));
    }

    @RequestMapping(value ="/nodes/{id}", method = RequestMethod.DELETE)
    public ApiResponse<Void> delete(@PathVariable int id) {
        nodeService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Delete one node.", null);
    }

    @RequestMapping(value ="/nodes/{id}/save-order/{position}/{targetIndex}/{sourceIndex}", method = RequestMethod.PUT)
    public ApiResponse<List<Node>> saveOrder(@PathVariable int id, @PathVariable int position, @PathVariable int targetIndex, @PathVariable int sourceIndex) {
        return new ApiResponse<>(HttpStatus.OK.value(), "order saved successfully.", nodeService.saveOrder(id, position, targetIndex, sourceIndex));
    }
}
