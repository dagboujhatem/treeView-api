package com.siteflow.treeView;

import com.siteflow.treeView.entities.Node;
import com.siteflow.treeView.repositories.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class TreeViewApplication implements CommandLineRunner {

	@Autowired
	NodeRepository nodeRepository;

	public static void main(String[] args) {
		SpringApplication.run(TreeViewApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		nodeRepository.deleteAll();
		List<Node> nodeList = new ArrayList<Node>();
//		Node
//		nodeList.add(new Node())
		nodeRepository.saveAll(nodeList);
	}
}
