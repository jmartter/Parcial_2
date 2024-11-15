package org.example.parcial_2.Front;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotificationController {

    private List<String> notifications = new ArrayList<>();

    // Método para recibir notificaciones y agregarlas a la lista
    @PostMapping("/notify")
    public void receiveNotification(@RequestBody String message) {
        notifications.add(message);
    }

    // Método para obtener la lista de notificaciones
    @GetMapping("/notifications")
    public List<String> getNotifications() {
        return notifications;
    }
}