package org.example;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        LocalDateTime time = LocalDateTime.now();

        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");

        try (IgniteClient client = Ignition.startClient(cfg)) {
            ClientCache<String, String> cache = client.getOrCreateCache("STRINGCACHE");
            // dummy value
            //MyCustomKey myCustomKey = new MyCustomKey("Adam", "Edsel");
            //MyCustomValue myCustomValue = new MyCustomValue("test value desc");
            cache.put("aStringKey", "dummy");
            // Get data from the cache
            //MyCustomKey myCustomKey2 = new MyCustomKey("Adam", time.toString());
            cache.put("aStringKey2", "test value 2" + time);
            System.out.println(cache.get("aStringKey"));
        }

        try (IgniteClient client = Ignition.startClient(cfg)) {
            ClientCache<MyCustomKey, String> cache = client.getOrCreateCache("PERSONCACHE");
            // dummy value
            MyCustomKey myCustomKey = new MyCustomKey("Adam", "Edsel");
            MyCustomValue myCustomValue = new MyCustomValue("test value desc");
            cache.put(myCustomKey, "dummy");
            // Get data from the cache
            MyCustomKey myCustomKey2 = new MyCustomKey("Adam", time.toString());
            cache.put(myCustomKey2, "test value 2" + time);
            System.out.println(cache.get(myCustomKey2));
        }
        try (IgniteClient client = Ignition.startClient(cfg)) {
            ClientCache<MyCustomKey, MyCustomValue> cache = client.getOrCreateCache("PERSONCACHE2");
            // Get data from the cache
            MyCustomKey myCustomKey = new MyCustomKey("Adam", "Edsel");
            MyCustomValue myCustomValue = new MyCustomValue("test value desc");
            MyCustomValue myCustomValue2 = new MyCustomValue("test value desc 2");
            cache.put(myCustomKey, myCustomValue);
            cache.put(myCustomKey, myCustomValue2);

            System.out.println(cache.get(myCustomKey));
        }

        ClientConfiguration cfgremote = new ClientConfiguration().setAddresses("10.0.0.13:10800");
        try (IgniteClient remoteclient = Ignition.startClient(cfgremote)) {
            ClientCache<MyCustomKey, MyCustomValue> cache = remoteclient.getOrCreateCache("PERSONCACHE");
            // Get data from the cache
            MyCustomKey myCustomKey = new MyCustomKey("Adam", "Edsel");
            MyCustomValue myCustomValue2 = cache.get(myCustomKey);
            System.out.println("remote value = " + myCustomValue2);
        }
    }
}