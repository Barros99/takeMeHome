package com.interview.takemehome;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import com.interview.takemehome.controller.AnimalController;
import com.interview.takemehome.model.Animal;
import com.interview.takemehome.model.Animal.Status;
import com.interview.takemehome.repository.AnimalRepository;
import com.interview.takemehome.service.AnimalService;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTest {

    @InjectMocks
    private AnimalController animalController;

    @Mock
    private AnimalService animalService;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private MockMvc mockMvc;


    @Test
    public void testGetAllAnimals_ShouldReturnOkWithAnimalsList() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        when(animalService.getAllAnimals()).thenReturn(animals);

        ResponseEntity<List<Animal>> response = animalController.getAllAnimals();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(animals, response.getBody());
    }

    @Test
    public void testGetAllAnimals_ShouldReturnEmptyList() {
        ResponseEntity<List<Animal>> response = animalController.getAllAnimals();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        if (response.hasBody()) {
            List<Animal> animals = response.getBody();
            assertTrue(animals.isEmpty());
        } else {
            fail("Response body is null");
        }
    }

    @Test
    void testGetAnimalById_ShouldReturnOK() {
        Animal expectedAnimal = new Animal("Rex", "Friendly dog", "https://example.com/rex.jpg",
                "dog", LocalDate.of(2018, 5, 20), Animal.Status.DISPONIVEL);

        when(animalService.getAnimalById(expectedAnimal.getId())).thenReturn(expectedAnimal);

        ResponseEntity<Animal> response = animalController.getAnimalById(expectedAnimal.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAnimal, response.getBody());
    }


    @Test
    public void testGetAnimalById_ShouldReturnNotFound() {
        UUID id = UUID.randomUUID();
        when(animalService.getAnimalById(id)).thenReturn(null);

        ResponseEntity<Animal> response = animalController.getAnimalById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateAnimal_ShouldReturnCreatedWithAnimal() {
        Animal animal = new Animal();
        when(animalService.saveAnimal(animal)).thenReturn(animal);

        ResponseEntity<Animal> response = animalController.createAnimal(animal);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(animal, response.getBody());
    }

    @Test
    public void testTrocarStatus_ShouldUpdateStatus() {
        // Arrange
        Animal expectedAnimal = new Animal("Rex", "...", "...", "...", LocalDate.of(2018, 5, 20),
                Animal.Status.DISPONIVEL);
        Status newStatus = Status.ADOTADO;

        when(animalService.trocarStatus(expectedAnimal.getId(), newStatus))
                .thenAnswer(invocation -> {
                    expectedAnimal.setStatus(newStatus);
                    return expectedAnimal;
                });

        // Act
        Animal updatedAnimal = animalController.trocarStatus(expectedAnimal.getId(), newStatus);

        // Assert
        assertThat(updatedAnimal.getStatus()).isEqualTo(newStatus);
        verify(animalService).trocarStatus(expectedAnimal.getId(), newStatus);
    }

}
