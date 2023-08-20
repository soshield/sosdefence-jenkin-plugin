package org.jenkinsci.plugins.sosdefence.model;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Thresholds implements Serializable {

    public final TotalFindings totalFindings = new TotalFindings();
    public final NewFindings newFindings = new NewFindings();

    @EqualsAndHashCode
    @ToString
    public static class TotalFindings implements Serializable {
        public Integer unstableCritical;
        public Integer unstableHigh;
        public Integer unstableMedium;
        public Integer unstableLow;
        public Integer failedCritical;
        public Integer failedHigh;
        public Integer failedMedium;
        public Integer failedLow;
    }

    @EqualsAndHashCode
    @ToString
    public static class NewFindings implements Serializable {
        public Integer unstableCritical;
        public Integer unstableHigh;
        public Integer unstableMedium;
        public Integer unstableLow;
        public Integer failedCritical;
        public Integer failedHigh;
        public Integer failedMedium;
        public Integer failedLow;
    }
}
