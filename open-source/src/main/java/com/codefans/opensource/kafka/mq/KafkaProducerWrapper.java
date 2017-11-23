package com.codefans.opensource.kafka.mq;

import org.apache.commons.collections.CollectionUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.*;
import java.util.concurrent.Future;

/**
 * @author: caishengzhi
 * @date: 2017-11-23 17:30
 **/
public class KafkaProducerWrapper<K, V> {

    List<String> bootstrapServerList;

    List<String> topicList;

    List<Producer> producerList;

    public KafkaProducerWrapper(List<String> bootstrapServerList, List<String> topicList) {
        this.bootstrapServerList = bootstrapServerList;
        this.topicList = topicList;
    }

    public void init() {

    }

    public Properties getDefaultProperties() {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    public List<Future<RecordMetadata>> sendOrderedRecords(LinkedHashMap<K, V> records) {
        List<Future<RecordMetadata>> futureResultList = new ArrayList<Future<RecordMetadata>>();

        sendMapRecords(records);

        return futureResultList;
    }

    public List<Future<RecordMetadata>> sendRecords(Map<K, V> records) {
        List<Future<RecordMetadata>> futureResultList = new ArrayList<Future<RecordMetadata>>();

        sendMapRecords(records);

        return futureResultList;
    }

    public List<Future<RecordMetadata>> sendMapRecords(Map<K, V> records) {
        List<Future<RecordMetadata>> futureResultList = new ArrayList<Future<RecordMetadata>>();

        Iterator<K> iterator = records.keySet().iterator();
        K key;
        V value;
        while(iterator.hasNext()) {
            key = iterator.next();
            value = records.get(key);
            sendRecord(key, value);
        }

        return futureResultList;
    }

    public Future<RecordMetadata> sendRecord(K key, V value) {
        Future<RecordMetadata> future = null;

        if(CollectionUtils.isNotEmpty(this.bootstrapServerList)) {
            String bootstrapServer = "";
//            Properties properties = null;
//            Producer<String, String> producer = null;
            for(int i = 0; i < this.bootstrapServerList.size(); i ++) {
                bootstrapServer = bootstrapServerList.get(i);
                this.sendRecord(bootstrapServer, topicList, key, value);

//                properties = this.getDefaultProperties();
//                properties.put("bootstrap.servers", bootstrapServer);
//                producer = new KafkaProducer<String, String>(properties);
//                if(CollectionUtils.isNotEmpty(topicList)) {
//                    String topic = "";
//                    for(int j = 0; j < topicList.size(); j ++) {
//                        topic = topicList.get(j);
//                        producer.send(new ProducerRecord<String, String>(topic, key, value));
//                    }
//                }
//                producer.close();

            }
        }

        return future;
    }

    public Future<RecordMetadata> sendRecord(String bootstrapServer, List<String> topics, K key, V value) {
        Future<RecordMetadata> future = null;

        Properties properties = null;
        Producer<K, V> producer = null;
        properties = this.getDefaultProperties();
        properties.put("bootstrap.servers", bootstrapServer);
        producer = new KafkaProducer<K, V>(properties);
        if(CollectionUtils.isNotEmpty(topicList)) {
            String topic = "";
            for(int j = 0; j < topicList.size(); j ++) {
                topic = topicList.get(j);
                this.sendRecord(producer, topic, key, value);
            }
        }
        producer.close();

        return future;
    }

    public Future<RecordMetadata> sendRecord(Producer<K, V> producer, String topic, K key, V value) {
        Future<RecordMetadata> future = null;
        future = producer.send(new ProducerRecord<K, V>(topic, key, value));
        return future;
    }

    public static void main(String[] args) {

    }


}
