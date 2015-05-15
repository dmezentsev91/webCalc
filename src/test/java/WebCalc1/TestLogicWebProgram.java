package WebCalc1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class TestLogicWebProgram {

    LogicWebProgram logicWebProgram;
    @Before
    public void setUp() throws Exception {
        logicWebProgram = new LogicWebProgram();
    }

    @Test
    public void testMain() throws Exception {
        assertEquals("30",logicWebProgram.mainMethod("d asin(1/2)"));
    }
    @Test
    public void testMain2() throws Exception {
        assertEquals("0.5",logicWebProgram.mainMethod("r cos(acos(1/2))"));
    }
    @Test
    public void testMain3() throws Exception {
        assertEquals("37",logicWebProgram.mainMethod("(13^2 + 2*13 -(11*13 - 2)/(3*13+1))/(13 + 1 - (2*13^2 + 13 +2)/(3*13 + 1))"));
    }








}
