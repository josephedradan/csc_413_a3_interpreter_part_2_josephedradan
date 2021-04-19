package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Condition;

import java.util.List;

/**
 * Statement representing an if-statement, with a condition check and a list of statements that are only run if the
 * condition evaluates to true.
 */
public class IfStatement extends ConditionalStatement {

    public IfStatement(Condition condition, List<Statement> bodyStatements) {
        super(condition, bodyStatements);
    }

    @Override
    public void run(ProgramState programState) {
        if (this.getCondition().evaluate(programState)) {
            executeStatements(programState);
        }
    }
}
