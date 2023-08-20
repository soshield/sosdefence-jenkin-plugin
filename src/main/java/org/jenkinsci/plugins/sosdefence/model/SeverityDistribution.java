package org.jenkinsci.plugins.sosdefence.model;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class SeverityDistribution implements Serializable {

    private static final long serialVersionUID = 8144779189066349036L;

    private final int buildNumber;
    private int critical;
    private int high;
    private int medium;
    private int low;
    private int info;
    private int unassigned;

    public void add(Severity severity) {
        if (Severity.CRITICAL == severity) {
            critical++;
        } else if (Severity.HIGH == severity) {
            high++;
        } else if (Severity.MEDIUM == severity) {
            medium++;
        } else if (Severity.LOW == severity) {
            low++;
        } else if (Severity.INFO == severity) {
            info++;
        } else if (Severity.UNASSIGNED == severity) {
            unassigned++;
        }
    }
}
