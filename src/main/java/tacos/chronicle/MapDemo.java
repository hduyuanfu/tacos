package tacos.chronicle;


import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import net.openhft.chronicle.values.Values;

import java.util.HashMap;


/**
 * @author yuanfu
 * @date 2021/9/17 21:23
 */
public class MapDemo {
    // ChronicleMapBuilder<String, String> builder =
    //         ChronicleMapBuilder.of(String.class, String.class)
    //                 .minSegments(512)
    //                 .averageValue(new BondVOImpl())
    //                 .maxBloatFactor(4.0)
    //                 .valueMarshaller(new BytesMarshallableReaderWriter<>(BondVOImpl.class))
    //                 .entries(ITERATIONS);

    public static void main(String[] args) {
        ChronicleMap<String, String> bu =
                ChronicleMap.of(String.class, String.class)
                        .name("haha")
                        .averageKey("1")
                        .averageValue("haha")
                        .entries(5000)
                        .create();

        bu.put("1", "gansha");
        bu.put("nidaye", "我是你大爷");
        System.out.println(bu.get("1"));
        System.out.println(bu.get("nidaye"));
        bu.replace("1", "haha");
        System.out.println(bu.get("1"));

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
    }



}
