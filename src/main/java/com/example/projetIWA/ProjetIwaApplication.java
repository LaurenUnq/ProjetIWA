package com.example.projetIWA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.io.IOException;

@SpringBootApplication
public class ProjetIwaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ProjetIwaApplication.class, args);
/*
		String serverUrl = "http://localhost:8180/auth";
		String realm = "ProjetIWA";
		// idm-client needs to allow "Direct Access Grants: Resource Owner Password Credentials Grant"
		String clientId = "login-app";

//		// Client "idm-client" needs service-account with at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"
//		Keycloak keycloak = KeycloakBuilder.builder() //
//				.serverUrl(serverUrl) //
//				.realm(realm) //
//				.grantType(OAuth2Constants.CLIENT_CREDENTIALS) //
//				.clientId(clientId) //
//				.clientSecret(clientSecret).build();

		// User "idm-admin" needs at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"
		Keycloak keycloak = KeycloakBuilder.builder() //
				.serverUrl(serverUrl) //
				.realm(realm) //
				.grantType(OAuth2Constants.PASSWORD) //
				.clientId(clientId) //
				.username("lauren") //
				.password("lauren") //
				.build();

		// Define user
		UserRepresentation user = new UserRepresentation();
		user.setEnabled(true);
		user.setUsername("tester1");
		user.setFirstName("First");
		user.setLastName("Last");
		user.setEmail("tom+tester1@tdlabs.local");
		user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

		// Get realm
		RealmResource realmResource = keycloak.realm(realm);
		UsersResource usersRessource = realmResource.users();

		// Create user (requires manage-users role)
		Response response = usersRessource.create(user);
		System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
		System.out.println(response.getLocation());
		String userId = CreatedResponseUtil.getCreatedId(response);

		System.out.printf("User created with userId: %s%n", userId);

		// Define password credential
		CredentialRepresentation passwordCred = new CredentialRepresentation();
		passwordCred.setTemporary(false);
		passwordCred.setType(CredentialRepresentation.PASSWORD);
		passwordCred.setValue("test");

		UserResource userResource = usersRessource.get(userId);

		// Set password credential
		userResource.resetPassword(passwordCred);

//        // Get realm role "tester" (requires view-realm role)
		RoleRepresentation testerRealmRole = realmResource.roles()//
				.get("tester").toRepresentation();
//
//        // Assign realm role tester to user
		userResource.roles().realmLevel() //
				.add(Arrays.asList(testerRealmRole));
//
//        // Get client
		ClientRepresentation app1Client = realmResource.clients() //
				.findByClientId("app-frontend-springboot").get(0);
//
//        // Get client level role (requires view-clients role)
		RoleRepresentation userClientRole = realmResource.clients().get(app1Client.getId()) //
				.roles().get("user").toRepresentation();
//
//        // Assign client level role to user
		userResource.roles() //
				.clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));
*/
	}

}
