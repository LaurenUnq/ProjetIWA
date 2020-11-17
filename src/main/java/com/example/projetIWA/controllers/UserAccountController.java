package com.example.projetIWA.controllers;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import com.example.projetIWA.models.User;
import com.example.projetIWA.services.UsersServices;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class UserAccountController {

    @Autowired
    private UsersServices userServices;

    @GetMapping("/userAccount")
    public String getUserInfo(Model model) {

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

            if(!userServices.userExist(userId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+userId+" not found");
            }
            else{
                User session = userServices.findById(userId);
                if(session != null){
                    model.addAttribute("firstName", session.getFirst_name());
                    model.addAttribute("lastName", session.getLast_name());
                    model.addAttribute("email", session.getEmail());
                    model.addAttribute("username", session.getUsername());
                }

            }

        }


        return "userInfo";
    }

}