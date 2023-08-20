package org.jenkinsci.plugins.sosdefence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Ronny "Sephiroth" Perinke <sephiroth@sephiroth-j.de>
 */
class ResultLinkActionTest {

    @ParameterizedTest
    @CsvSource({
        ",",
        "'',''",
        ",'an-id'",
        "'http://foo.bar',",})
    void testDisableSituations(String url, String projectId) {
        ResultLinkAction uut = new ResultLinkAction(url, projectId);
        assertThat(uut.getUrlName()).isNull();
        assertThat(uut.getDisplayName()).isNull();
        assertThat(uut.getIconFileName()).isNull();
    }

    @Test
    void testWithUrlAndProjectId() {
        ResultLinkAction uut = new ResultLinkAction("http://foo.bar", "an-id\"");
        assertThat(uut.getUrlName()).isEqualTo("http://foo.bar/project/?uuid=an-id%22");
        assertThat(uut.getDisplayName()).isNotEmpty();
        assertThat(uut.getIconFileName()).isNotEmpty();
    }

}
