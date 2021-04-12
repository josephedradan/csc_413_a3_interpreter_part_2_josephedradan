
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

import edu.csc413.interpreter.expression.Condition;

import java.util.List;

public abstract class ConditionalStatement extends BlockStatement {

    private Condition condition;

    public ConditionalStatement(Condition condition, List<Statement> bodyStatements) {
        super(bodyStatements);
        this.condition = condition;
    }

    protected Condition getCondition() {
        return condition;
    }

}

