package com.cuscuzcomjava.remotecontroller.controller;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFoundException;
import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.service.ActressService;
import java.rmi.activation.ActivationException;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actress")
public class ActressController {

    @Autowired
    private ActressService actressService;

    @PostMapping("/create")
    public ResponseEntity<Actress> saveActress(@RequestBody Actress actress) throws ConflictException, Exception {
        if (actress != null) {
            Actress actressSave = actressService.save(actress);
            URI uri = URI.create(String.format("/actress/create/%d",actressSave.getId()));
            return ResponseEntity.created(uri).body(actressSave);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Actress>> getListActress() throws Exception {
        List<Actress> actresses = actressService.getAll();
        if (!actresses.isEmpty()){
            return ResponseEntity.ok(actresses);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actress> getById(@PathVariable Long id) throws ActivationException, Exception {
        if (id != null){
            Actress actress = actressService.getById(id);
            return ResponseEntity.ok(actress);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<List<Actress>> getActressByStatus(@RequestParam("status") boolean actressStatus) throws Exception {
        List<Actress> actressesByStatus = actressService.getByStatus(actressStatus);
        if (!actressesByStatus.isEmpty()){
            return ResponseEntity.ok(actressesByStatus);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getMostRelevant")
    public ResponseEntity<Map<Integer, Set<String>>> getMostRelevantActresses() throws Exception {
        Map<Integer, Set<String>> actresses = actressService.getMostRelevant();
        if (!actresses.isEmpty()){
            return ResponseEntity.ok(actresses);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getLessRelevant")
    public ResponseEntity<Map<Integer, Set<String>>> getLessRelevantActresses() throws Exception {
        Map<Integer, Set<String>> actresses = actressService.getLessRelevant();
        if (!actresses.isEmpty()){
            return ResponseEntity.ok(actresses);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getMostExpensive")
    public ResponseEntity<Map<Double, Set<String>>> getMostExpensiveActresses() throws Exception {
        Map<Double, Set<String>> actresses = actressService.getMostExpensive();
        if (!actresses.isEmpty()){
            return ResponseEntity.ok(actresses);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getLessExpensive")
    public ResponseEntity<Map<Double, Set<String>>> getLessExpensiveActresses() throws Exception {
        Map<Double, Set<String>> actresses = actressService.getLessExpensive();
        if (!actresses.isEmpty()){
            return ResponseEntity.ok(actresses);
        }
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/{updateActressId}")
    public ResponseEntity<Actress> updateActress(@RequestBody Actress actress,
        @PathVariable Long updateActressId) throws ActivationException, Exception {
        if (actress != null && updateActressId != null){
            Actress actressUpdate = actressService.update(actress, updateActressId);
            return ResponseEntity.ok(actressUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Actress> deleteActress(@PathParam("id") Long id) throws
        EntityNotFoundException, Exception {
        if (id != null) {
            Actress actress = actressService.delete(id);
            return ResponseEntity.ok(actress);
        }
        return ResponseEntity.notFound().build();
    }

}