package org.jenkinsci.plugins.sosdefence.model;

import java.util.Set;
import lombok.Builder;
import lombok.Value;

/**
 *
 * @author Ronny "Sephiroth" Perinke <sephiroth@sephiroth-j.de>
 */
@Value
@Builder
public class Team {
    private String name;
    private Set<String> permissions;
}
