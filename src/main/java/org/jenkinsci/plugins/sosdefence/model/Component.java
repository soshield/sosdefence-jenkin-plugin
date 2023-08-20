package org.jenkinsci.plugins.sosdefence.model;

import java.io.Serializable;
import lombok.Value;

@Value
public class Component implements Serializable {

    private static final long serialVersionUID = -4825926766668357091L;

    private final String uuid;
    private final String name;
    private final String group;
    private final String version;
    private final String purl;

}
