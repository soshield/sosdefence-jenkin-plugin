package org.jenkinsci.plugins.sosdefence.model;

import java.io.Serializable;
import lombok.Value;

@Value
public class Analysis implements Serializable {

    private static final long serialVersionUID = 9143258740219702420L;

    private final String state;
    private final boolean isSuppressed;

}
