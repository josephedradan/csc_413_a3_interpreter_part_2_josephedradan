
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

public class DefineFunctionStatement extends BlockStatement {

    private String functionName;
    private List<String> parameterNames;

    public DefineFunctionStatement(String functionName, List<Statement> bodyStatements, List<String> parameterNames) {
        super(bodyStatements);
        this.functionName = functionName;
        this.parameterNames = parameterNames;
    }

    @Override
    public void run(ProgramState programState) {
        programState.registerFunction(this.functionName, this.parameterNames, this.getBodyStatements());
    }
}
