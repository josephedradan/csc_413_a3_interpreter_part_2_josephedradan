
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

import java.util.List;

public abstract class BlockStatement implements Statement {

    private List<Statement> bodyStatements;

    public BlockStatement(List<Statement> bodyStatements) {

        this.bodyStatements = bodyStatements;
    }

    protected List<Statement> getBodyStatements() {
        return bodyStatements;
    }

    protected void executeStatements(ProgramState programState) {
        // Execute body statements
        for (Statement statement : bodyStatements) {

            /*
            Slight optimization by preventing unnecessary statements from running after a return statement has been
            called. This is necessary to prevent print statements and unnecessary calculations from being ran after
            the return statement has been called.
            */
            if (programState.hasReturnValue()) {
                break;
            }

            statement.run(programState);

        }
    }
}
