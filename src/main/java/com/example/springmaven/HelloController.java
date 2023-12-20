package com.example.springmaven;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {
    List<Message> messageList = new ArrayList<>();

    @GetMapping("/api/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @PostMapping("/api/hello")
    public String sayHello(@RequestBody String name) {
        return "Hello" + name;
    }

    @GetMapping("/api/hello/{name}")
    public String getProductById(@PathVariable String name) {
        return "Hello, " + name;
    }

    @PostMapping("api/messages")
    public List<Message> addMessageToList(@RequestBody Message message) {
        Message messageToList = new Message();
        messageToList.setName(message.getName());
        messageToList.setText(message.getText());
        messageToList.setId(message.getId());
        messageList.add(messageToList);
        return messageList;

    }

    @GetMapping("api/messages")
    public List<Message> getListOfMessages() {
        return messageList;
    }

    @DeleteMapping("/api/messages/{id}")
    public String deleteProductById(@PathVariable String id) {
        Optional<Message> messageToDelete = messageList.stream()
                .filter(message -> message.getId().equals(id))
                .findFirst();
        if (messageToDelete.isPresent()) {
            messageList.remove(messageToDelete);
            return "Message with id: " + id + " has been deleted";
        } else {
            return "No Message with such id";
        }
    }
}
