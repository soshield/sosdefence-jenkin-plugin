package org.jenkinsci.plugins.sosdefence;

import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import hudson.model.Job;
import hudson.model.Run;
import hudson.model.User;
import hudson.security.ACL;
import hudson.security.ACLContext;
import hudson.security.AccessDeniedException3;
import hudson.util.RunList;
import java.io.IOException;
import java.util.List;
import net.sf.json.JSONArray;
import org.assertj.core.api.Assertions;
import org.jenkinsci.plugins.sosdefence.model.Severity;
import org.jenkinsci.plugins.sosdefence.model.SeverityDistribution;
import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.MockAuthorizationStrategy;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Ronny "Sephiroth" Perinke <sephiroth@sephiroth-j.de>
 */
@WithJenkins
class JobActionTest {

    @Test
    void isTrendVisible() {
        Job job = mock(Job.class);
        Run run = mock(Run.class);
        when(run.getAction(ResultAction.class)).thenReturn(new ResultAction(List.of(), new SeverityDistribution(1)));
        when(job.getBuilds())
                .thenReturn(RunList.fromRuns(List.of()))
                .thenReturn(RunList.fromRuns(List.of(run)));
        JobAction uut = new JobAction(job);
        assertThat(uut.isTrendVisible()).isFalse();
        assertThat(uut.isTrendVisible()).isTrue();
    }

    @Test
    void getSeverityDistributionTrendPermissionTest(JenkinsRule j) throws IOException {
        final MockAuthorizationStrategy mockAuthorizationStrategy = new MockAuthorizationStrategy();
        j.jenkins.setAuthorizationStrategy(mockAuthorizationStrategy);
        j.jenkins.setSecurityRealm(j.createDummySecurityRealm());
        
        FreeStyleProject project;
        try (ACLContext ignored = ACL.as(User.getOrCreateByIdOrFullName(ACL.SYSTEM_USERNAME))) {
            mockAuthorizationStrategy.grant(Job.CREATE).onRoot().to(ACL.SYSTEM_USERNAME);
            project = j.createFreeStyleProject();
        }
        final JobAction uut = new JobAction(project);
        final User anonymous = User.getOrCreateByIdOrFullName(ACL.ANONYMOUS_USERNAME);
        // without propper permissions
        try (ACLContext ignored = ACL.as(anonymous)) {
            assertThatThrownBy(() -> uut.getSeverityDistributionTrend()).isInstanceOf(AccessDeniedException3.class);
        }
        // with propper permissions
        try (ACLContext ignored = ACL.as(anonymous)) {
            mockAuthorizationStrategy.grant(Job.READ).onItems(project).to(anonymous);
            assertThatCode(() -> uut.getSeverityDistributionTrend()).doesNotThrowAnyException();
        }
    }

    @Test
    void getSeverityDistribution(JenkinsRule j) throws IOException {
        final FreeStyleProject project = j.createFreeStyleProject();
        final SeverityDistribution sd1 = new SeverityDistribution(1);
        sd1.add(Severity.MEDIUM);
        final SeverityDistribution sd2 = new SeverityDistribution(2);
        sd2.add(Severity.HIGH);
        final ResultAction ra1 = mock(ResultAction.class);
        final ResultAction ra2 = mock(ResultAction.class);
        when(ra1.getSeverityDistribution()).thenReturn(sd1);
        when(ra2.getSeverityDistribution()).thenReturn(sd2);
        final FreeStyleBuild b1 = new FreeStyleBuild(project);
        b1.addAction(ra1);
        final FreeStyleBuild b2 = new FreeStyleBuild(project);
        b2.addAction(ra2);
        project._getRuns().put(1, b1);
        project._getRuns().put(2, b2);

        final JobAction uut = new JobAction(project);
        Assertions.<JSONArray>assertThat(uut.getSeverityDistributionTrend()).isEqualTo(JSONArray.fromObject(List.of(sd1, sd2)));
    }

}
