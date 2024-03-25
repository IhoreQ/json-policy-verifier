package org.ibobek.model;

import java.util.List;

public class Statement {
    private String effect;

    private List<String> action;

    private List<String> resources;

    public List<String> getResources() {
        return resources;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setAction(List<String> action) {
        this.action = action;
    }

    public void setResource(List<String> resources) {
        this.resources = resources;
    }
}
