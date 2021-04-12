
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

package edu.csc413.interpreter.expression;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.statement.ReturnStatement;
import edu.csc413.interpreter.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class FunctionCallExpression implements Expression {

    private String functionName;
    private List<Expression> expressionsPseudoArguments;

    public FunctionCallExpression(String functionName, List<Expression> expressionsPseudoArguments) {
        this.functionName = functionName;
        this.expressionsPseudoArguments = expressionsPseudoArguments;
    }

    @Override
    public int evaluate(ProgramState programState) {

        // Get function information
        List<Statement> statements = programState.getFunctionStatements(this.functionName);
        List<String> parameterNames = programState.getParameterNames(this.functionName);

        // Check if the size of parameters and size of arguments match
        if (parameterNames.size() != expressionsPseudoArguments.size()) {
            throw new RuntimeException(String.format("%s() takes %d positional arguments but %d were given",
                    this.functionName,
                    parameterNames.size(),
                    expressionsPseudoArguments.size()));
        }

        // Extract arguments from expressions
        List<Integer> arguments = new ArrayList<>();
        for (Expression expression : expressionsPseudoArguments) {
            Integer evaluate = expression.evaluate(programState);
            arguments.add(evaluate);
        }

        // Make new stack frame and go to it
        programState.addCallFrame();

        // Make key value pair for parameterNames and arguments together
        for (int i = 0; i < parameterNames.size(); i++) {
            programState.setVariable(parameterNames.get(i), arguments.get(i));
        }

        // Execute body statements
        for (Statement statement : statements) {

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

        // Go back to the previous stack frame
        programState.removeCallFrame();

        // Get return value and return it
        if (programState.hasReturnValue()) {
            programState.clearReturnValue();
            return programState.getReturnValue();
        }

        // RIP no None
        return 0;
    }
}
