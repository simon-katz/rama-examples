package nomis_stuff.dataflow;

import com.rpl.rama.Block;
import com.rpl.rama.Expr;
import com.rpl.rama.LoopVars;
import com.rpl.rama.ops.Ops;

public class Example009LoopWithVars {

    public static void loopExample() {
        Block.loopWithVars(LoopVars.var("*i", 0),
                        Block.ifTrue(new Expr(Ops.NOT_EQUAL, "*i", 5),
                                Block.emitLoop("*i")
                                        .each(Ops.PRINTLN, "Variable *i is not 5 yet")
                                        .continueLoop(new Expr(Ops.INC, "*i")))).out("*loopValue")
                .each(Ops.PRINTLN, "Emitted:", "*loopValue")
                .execute();
    }

    public static void main(String[] args) throws Exception {
        loopExample();
    }
}
