package wordCount;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * Created with IntelliJ IDEA.
 * User: an
 * Date: 2022/3/31
 * Time: 11:46
 * Description: wordCount 批处理       基本被弃用了
 */
public class BatchWordCount {
    public static void main(String[] args) throws Exception {
        // 配置flink环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // 输入数据
        DataSource<String> lineDataSource = env.readTextFile("input/word.txt");
        // 扁平化map每一个数据，并搞成元组
        // Collector是收集器，收集结果，，，，，，最后返回的结果类型就是收集器收集类型
        FlatMapOperator<String, Tuple2<String, Long>> wordAndOneTuple = lineDataSource.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            // 将一行文本进行分词
            String[] words = line.split(" ");
            // 将每个单词转换成二元组输出
            for (String word : words) {
                out.collect(Tuple2.of(word, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        System.out.println("");
        wordAndOneTuple.print();
        System.out.println("");

        // 按照word进行分组
        UnsortedGrouping<Tuple2<String, Long>> wordOneGroup = wordAndOneTuple.groupBy(0);

        // 分组内进行聚合统计 field 是元组中的索引位置
        AggregateOperator<Tuple2<String, Long>> sum = wordOneGroup.sum(1);


        sum.print();
    }


    
}
