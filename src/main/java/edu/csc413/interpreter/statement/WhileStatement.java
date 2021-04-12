
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 4/10/2021
 *
 * Purpose:
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
import edu.csc413.interpreter.expression.Condition;

import java.util.List;

public class WhileStatement extends ConditionalStatement {

    public WhileStatement(Condition condition, List<Statement> body) {
        super(condition, body);

    }

    @Override
    public void run(ProgramState programState) {
        while (this.getCondition().evaluate(programState)) {

            /*
            Slight optimization by preventing unnecessary statements from running after a return statement has been
            called. This is necessary to prevent print statements and unnecessary calculations from being ran after
            the return statement has been called.
            */
            if (programState.hasReturnValue()) {
                break;
            }

            // Old way
//            for (Statement statement : this.getBodyStatements()) {
//                statement.run(programState);
//            }

            // New way
            this.executeStatements(programState);
        }
    }
}
