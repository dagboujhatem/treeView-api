package com.siteflow.treeView.playload.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNodeRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
