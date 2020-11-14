package com.example.projetIWA.controllers;

import com.example.projetIWA.models.Location;
import com.example.projetIWA.services.LocationsService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;

@Controller
public class LocationsViewController {

    @Autowired
    private LocationsService locationsService;

    @Autowired
    private UsersServices usersServices;

    /**
     * show the locationView for user connected
     * @param model
     * @return the location view
     */
    @GetMapping("/locationView")
    public String getUserInfo(Model model) {
        model.addAttribute("location", new Location());
        return "location";
    }

    /**
     * Create localisation for user connected
     * @param location - the location
     * @return the location view
     */
    @PostMapping("/locationView")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute Location location) {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();

        final Principal principal = (Principal) authentication.getPrincipal();

        if (principal instanceof KeycloakPrincipal) {

            KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            IDToken token = kPrincipal.getKeycloakSecurityContext()
                    .getIdToken();

            Map<String, Object> customClaims = token.getOtherClaims();
            //Recuperer l'id du user actuel
            String userId = token.getSubject();

            if(!usersServices.userExist(userId)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User with ID "+userId+" not found");
            }
            else{
                this.locationsService.create(location, userId);
            }
        }
        return "location";
    }
}
