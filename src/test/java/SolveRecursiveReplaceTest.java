import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;
import java.util.function.BiFunction;

import static org.junit.Assert.*;

public final class SolveRecursiveReplaceTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(3);

    private BiFunction<List<String>, Map<String, String>, List<String>> recursiveReplace;

    @Before
    public void setUp() {
        recursiveReplace = new SolveRecursiveReplace();
        //recursiveReplace = new RecursiveReplaceForLoopWhile();
        //recursiveReplace = new RecursiveReplaceForLoopDoWhile();
        //recursiveReplace = new RecursiveReplaceForLoopRecursion();
        //recursiveReplace = new RecursiveReplaceStreamRecursion();
    }

    @Test
    public void applySuccessNoReplacements0() {
        final List<String> sequence = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");
        List<String> expectedList = new ArrayList<>(sequence);

        final Map<String, String> replacements = new HashMap<>();
        replacements.put("Z", "A");
        replacements.put("Y", "B");
        replacements.put("X", "C");

        List<String> results = recursiveReplace.apply(sequence, replacements);

        assertNotNull(results);
        assertEquals(expectedList, results);
    }

    @Test
    public void applySuccessReplacements1() {
        final List<String> sequence = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");

        final Map<String, String> replacements = new HashMap<>();
        replacements.put("B", "Z");
        replacements.put("F", "X");
        replacements.put("X", "Y");

        List<String> results = recursiveReplace.apply(sequence, replacements);

        assertNotNull(results);
        List<String> expectedList = Arrays.asList("A", "Z", "C", "D", "E", "Y", "G", "H", "I");
        assertEquals(expectedList, results);
    }

    @Test
    public void applySuccessReplacements2() {
        final List<String> sequence = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");

        final Map<String, String> replacements = new HashMap<>();
        replacements.put("A", "B");
        replacements.put("B", "C");
        replacements.put("C", "D");
        replacements.put("D", "E");
        replacements.put("E", "F");
        replacements.put("F", "G");
        replacements.put("G", "H");
        replacements.put("H", "I");

        List<String> results = recursiveReplace.apply(sequence, replacements);

        assertNotNull(results);
        List<String> expectedList = Arrays.asList("I", "I", "I", "I", "I", "I", "I", "I", "I");
        assertEquals(expectedList, results);
    }

    @Test
    public void applySuccessReplacements3() {
        final List<String> sequence = Arrays.asList("A", "C", "E", "G", "I", "K", "M", "O", "Q");

        final Map<String, String> replacements = new HashMap<>();
        replacements.put("A", "B");
        replacements.put("C", "D");
        replacements.put("E", "F");
        replacements.put("G", "H");
        replacements.put("I", "J");
        replacements.put("K", "L");
        replacements.put("M", "N");
        replacements.put("O", "P");
        replacements.put("Q", "R");

        List<String> results = recursiveReplace.apply(sequence, replacements);

        assertNotNull(results);
        List<String> expectedList = Arrays.asList("B", "D", "F", "H", "J", "L", "N", "P", "R");
        assertEquals(expectedList, results);
    }

    @Test
    public void applyFailInfintiteLoop1() {
        final List<String> sequence = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");

        final Map<String, String> replacements = new HashMap<>();
        replacements.put("A", "A");
        replacements.put("B", "Z");
        replacements.put("F", "X");
        replacements.put("X", "Y");

        List<String> results = recursiveReplace.apply(sequence, replacements);

        assertNotNull(results);
        List<String> expectedList = Arrays.asList("ERROR", "Z", "C", "D", "E", "Y", "G", "H", "I");
        assertEquals(expectedList, results);
    }

    @Test
    public void applyFailInfintiteLoop2() {
        final List<String> sequence = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");

        final Map<String, String> replacements = new HashMap<>();
        replacements.put("A", "B");
        replacements.put("B", "C");
        replacements.put("C", "A");
        replacements.put("F", "X");
        replacements.put("X", "Y");

        List<String> results = recursiveReplace.apply(sequence, replacements);

        assertNotNull(results);
        List<String> expectedList = Arrays.asList("ERROR", "ERROR", "ERROR", "D", "E", "Y", "G", "H", "I");
        assertEquals(expectedList, results);
    }

    @Test
    public void applyFailInfintiteLoop3() {
        final List<String> sequence = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");

        final Map<String, String> replacements = new HashMap<>();
        replacements.put("A", "B");
        replacements.put("B", "C");
        replacements.put("C", "D");
        replacements.put("D", "B");
        replacements.put("F", "X");
        replacements.put("X", "Y");

        List<String> results = recursiveReplace.apply(sequence, replacements);

        assertNotNull(results);
        List<String> expectedList = Arrays.asList("ERROR", "ERROR", "ERROR", "ERROR", "E", "Y", "G", "H", "I");
        assertEquals(expectedList, results);
    }

    @Test
    public void applyFailInfintiteLoop4() {
        final List<String> sequence = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");

        final Map<String, String> replacements = new HashMap<>();
        replacements.put("A", "B");
        replacements.put("B", "C");
        replacements.put("C", "D");
        replacements.put("D", "A");
        replacements.put("F", "G");
        replacements.put("G", "H");
        replacements.put("H", "I");
        replacements.put("I", "F");

        List<String> results = recursiveReplace.apply(sequence, replacements);

        assertNotNull(results);
        List<String> expectedList = Arrays.asList("ERROR", "ERROR", "ERROR", "ERROR", "E", "ERROR", "ERROR", "ERROR", "ERROR");
        assertEquals(expectedList, results);
    }
}