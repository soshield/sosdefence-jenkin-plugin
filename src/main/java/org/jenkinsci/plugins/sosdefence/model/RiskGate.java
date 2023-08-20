package org.jenkinsci.plugins.sosdefence.model;

import hudson.model.Result;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class RiskGate implements Serializable {

    private static final long serialVersionUID = 171256230735670985L;

    @NonNull
    private final Thresholds thresholds;

    /**
     * Evaluates if the current results meet or exceed the defined threshold.
     *
     * @param currentDistribution currentDistribution
     * @param previousDistribution previousDistribution
     * @return a Result
     */
    public Result evaluate(@NonNull final SeverityDistribution currentDistribution, @NonNull final SeverityDistribution previousDistribution) {

        Result result = Result.SUCCESS;
        if ((thresholds.totalFindings.failedCritical != null && currentDistribution.getCritical() > 0 && currentDistribution.getCritical() >= thresholds.totalFindings.failedCritical)
                || (thresholds.totalFindings.failedHigh != null && currentDistribution.getHigh() > 0 && currentDistribution.getHigh() >= thresholds.totalFindings.failedHigh)
                || (thresholds.totalFindings.failedMedium != null && currentDistribution.getMedium() > 0 && currentDistribution.getMedium() >= thresholds.totalFindings.failedMedium)
                || (thresholds.totalFindings.failedLow != null && currentDistribution.getLow() > 0 && currentDistribution.getLow() >= thresholds.totalFindings.failedLow)) {

            return Result.FAILURE;
        }
        if ((thresholds.totalFindings.unstableCritical != null && currentDistribution.getCritical() > 0 && currentDistribution.getCritical() >= thresholds.totalFindings.unstableCritical)
                || (thresholds.totalFindings.unstableHigh != null && currentDistribution.getHigh() > 0 && currentDistribution.getHigh() >= thresholds.totalFindings.unstableHigh)
                || (thresholds.totalFindings.unstableMedium != null && currentDistribution.getMedium() > 0 && currentDistribution.getMedium() >= thresholds.totalFindings.unstableMedium)
                || (thresholds.totalFindings.unstableLow != null && currentDistribution.getLow() > 0 && currentDistribution.getLow() >= thresholds.totalFindings.unstableLow)) {

            result = Result.UNSTABLE;
        }

        if ((thresholds.newFindings.failedCritical != null && currentDistribution.getCritical() > 0 && currentDistribution.getCritical() >= previousDistribution.getCritical() + thresholds.newFindings.failedCritical)
                || (thresholds.newFindings.failedHigh != null && currentDistribution.getHigh() > 0 && currentDistribution.getHigh() >= previousDistribution.getHigh() + thresholds.newFindings.failedHigh)
                || (thresholds.newFindings.failedMedium != null && currentDistribution.getMedium() > 0 && currentDistribution.getMedium() >= previousDistribution.getMedium() + thresholds.newFindings.failedMedium)
                || (thresholds.newFindings.failedLow != null && currentDistribution.getLow() > 0 && currentDistribution.getLow() >= previousDistribution.getLow() + thresholds.newFindings.failedLow)) {

            return Result.FAILURE;
        }
        if ((thresholds.newFindings.unstableCritical != null && currentDistribution.getCritical() > 0 && currentDistribution.getCritical() >= previousDistribution.getCritical() + thresholds.newFindings.unstableCritical)
                || (thresholds.newFindings.unstableHigh != null && currentDistribution.getHigh() > 0 && currentDistribution.getHigh() >= previousDistribution.getHigh() + thresholds.newFindings.unstableHigh)
                || (thresholds.newFindings.unstableMedium != null && currentDistribution.getMedium() > 0 && currentDistribution.getMedium() >= previousDistribution.getMedium() + thresholds.newFindings.unstableMedium)
                || (thresholds.newFindings.unstableLow != null && currentDistribution.getLow() > 0 && currentDistribution.getLow() >= previousDistribution.getLow() + thresholds.newFindings.unstableLow)) {

            result = Result.UNSTABLE;
        }

        return result;
    }
}
