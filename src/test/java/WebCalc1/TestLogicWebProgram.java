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
    @Test
    public void testMain4() throws Exception {
        assertEquals("4",logicWebProgram.mainMethod("d sqrt(2*(1/sin(30)^((3!^2)/12)))"));
    }
    @Test
    public void testMain5() throws Exception {
        assertEquals("error",logicWebProgram.mainMethod("d tan(90 + 360*15)"));
    }
    @Test
    public void testMain6() throws Exception {
        assertEquals("error",logicWebProgram.mainMethod("ctan(15 * pi)"));
    }











}
