import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public final class SolveRecursiveReplace implements BiFunction<List<String>, Map<String, String>, List<String>> {

    /**
     * <b>Problem 1:<b/> The "sequence" list will contain values that if they match a key in the "replacements" map,
     * replace the "sequence" value with the "replacements" value.
     *
     * Example "sequence" list: "A", "B", "C", "D", "E", "F", "A"
     *
     * For example, say the "sequence" list has the string "A" and the "replacements" map has a key/value of "A -> B".
     * Then the sequence list should have "A" replaced with "B".  Also, say the "replacements" map has a key/value of
     * "B -> C".  Then the sequence list should have "B" replaced with "C".
     *
     * Resulting "sequence" list from example above: "C", "C", "C", "D", "E", "F", "C"
     *
     * <b>Problem 2:<b/> Once the first part is solved, the next issue is to protect against an infinite loop.  Suppose
     * the "replacements" map has a key/value of "A -> B", "B -> C", "C -> A".  Once this is detected, replace the value
     * with the string "ERROR".
     *
     * Resulting "sequence" list from example above: "ERROR", "ERROR", "ERROR", "D", "E", "F", "ERROR"
     */
    @Override
    public List<String> apply(List<String> sequence, Map<String, String> replacements) {
        for (int i = 0; i < sequence.size(); i++) {
            String item = sequence.get(i);
            if (replacements.containsKey(item)) {
                List<String> changedLetters = new ArrayList<String>();
                String finalLetter = checkAgain(replacements.get(item), replacements, changedLetters);
                sequence.set(i, finalLetter);
            }
        }
        return sequence;
    }

    public String checkAgain(String letter, Map<String, String> replacements, List<String> changedLetters) {
        if(changedLetters.contains(letter)){
            return "ERROR";
        }
        if(replacements.containsKey(letter)){
            String newLetter = replacements.get(letter);
            changedLetters.add(letter);
            return checkAgain(newLetter, replacements, changedLetters);
        }
        return letter;
    }

}