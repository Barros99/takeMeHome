package com.interview.takemehome.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.interview.takemehome.exception.ResourceNotFoundException;
import com.interview.takemehome.model.Animal;
import com.interview.takemehome.model.Animal.Status;
import com.interview.takemehome.repository.AnimalRepository;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    private static final String DIRETORIO_UPLOAD = "src/main/resources/assets/uploads/";

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAllAnimals() {
        try {
            return animalRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve animals", e);
        }
    }

    public Animal getAnimalById(UUID id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        return optionalAnimal
                .orElseThrow(() -> new RuntimeException("Animal not found with id: " + id));
    }

    public Animal saveAnimal(Animal animal) {
        Animal animalInstance =
                new Animal(animal.getName(), animal.getDescription(), animal.getImageUrl(),
                        animal.getCategory(), animal.getBirthdate(), animal.getStatus());
        validateAnimal(animalInstance);
        if (animalInstance.getId() == null) {
            throw new IllegalArgumentException("Animal ID cannot be null.");
        }
        return animalRepository.save(animalInstance);
    }

    public Animal trocarStatus(UUID id, Status status) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));
        animal.setStatus(status);
        return animalRepository.save(animal);
    }


    private void validateAnimal(Animal animal) {
        List.of(check(animal.getName(), "Animal name must not be blank.",
                s -> s == null || s.isBlank()),
                check(animal.getDescription(), "Animal description must not be blank.",
                        s -> s == null || s.isBlank()),
                check(animal.getImageUrl(), "Animal image URL must be a valid URL.",
                        url -> url == null || !isValidURL(url)),
                check(animal.getBirthdate(), "Animal birth date must be a valid past date.",
                        date -> date == null || date.isAfter(LocalDate.now())),
                check(animal.getCategory(), "Animal category must not be blank.",
                        s -> s == null || s.isBlank()),
                check(animal.getStatus(), "Animal status must be either DISPONIVEL or ADOTADO.",
                        s -> s == null
                                || (s != Animal.Status.DISPONIVEL && s != Animal.Status.ADOTADO)))

                .forEach(ValidationResult::validate);
    }

    private <T> ValidationResult check(T value, String message, Predicate<T> predicate) {
        return new ValidationResult(predicate.test(value), message);
    }

    private boolean isValidURL(String url) {
        String regex = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }

    private static class ValidationResult {
        private final boolean invalid;
        private final String message;

        public ValidationResult(boolean invalid, String message) {
            this.invalid = invalid;
            this.message = message;
        }

        public void validate() {
            if (invalid) {
                throw new IllegalArgumentException(message);
            }
        }
    }

    public String uploadAnimal(MultipartFile foto, String name, String birthdate,
            String description, Status status, String category) throws IOException {
        if (foto.isEmpty()) {
            throw new IllegalArgumentException("Selecione uma foto para upload.");
        }

        String nomeFoto = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
        Path caminhoArquivo = Paths.get(DIRETORIO_UPLOAD, nomeFoto);

        Files.createDirectories(caminhoArquivo.getParent());
        Files.write(caminhoArquivo, foto.getBytes());

        Animal animal = new Animal(name, description, nomeFoto, category,
                LocalDate.parse(birthdate), status);
        animalRepository.save(animal);

        return String.format("Animal \"%s\" criado com sucesso com a foto: %s", name, nomeFoto);
    }

    public Resource loadFotoAsResource(String filename) throws IOException {
        Path caminhoArquivo = Paths.get(DIRETORIO_UPLOAD).resolve(filename);
        Resource resource = new UrlResource(caminhoArquivo.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException("Não foi possível ler o arquivo: " + filename);
        }
    }
}
