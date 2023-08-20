package org.jenkinsci.plugins.sosdefence;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Ronny "Sephiroth" Perinke <sephiroth@sephiroth-j.de>
 */
class ConsoleLoggerTest {

    @Test
    void testLog() throws IOException {
        PrintStream ps = mock(PrintStream.class);
        ConsoleLogger uut = new ConsoleLogger(ps);
        uut.log("test\r\nline2");
        verify(ps).println("[DependencyTrack] test\r\n[DependencyTrack] line2");
    }
    
    @Test
    void testWrite() throws IOException {
        PrintStream ps = spy(new PrintStream(System.err));
        ConsoleLogger uut = new ConsoleLogger(ps);
        uut.write("test\nline2\n".getBytes());
        uut.flush();
        verify(ps, times(2)).append("[DependencyTrack] ");
        verify(ps).write(Arrays.copyOf("line2\n".getBytes(), 32), 0, 6);
    }
}
