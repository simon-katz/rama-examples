// Example from https://redplanetlabs.com/docs/~/tutorial4.html
// Run from Groovy REPL with:
//   nomis_stuff.dataflow.ExampleNUnify.main(null)

package nomis_stuff.dataflow;

import com.rpl.rama.Block;
import com.rpl.rama.ops.Ops;
import com.rpl.rama.ops.OutputCollector;
import com.rpl.rama.ops.RamaOperation0;

public class Example005BranchAndUnify {
    public static class Brancher implements RamaOperation0 {
        @Override
        public void invoke(OutputCollector collector) {
            collector.emitStream("pizzaOrders", 1);
            collector.emitStream("saladOrders", 10);
            collector.emitStream("saladOrders", 11);
        }
    }

    public static void unifyExample() {
        Block.each(new Brancher()).outStream("pizzaOrders", "pizzaAnchor1", "*pizzaOrderSize")
                .outStream("saladOrders", "saladAnchor1", "*saladOrderSize")
                .each(Ops.DEC, "*saladOrderSize").out("*orderSize")
                .anchor("saladAnchor2")
                .hook("pizzaAnchor1")
                .each(Ops.INC, "*pizzaOrderSize").out("*orderSize")
                .anchor("pizzaAnchor2")
                .unify("pizzaAnchor2", "saladAnchor2")
                .each(Ops.PRINTLN, "*orderSize")
                .execute();
    }

    public static void main(String[] args) throws Exception {
        unifyExample();
    }
}
