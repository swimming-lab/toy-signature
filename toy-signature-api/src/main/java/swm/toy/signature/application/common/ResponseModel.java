package swm.toy.signature.application.common;

import java.util.HashMap;
import java.util.Map;

public class ResponseModel {

    public static Map<String, Object> response(String name, Object object) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(name, object);
        return map;
    }
}
