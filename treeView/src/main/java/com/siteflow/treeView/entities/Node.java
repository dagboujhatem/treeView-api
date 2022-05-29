package com.siteflow.treeView.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "nodes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Node implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Node parentNode;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parentNode")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JsonManagedReference
    @OrderBy("position")
    private List<Node> children;
}
