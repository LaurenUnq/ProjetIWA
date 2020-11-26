package com.example.projetIWA.controllers;

import com.example.projetIWA.auth.AuthService;
import com.example.projetIWA.models.Notification;
import com.example.projetIWA.models.User;
import com.example.projetIWA.services.NotificationsService;
import com.example.projetIWA.services.UsersServices;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class NotificationViewController {

    @Autowired
    private NotificationsService notificationsService;

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private AuthService authService;

    /**
     * show the notifications view for user connected
     * @param model
     * @return the notification view
     */
    @GetMapping("/notificationView")
    public String getUserInfo(Model model) {

        String userId = this.authService.getUserIdByContext();
        if(!usersServices.userExist(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+userId+" not found");
        }
        else{
            List<Notification> notifications = notificationsService.getAllNotificationByUserId(userId);
            long nbNotificationNotViewed = notificationsService.getNumberNotificationNotViewedByUserId(userId);
            model.addAttribute("notifications", notifications);
            model.addAttribute("nbNotificationNotViewed", nbNotificationNotViewed);
        }

        return "notification";
    }

    /**
     * create notification for users who have been in contact with the given positive user (user connected)
     * @return notification view
     */
    @PostMapping("/notificationView")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(Model model) {
        String userId = this.authService.getUserIdByContext();
        if(!usersServices.userExist(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User with ID "+userId+" not found");
        }
        else{
            User user = this.usersServices.findById(userId);
            notificationsService.createNotificationsFromPositiveUser(user);
            List<Notification> notifications = notificationsService.getAllNotificationByUserId(userId);
            long nbNotificationNotViewed = notificationsService.getNumberNotificationNotViewedByUserId(userId);
            model.addAttribute("notifications", notifications);
            model.addAttribute("nbNotificationNotViewed", nbNotificationNotViewed);
        }
        return "notification";
    }
}
