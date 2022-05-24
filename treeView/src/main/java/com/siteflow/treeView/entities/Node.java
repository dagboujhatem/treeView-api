package com.siteflow.treeView.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
}
