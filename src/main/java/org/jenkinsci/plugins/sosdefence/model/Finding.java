package org.jenkinsci.plugins.sosdefence.model;

import java.io.Serializable;
import lombok.Value;

@Value
public class Finding implements Serializable {

    private static final long serialVersionUID = 5309487290800777874L;

    private final Component component;
    private final Vulnerability vulnerability;
    private final Analysis analysis;
    private final String matrix;

}
