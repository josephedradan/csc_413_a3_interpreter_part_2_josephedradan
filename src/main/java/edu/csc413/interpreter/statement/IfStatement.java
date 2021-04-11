package edu.csc413.interpreter.statement;

import edu.csc413.interpreter.ProgramState;
import edu.csc413.interpreter.expression.Condition;
import java.util.List;

/**
 * Statement representing an if-statement, with a condition check and a list of statements that are only run if the
 * condition evaluates to true.
 */
// TODO: Implement. IfStatement and WhileStatement share a lot in common; find a way to avoid code duplication.
public class IfStatement implements Statement {
    private Condition condition;
    private List<Statement> body;

    public IfStatement(Condition condition, List<Statement> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void run(ProgramState programState) {
        if (condition.evaluate(programState)) {
            for (Statement statement: body) {
                statement.run(programState);
            }
        }
    }
}
