package org.jenkinsci.plugins.sosdefence.model;

import hudson.model.Result;
import java.util.stream.IntStream;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Ronny "Sephiroth" Perinke <sephiroth@sephiroth-j.de>
 */
@Execution(ExecutionMode.CONCURRENT)
class RiskGateTest {

    @ParameterizedTest(name = "expect result={8} given total failed thresholds[critical={0},high={1},medium={2},low={3}] and having findings[critical={4},high={5},medium={6},low={7}]")
    @CsvFileSource(resources = "/failedTotalFindings.csv", numLinesToSkip = 1)
    void failedTotalFindingsOnly(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, int numCrit, int numHigh, int numMed, int numLow, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.totalFindings.failedCritical = thCrit;
        th.totalFindings.failedHigh = thHigh;
        th.totalFindings.failedMedium = thMedium;
        th.totalFindings.failedLow = thLow;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0))).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={8} given total unstable thresholds[critical={0},high={1},medium={2},low={3}] and having findings[critical={4},high={5},medium={6},low={7}]")
    @CsvFileSource(resources = "/unstableTotalFindings.csv", numLinesToSkip = 1)
    void unstableTotalFindingsOnly(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, int numCrit, int numHigh, int numMed, int numLow, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.totalFindings.unstableCritical = thCrit;
        th.totalFindings.unstableHigh = thHigh;
        th.totalFindings.unstableMedium = thMedium;
        th.totalFindings.unstableLow = thLow;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0))).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={8} given total thresholds[critical={0},high={1},medium={2},low={3}] for both and having findings[critical={4},high={5},medium={6},low={7}]")
    @CsvFileSource(resources = "/failedTotalFindings.csv", numLinesToSkip = 1)
    void totalFindings(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, int numCrit, int numHigh, int numMed, int numLow, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.totalFindings.failedCritical = th.totalFindings.unstableCritical = thCrit;
        th.totalFindings.failedHigh = th.totalFindings.unstableHigh = thHigh;
        th.totalFindings.failedMedium = th.totalFindings.unstableMedium = thMedium;
        th.totalFindings.failedLow = th.totalFindings.unstableLow = thLow;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0))).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={8} given new failed thresholds[critical={0},high={1},medium={2},low={3}] and having findings[critical={4},high={5},medium={6},low={7}]")
    @CsvFileSource(resources = "/failedTotalFindings.csv", numLinesToSkip = 1)
    void failedNewFindingsOnly(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, int numCrit, int numHigh, int numMed, int numLow, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.newFindings.failedCritical = thCrit;
        th.newFindings.failedHigh = thHigh;
        th.newFindings.failedMedium = thMedium;
        th.newFindings.failedLow = thLow;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0))).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={8} given new unstable thresholds[critical={0},high={1},medium={2},low={3}] and having findings[critical={4},high={5},medium={6},low={7}]")
    @CsvFileSource(resources = "/unstableTotalFindings.csv", numLinesToSkip = 1)
    void unstableNewFindingsOnly(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, int numCrit, int numHigh, int numMed, int numLow, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.newFindings.unstableCritical = thCrit;
        th.newFindings.unstableHigh = thHigh;
        th.newFindings.unstableMedium = thMedium;
        th.newFindings.unstableLow = thLow;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0))).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={8} given new thresholds[critical={0},high={1},medium={2},low={3}] for both and having findings[critical={4},high={5},medium={6},low={7}]")
    @CsvFileSource(resources = "/failedTotalFindings.csv", numLinesToSkip = 1)
    void newFindings(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, int numCrit, int numHigh, int numMed, int numLow, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.newFindings.failedCritical = th.newFindings.unstableCritical = thCrit;
        th.newFindings.failedHigh = th.newFindings.unstableHigh = thHigh;
        th.newFindings.failedMedium = th.newFindings.unstableMedium = thMedium;
        th.newFindings.failedLow = th.newFindings.unstableLow = thLow;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0))).isEqualTo(expectedResult);
    }

}
