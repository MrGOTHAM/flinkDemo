package chapter05;

import chapter05.pojo.Event;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: an
 * Date: 2022/4/6
 * Time: 11:24
 * Description:
 */
public class SourceTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

//        // 从文件读取数据
//        DataStreamSource<String> source1 = env.readTextFile("input/clicks.txt");
//        source1.print();

        // 从集合读取数据
//        ArrayList<Integer> list =new ArrayList<>();
//        list.add(2);
//        list.add(5);
//        DataStreamSource<Integer> numStream = env.fromCollection(list);
//        numStream.print();

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event("mary","./home",16516516666L));
        eventList.add(new Event("mary","./home",16516516666L));
        eventList.add(new Event("mary","./home",16516516666L));
        DataStreamSource<Event> eventStream = env.fromCollection(eventList);
        eventStream.print("2");


        env.execute();


    }
}
