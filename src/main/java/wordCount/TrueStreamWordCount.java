package wordCount;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * Created with IntelliJ IDEA.
 * User: an
 * Date: 2022/3/31
 * Time: 15:31
 * Description: 无界流 wordCount   虚拟机 nc -lk 7777  在7777端口输入无界
 */
public class TrueStreamWordCount {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment ev = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> ds = ev.socketTextStream("150.158.90.84",7777);
        SingleOutputStreamOperator<Tuple2<String, Long>> streamTuples = ds.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            String[] words = line.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        // 分组操作，将具有相同key的数据分到同一个组
        KeyedStream<Tuple2<String, Long>, String> tuple2StringKeyedStream = streamTuples.keyBy(data -> data.f0);

        SingleOutputStreamOperator<Tuple2<String, Long>> sum = tuple2StringKeyedStream.sum(1);

        sum.print();
        ev.execute();
        // 结果中 前面的数字是指在哪个线程出来的结果，电脑16核就会 1-16




    }


}
