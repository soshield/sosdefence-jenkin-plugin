package org.jenkinsci.plugins.sosdefence;

import java.io.File;
import java.nio.charset.StandardCharsets;
import org.assertj.core.util.Files;
import org.jenkinsci.plugins.sosdefence.model.Analysis;
import org.jenkinsci.plugins.sosdefence.model.Component;
import org.jenkinsci.plugins.sosdefence.model.Finding;
import org.jenkinsci.plugins.sosdefence.model.Severity;
import org.jenkinsci.plugins.sosdefence.model.Vulnerability;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Ronny "Sephiroth" Perinke <sephiroth@sephiroth-j.de>
 */
class FindingParserTest {

    @Test
    void parseTest() {
        assertThat(FindingParser.parse("[]")).isEmpty();

        File findings = new File("src/test/resources/findings.json");
        Component c1 = new Component("uuid-1", "name-1", "group-1", "version-1", "purl-1");
        Vulnerability v1 = new Vulnerability("uuid-1", "source-1", "vulnId-1", "title-1", "subtitle-1", "description-1", "recommendation-1", Severity.CRITICAL, 1, 2, "cweName-1");
        Analysis a1 = new Analysis("state-1", false);
        Finding f1 = new Finding(c1, v1, a1, "matrix-1");
        assertThat(FindingParser.parse(Files.contentOf(findings, StandardCharsets.UTF_8))).containsExactly(f1);
    }
}
