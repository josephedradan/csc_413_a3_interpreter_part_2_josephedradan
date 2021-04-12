
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 4/10/2021
 *
 * Purpose:
 *      Mimic python's "for i in range()"
 *
 * Details:
 *
 * Description:
 *
 * Notes:
 *
 * IMPORTANT NOTES:
 *
 * Explanation:
 *
 * Reference:
 *
 */

package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Expression;

import java.util.List;

public class ForStatement extends BlockStatement {

    private final String loopVariableName;
    private final Expression expressionRangeStart;
    private final Expression expressionRangeEnd;

    public ForStatement(String loopVariableName, Expression start, Expression stop, List<Statement> body) {
        super(body);
        this.loopVariableName = loopVariableName;
        this.expressionRangeStart = start;
        this.expressionRangeEnd = stop;
    }

    /*
    Recall that the python's range function is evaluated once and not the for or while loop itself
    e.g.
        # Will run forever and use up memory
        x = [1]
        for i in x:
            x.append(len(x) + 1)
            print(i)
    */
    @Override
    public void run(ProgramState programState) {


        // The clean way
        int end = this.expressionRangeEnd.evaluate(programState);

        for (int i = this.expressionRangeStart.evaluate(programState); i < end; i++) {

            /*
            Slight optimization by preventing unnecessary statements from running after a return statement has been
            called. This is necessary to prevent print statements and unnecessary calculations from being ran after
            the return statement has been called.
            */
            if (programState.hasReturnValue()) {
                break;
            }

            /*
            Change this.loopVariableName value to i

            Must be called before statement executions because the interpreter's
            this.loopVariableName does not exist yet.
             */
            programState.setVariable(this.loopVariableName, i);

            // Old way
//            for (Statement statement : this.getBodyStatements()) {
//                statement.run(programState);
//            }

            // New way
            this.executeStatements(programState);
        }


        // The technically correct way (follows the structure of how a loop should work internally)
//        int start = this.expressionRangeStart.evaluate(programState);
//        int end = this.expressionRangeEnd.evaluate(programState);
//
//        programState.setVariable(this.loopVariableName, this.expressionRangeStart.evaluate(programState));
//
//        while(start < end){
//            if (programState.hasReturnValue()) {
//                break;
//            }
//
//            this.executeStatements(programState);
//
//            start++;
//            programState.setVariable(this.loopVariableName, start);
//        }
    }
}
