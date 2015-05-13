package WebCalc1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class TestLogicWebProgram {
    String strTest1 = "2^3!";
    String strTest2 = "Sin(Pi/6)";


    LogicWebProgram logicWebProgram;
    @Before
    public void setUp() throws Exception {
        logicWebProgram = new LogicWebProgram();
    }

    @Test
    public void testMain() throws Exception {
        assertEquals("64",logicWebProgram.mainMethod(strTest1));
    }
    @Test
    public void testMain2() throws Exception {
        assertEquals("0.5",logicWebProgram.mainMethod(strTest2));
    }




}
