package guru.springframework.sfgpetclinic.fauxspring;

import java.util.HashMap;
import java.util.UUID;

public class HashMapModel implements Model {

    private HashMap<String, Object> attributes = new HashMap<>();

    @Override
    public void addAttribute(String key, Object o) {
        attributes.put(key, o);
    }

    @Override
    public void addAttribute(Object o) {
        attributes.put(UUID.randomUUID().toString(), o);
    }

    public boolean contains(String key) {
        return attributes.containsKey(key);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

}
