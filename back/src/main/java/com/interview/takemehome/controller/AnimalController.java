package com.interview.takemehome.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.interview.takemehome.model.Animal;
import com.interview.takemehome.model.Animal.Status;
import com.interview.takemehome.service.AnimalService;


@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable UUID id) {
        Animal animal = animalService.getAnimalById(id);
        return animal != null ? ResponseEntity.ok(animal) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        Animal createdAnimal = animalService.saveAnimal(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
    }

    @PutMapping("/{id}/")
    public Animal trocarStatus(@PathVariable UUID id, @RequestParam Status status) {
        return animalService.trocarStatus(id, status);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAnimal(@RequestParam("foto") MultipartFile foto,
            @RequestParam("name") String name, @RequestParam("birthdate") String birthdate,
            @RequestParam("description") String description, @RequestParam("status") Status status,
            @RequestParam("category") String category) throws IOException {
        try {
            String mensagemSucesso = animalService.uploadAnimal(foto, name, birthdate, description,
                    status, category);
            return ResponseEntity.status(HttpStatus.OK).body(mensagemSucesso);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao salvar a foto.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/foto/{filename:.+}")
    public ResponseEntity<Resource> serveFoto(@PathVariable String filename) {
        try {
            Path file = Paths.get("src/main/resources/assets/uploads/").resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            } else {
                throw new IOException("Não foi possível ler o arquivo: " + filename);
            }
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
