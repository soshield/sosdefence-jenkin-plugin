package org.jenkinsci.plugins.sosdefence.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Project implements Serializable {

    private static final long serialVersionUID = 5615023685011011641L;
	
    private String name;
    private String description;
    private String version;
    private String uuid;
    private Collection<String> tags;
    private LocalDateTime lastBomImport;
    private String lastBomImportFormat;
    private Double lastInheritedRiskScore;
    private Boolean active;
    private String swidTagId;
    private String group;
    private Project parent;
}
