package com.abbtech.controller;

import com.abbtech.model.Model;
import com.abbtech.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels() {
        return ResponseEntity.ok(modelService.getAllModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Integer id) {
        return ResponseEntity.ok(modelService.getModelById(id));
    }

    @PostMapping
    public ResponseEntity<Model> createModel(@Valid @RequestBody Model model) {
        Model created = modelService.createModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable Integer id, @Valid @RequestBody Model model) {
        Model updated = modelService.updateModel(id, model);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Integer id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}
