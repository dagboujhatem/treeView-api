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
		Node node1 = new Node();
        node1.setName("HTML");
        node1.setDescription("");
        nodeList.add(node1);

        Node node2 = new Node();
        node2.setName("CSS");
        node2.setDescription("");
        nodeList.add(node2);

        Node node3 = new Node();
        node3.setName("CSS Frameworks");
        node3.setDescription("");
        nodeList.add(node3);

        Node node4 = new Node();
        node4.setName("Bootstrap");
        node4.setDescription("");
        node4.setParentNode(node3);
        nodeList.add(node4);

        List<Node> cssFramework = new ArrayList<>();
        cssFramework.add(node4);
        node3.setChildren(cssFramework);

		nodeRepository.saveAll(nodeList);
	}
}
