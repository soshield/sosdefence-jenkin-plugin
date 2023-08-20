package org.jenkinsci.plugins.sosdefence;

import hudson.util.FormValidation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Ronny "Sephiroth" Perinke <sephiroth@sephiroth-j.de>
 */
class PluginUtilTest {

    @Test
    void doCheckUrlTest() {
        assertThat(PluginUtil.doCheckUrl("")).isEqualTo(FormValidation.ok());
        assertThat(PluginUtil.doCheckUrl(null)).isEqualTo(FormValidation.ok());
        assertThat(PluginUtil.doCheckUrl("https://foo.bar/asd")).isEqualTo(FormValidation.ok());
        assertThat(PluginUtil.doCheckUrl("http://foo.bar/asd")).isEqualTo(FormValidation.ok());
        assertThat(PluginUtil.doCheckUrl("http://foo.bar")).isEqualTo(FormValidation.ok());

        assertThat(PluginUtil.doCheckUrl("foo")).hasMessage(Messages.Publisher_ConnectionTest_UrlMalformed()).hasFieldOrPropertyWithValue("kind", FormValidation.Kind.ERROR);
        assertThat(PluginUtil.doCheckUrl("ftp://foo.bar")).hasMessage(Messages.Publisher_ConnectionTest_InvalidProtocols()).hasFieldOrPropertyWithValue("kind", FormValidation.Kind.ERROR);
    }

    @Test
    void parseBaseUrlTest() {
        assertThat(PluginUtil.parseBaseUrl("  ")).isNull();
        assertThat(PluginUtil.parseBaseUrl("http://foo.bar")).isEqualTo("http://foo.bar");
        assertThat(PluginUtil.parseBaseUrl("http://foo.bar/")).isEqualTo("http://foo.bar");
        assertThat(PluginUtil.parseBaseUrl("http://foo.bar/asd/")).isEqualTo("http://foo.bar/asd");
        assertThat(PluginUtil.parseBaseUrl("http://foo.bar/asd/foo")).isEqualTo("http://foo.bar/asd/foo");
    }
}
