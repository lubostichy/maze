package maze;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import maze.tape.*;
import maze.objects.*;
/**
 *
 * @author Lubos Tichy
 */
public class TestHW3 {
    
    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }
    
    @Test
    public void testTapeObject() {
        TapeObject to1 = TapeObject.create("w");
        TapeObject to2 = TapeObject.create("g");
        TapeObject to3 = TapeObject.create("p");
        TapeObject to4 = TapeObject.create("k");
        TapeObject to5 = TapeObject.create("f");
        
        assertEquals("Object to1 je instanciou triedy Wall.", Wall.class, to1.getClass());
        assertEquals("Object to2 je instanciou triedy Gate.", Gate.class, to2.getClass());
        assertNull("Object to3 je volne policko.", to3);
        assertEquals("Object to4 je instanciou triedy Key.", Key.class, to4.getClass());
        assertEquals("Object to5 je instanciou triedy Finish.", Finish.class, to5.getClass());
        
        assertFalse("Stena sa neda otvorit.", to1.canBeOpen());
        assertTrue("Zatvorena brana sa da otvorit.", to2.canBeOpen());
        to2.open();
        assertFalse("Otvorena brana sa neda otvorit.", to2.canBeOpen());
        
    }
    
    @Test
    public void testTape() {
        System.out.println("game textfile.txt");
        String input = "wwwwwwwwwwwwwwwwwwwwwpppppppppppppppppkwwkwwpwwpwwpwwwwwwwwwwpwwkwwgwwpwwwwwwwwwwgwwpwwpwwgppppppppwwpppppgpwwwwwwwwwwpwwwwpwwwpwwpppppppppwwwwpwwwppppwwwwwwwpwwwwpwwwwFwwwwwwwwwpwwwwpwwwwpwwwwwwwwwpwwwwpwwwwpwwwwwwwwwpwwpppppppppppppppppkwwkwwpwwpwwpwwwwwwwwwwpwwkwwgwwpwwwwwwwwwwpwwpwwpwwgppppppppwwpppppgpwwwwwwwwwwpwwwwpwwwpwwpppppppppwwwwpwwwppppwwwwwwwpwwwwpwwwwwwwwwwwwwwpwwwwwwwwwwwwwwwwwwwpw";
        Tape t = new Tape(20, 20, 1, input);
        TapeHead h = t.createHead(1);
        System.out.println("show");
        t.show(h);
        System.out.println("step");
        System.out.println("Nemozno obsadit stenu");
        System.out.println("show");
        t.show(h);
        assertFalse("Hrac neobsadi stenu.", h.step());
        System.out.println("right");
        System.out.println("OK");
        h.right();
        System.out.println("show");
        t.show(h);
        System.out.println("take");
        System.out.println("Nie je kluc");
        assertFalse("Hrac nevezme kluc, nie je aky vziat.", h.take());
        System.out.println("keys");
        System.out.println("Pocet klucov je 0.");
        assertEquals("Pocet klucov je 0.", h.keys(), 0);
        System.out.println("left");
        System.out.println("OK");
        h.left();
        System.out.println("show");
        t.show(h);
        System.out.println("left");
        System.out.println("OK");
        h.left();
        System.out.println("show");
        t.show(h);        
        System.out.println("left");
        System.out.println("OK");
        h.left();
        System.out.println("show");
        t.show(h);
        System.out.println("step");
        System.out.println("V ceste je kluc");
        assertFalse("Hrac neobsadi policko s klucom.", h.step());
        System.out.println("show");
        t.show(h);
        System.out.println("take");
        System.out.println("OK, hrac vezme kluc");
        assertTrue("Hrac vezme kluc", h.take());
        System.out.println("show");
        t.show(h);
        System.out.println("keys");
        System.out.println("Pocet klucov je 1.");
        assertEquals("Pocet klucov je 1.", h.keys(), 1);
        System.out.println("show");
        t.show(h);
        System.out.println("step");
        System.out.println("OK");
        assertTrue("Hrac obsadi policko.", h.step());
        System.out.println("show");
        t.show(h);
        System.out.println("step");
        System.out.println("OK");
        assertTrue("Hrac obsadi dalsie policko.", h.step());
        System.out.println("show");
        t.show(h);
        System.out.println("step");
        System.out.println("OK, zatvorena brana");
        assertFalse("Hrac neobsadi zatvorenu branu.", h.step());
        System.out.println("show");
        t.show(h);
        System.out.println("open");
        System.out.println("OK, otvori branu lebo ma kluc");
        assertTrue("Hrac pouzije kluc a otvori branu.", h.open());
        System.out.println("show");
        t.show(h);
        System.out.println("keys");
        System.out.println("Pocet klucov je 0");
        assertEquals("Pocet klucov je 0.", h.keys(), 0);
        System.out.println("step");
        System.out.println("OK");
        assertTrue("Hrac obsadi otvorenu branu.", h.step());
    }
    
}
