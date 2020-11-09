package com.example.projetIWA.controllers;

import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;


    //get tous les users
    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

    //get un user avec tel username
    @GetMapping @RequestMapping("{id}")
    public User get(@PathVariable String id) {
        if(!userRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }
        return userRepository.getOne(id);
    }

    //creer un user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user) {
        return  userRepository.saveAndFlush(user);
    }

    //delete un user
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        // Toujours verifier s'il faut supprimer aussi
        // les enregistrements enfants
        userRepository.deleteById(id);
    }

    //Update un user
    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public User update(@PathVariable String id, @RequestBody User user) {
        // TODO: Ajouter ici une validation si tous
        // les champs ont ete passes
        // Sinon, retourner une erreur 400 (Bad Payload)
        User existingUser = userRepository.getOne(id);
        BeanUtils.copyProperties(user,existingUser,"username");
        return userRepository.saveAndFlush(existingUser);
    }
}
