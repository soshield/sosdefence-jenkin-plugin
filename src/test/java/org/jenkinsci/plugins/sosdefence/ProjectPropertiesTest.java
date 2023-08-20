package org.jenkinsci.plugins.sosdefence;

import hudson.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jenkins.model.Jenkins;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Ronny "Sephiroth" Perinke <sephiroth@sephiroth-j.de>
 */
class ProjectPropertiesTest {

    @Test
    void testSetTags() {
        ProjectProperties uut = new ProjectProperties();

        assertThat(uut.getTags()).isNotNull();
        uut.setTags(null);
        assertThat(uut.getTags()).isNotNull();

        uut.setTags("tag2 abc\ttag1 tag1");
        assertThat(uut.getTags()).containsExactly("abc", "tag1", "tag2");
        assertThat(uut.getTagsAsText()).isEqualTo("abc%stag1%stag2", System.lineSeparator(), System.lineSeparator());

        uut.setTags(new String[]{"tag2", "tag1"});
        assertThat(uut.getTags()).containsExactly("tag1", "tag2");

        uut.setTags(Stream.of("TAG2", "tag2").collect(Collectors.toList()));
        assertThat(uut.getTags()).containsExactly("tag2");

        uut.setTags(Stream.of("TAG2", "tag2").collect(Collectors.toSet()));
        assertThat(uut.getTags()).containsExactly("tag2");

        assertThatCode(() -> uut.setTags(1)).isInstanceOf(IllegalArgumentException.class);

        Set<Long> setOfWrongType = Set.of(1L);
        assertThatCode(() -> uut.setTags(setOfWrongType)).isInstanceOf(IllegalArgumentException.class);

        List<Long> listOfWrongType = List.of(1L);
        assertThatCode(() -> uut.setTags(listOfWrongType)).isInstanceOf(IllegalArgumentException.class);

        List<Character> listOfWrongType2 = List.of('a');
        assertThatCode(() -> uut.setTags(listOfWrongType2)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void verifyEmptyStringsShallBeNull() {
        ProjectProperties uut = new ProjectProperties();
        uut.setDescription("");
        uut.setGroup("\t");
        uut.setSwidTagId(System.lineSeparator());
        uut.setParentId("       ");
        assertThat(uut.getDescription()).isNull();
        assertThat(uut.getGroup()).isNull();
        assertThat(uut.getSwidTagId()).isNull();
        assertThat(uut.getParentId()).isNull();
    }

    @Nested
    class DescriptorImplTest {

        @Test
        void doFillParentIdItemsTest() throws Exception {
            Field instanceField = ReflectionUtils.findField(Jenkins.class, "theInstance", Jenkins.class);
            ReflectionUtils.makeAccessible(instanceField);
            Jenkins origJenkins = (Jenkins) instanceField.get(null);
            Jenkins mockJenkins = mock(Jenkins.class);
            ReflectionUtils.setField(instanceField, null, mockJenkins);
            org.jenkinsci.plugins.sosdefence.DescriptorImpl descriptorMock = mock(org.jenkinsci.plugins.sosdefence.DescriptorImpl.class);
            when(mockJenkins.getDescriptorByType(org.jenkinsci.plugins.sosdefence.DescriptorImpl.class)).thenReturn(descriptorMock);
            ProjectProperties.DescriptorImpl uut = new ProjectProperties.DescriptorImpl();

            uut.doFillParentIdItems("url", "key", null);

            ReflectionUtils.setField(instanceField, null, origJenkins);
            verify(mockJenkins).getDescriptorByType(org.jenkinsci.plugins.sosdefence.DescriptorImpl.class);
            verify(descriptorMock).doFillProjectIdItems("url", "key", null);
        }
    }
}
