package com.codefans.opensource.rocketmq.official;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author caishengzhi
 * @date 2018/2/6 15:07
 */

public class SyncProducer {

    private String namesrvAddr;

    private String producerGroup;

    private String topicName;

    private DefaultMQProducer producer;

    public SyncProducer(String namesrvAddr, String producerGroup, String topicName) {
        this.namesrvAddr = namesrvAddr;
        this.producerGroup = producerGroup;
        this.topicName = topicName;
        this.initProducer();
    }

    public void initProducer() {
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
    }

    public void sendSingleMsgSync(String msgStr) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        producer.start();
        //Create a message instance, specifying topic, tag and message body.
        Message msg = new Message(topicName /* Topic */,
                "TagA" /* Tag */,
                msgStr.getBytes("UTF-8") /* Message body */
        );
        //Call send message to deliver message to one of brokers.
        SendResult sendResult = producer.send(msg);
        System.out.printf("%s%n", sendResult);
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

    public static void main(String[] args) throws Exception {

        String producerGroup = "producerGroup";
//        String namesrvAddr = "namesrvIp:port";
        String namesrvAddr = "localhost:9876";
        String topicName = "topicName";
        String msgContent = "{\"projectCode\":\"dfdfdfd\",\"orderId\":\"11111111112\",\"orderStatus\":\"14\"}";
        SyncProducer syncProducer = new SyncProducer(namesrvAddr, producerGroup, topicName);
        syncProducer.sendSingleMsgSync(msgContent);
        
    }


    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }


}
