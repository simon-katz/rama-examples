package nomis_stuff.dataflow;

import com.rpl.rama.Block;
import com.rpl.rama.ops.Ops;
import com.rpl.rama.ops.OutputCollector;
import com.rpl.rama.ops.RamaOperation0;

public class Example008ImmutableScopeAndShadowing {

    public static class Person {
        public String name;

        public Person(String name) {
            this.name = name;
        }
    }

    public static class MultiOut implements RamaOperation0 {
        @Override
        public void invoke(OutputCollector collector) {
            collector.emitStream("stream1");
            collector.emitStream("stream2");
        }
    }

/* I can't get this to compile.
    public static void shadowExample() {
        Block.each(Ops.IDENTITY, new Person("Megan")).out("*person")
                .each(new MultiOut()).outStream("stream1", "stream1Anchor")
                .outStream("stream2", "stream2Anchor")
                .each(Ops.IDENTITY, new Person("John")).out("*person")
                .hook("stream1Anchor")
                .each((Person person) -> person.name).out("*personName")
                .each(Ops.PRINTLN, "*stream1Name")
                .execute();
    }

    public static void shadowMutationExample() {
        Block.each(Ops.IDENTITY, new Person("Megan")).out("*person")
                .each(new MultiOut()).outStream("stream1", "stream1Anchor")
                .outStream("stream2", "stream2Anchor")
                .each((Person person) -> {
                    person.name = "John";
                    return null;
                })
                .hook("stream1Anchor")
                .each((Person person) -> person.name).out("*name")
                .each(Ops.PRINTLN, "*name")
                .execute();
    }
*/
}
