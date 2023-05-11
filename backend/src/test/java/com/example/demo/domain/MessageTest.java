package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test
    void compareEquals() {
        Message message1 = new Message();
        message1.setMessage("message");
        message1.setDate("date");
        message1.setReceiverName("receiverName");
        message1.setSenderName("senderName");
        message1.setStatus(Status.MESSAGE);

        Message message2 = new Message();
        message2.setMessage("message");
        message2.setDate("date");
        message2.setReceiverName("receiverName");
        message2.setSenderName("senderName");
        message2.setStatus(Status.MESSAGE);

        assertEquals(message1.getMessage(), message2.getMessage());
        assertEquals(message1.getDate(), message2.getDate());
        assertEquals(message1.getReceiverName(), message2.getReceiverName());
        assertEquals(message1.getSenderName(), message2.getSenderName());
        assertEquals(message1.getStatus(), message2.getStatus());
    }

    @Test
    void compareNotEquals() {
        Message message1 = new Message();
        message1.setMessage("message1");
        message1.setDate("date");
        message1.setReceiverName("receiverName");
        message1.setSenderName("senderName");
        message1.setStatus(Status.MESSAGE);

        Message message2 = new Message();
        message2.setMessage("message2");
        message2.setDate("date");
        message2.setReceiverName("receiverName");
        message2.setSenderName("senderName");
        message2.setStatus(Status.MESSAGE);

        assertNotEquals(message1, message2);
    }
}