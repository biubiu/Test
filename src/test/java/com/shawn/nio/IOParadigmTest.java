package com.shawn.nio;

import com.shawn.IOParadigm.TimeServer;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * User: Shawn cao
 * Date: 14-5-16
 * Time: PM4:59
 */
public class IOParadigmTest extends TestCase {

    @Test
    public void testSingleThreadModule(){
        TimeServer timeServer = new TimeServer();
        timeServer.start(8080);
    }
}
