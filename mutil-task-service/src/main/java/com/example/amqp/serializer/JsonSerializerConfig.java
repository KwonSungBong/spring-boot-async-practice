package com.example.amqp.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.nio.serialization.ByteArraySerializer;

import java.io.IOException;

public class JsonSerializerConfig extends SerializerConfig {

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonSerializerConfig(Class targetClass, int typeId) {
        setTypeClass(targetClass);
        setImplementation(new ByteArraySerializer() {
            @Override
            public int getTypeId() {
                return typeId;
            }

            @Override
            public void destroy() {

            }

            @Override
            public byte[] write(Object object) throws IOException {
                return mapper.writeValueAsBytes(object);
            }

            @Override
            public Object read(byte[] buffer) throws IOException {
                return mapper.readValue(buffer, targetClass);
            }
        });
    }
}
