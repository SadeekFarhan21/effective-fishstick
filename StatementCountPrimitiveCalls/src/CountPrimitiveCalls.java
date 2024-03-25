import components.statement.Statement;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                // TODO - fill in case
                int length = s.lengthOfBlock();
                for(int i = 0; i < length; i++){
                    Statement block = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(block);
                    s.addToBlock(i, block);
                }
                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */
                 Statement s1 = s.newInstance();
                 Condition c = s.disassembleIf(s1);
                 count += countOfPrimitiveCalls(s1);
                 s.assembleIf(c, s1);
                // TODO - fill in case

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                // TODO - fill in case
                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                Condition c = s.disassembleIfElse(s1, s2);
                count += (countOfPrimitiveCalls(s1) + countOfPrimitiveCalls(s2));
                s.assembleIfElse(c, s1, s2);
                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                // TODO - fill in case
                Statement s1 = s.newInstance();
                Condition c = s.disassembleWhile(s1);
                count += countOfPrimitiveCalls(s1);
                s.assembleWhile(c, s1);
                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                // TODO - fill in case
                String label = s.disassembleCall();
                if(label.equals("move") || label.equals("turnleft") || label.equals("turnright") || label.equals("infect") || label.equals("skip")){
                    count += 1;
                }

                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
    }

}
