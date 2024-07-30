package com.interview.takemehome;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import com.interview.takemehome.exception.ResourceNotFoundException;
import com.interview.takemehome.model.Animal;
import com.interview.takemehome.model.Animal.Status;
import com.interview.takemehome.repository.AnimalRepository;
import com.interview.takemehome.service.AnimalService;


@SpringBootTest
@AutoConfigureMockMvc
public class AnimalServiceTest {

        @Mock
        private AnimalRepository animalRepository;

        @InjectMocks
        private AnimalService animalService;


        @Test
        public void testGetAllAnimals_ShouldReturnAllAnimals() {
                // Arrange
                Animal animal1 = new Animal("Rex", "Um cachorro amigável e brincalhão.",
                                "https://example.com/rex.jpg", "cachorro",
                                LocalDate.of(2018, 5, 20), Animal.Status.DISPONIVEL);

                Animal animal2 = new Animal("Mimi", "Uma gata calma e carinhosa.",
                                "https://example.com/mimi.jpg", "gato", LocalDate.of(2020, 8, 15),
                                Animal.Status.ADOTADO);

                List<Animal> animals = Arrays.asList(animal1, animal2);
                AnimalRepository mockRepository = mock(AnimalRepository.class);

                when(mockRepository.findAll()).thenReturn(animals);
                AnimalService animalService = new AnimalService(mockRepository);

                // Act
                List<Animal> result = animalService.getAllAnimals();

                // Assert
                assertNotNull(result);
                assertEquals("Rex", result.get(0).getName());
                assertEquals("Mimi", result.get(1).getName());
                assertEquals(2, result.size());
        }

        @Test
        public void testGetAnimalById_ShouldReturnAnimalWhenFound() {
                // Arrange
                Animal expectedAnimal = new Animal("Rex", "Um cachorro amigável e brincalhão.",
                                "https://example.com/rex.jpg", "cachorro",
                                LocalDate.of(2018, 5, 20), Animal.Status.DISPONIVEL);
                UUID id = expectedAnimal.getId();
                AnimalRepository mockRepository = mock(AnimalRepository.class);
                when(mockRepository.findById(id)).thenReturn(Optional.of(expectedAnimal));
                AnimalService animalService = new AnimalService(mockRepository);

                // Act
                Animal result = animalService.getAnimalById(id);

                // Assert
                assertNotNull(result);
                assertEquals(expectedAnimal, result);
        }

        @Test
        public void testSaveAnimal_ShouldSaveAnimal() {
                // Arrange
                Animal animal = new Animal("Rex", "Um cachorro amigável e brincalhão.",
                                "https://example.com/rex.jpg", "cachorro",
                                LocalDate.of(2018, 5, 20), Animal.Status.DISPONIVEL);

                AnimalRepository mockRepository = mock(AnimalRepository.class);
                when(mockRepository.save(any(Animal.class))).thenReturn(animal);
                AnimalService animalService = new AnimalService(mockRepository);

                // Act
                Animal result = animalService.saveAnimal(animal);

                // Assert
                assertNotNull(result);
                assertEquals("Rex", result.getName());
        }

        @Test
        public void testSaveAnimal_ShouldThrowExceptionForEmptyName() {
                // Arrange
                Animal animal = new Animal("", "...", "...", "...", LocalDate.now(),
                                Animal.Status.DISPONIVEL);

                AnimalRepository mockRepository = mock(AnimalRepository.class);
                when(mockRepository.save(any(Animal.class))).thenReturn(animal);
                AnimalService animalService = new AnimalService(mockRepository);

                // Act & Assert
                String expectedErrorMessage = "Animal name must not be blank.";
                RuntimeException exception = assertThrows(IllegalArgumentException.class,
                                () -> animalService.saveAnimal(animal));
                assertEquals(expectedErrorMessage, exception.getMessage());
        }

        @Test
        public void testValidateAnimal() {
                Animal animal = new Animal("Rex", "Um cachorro amigável e brincalhão.",
                                "https://example.com/rex.jpg", "cachorro",
                                LocalDate.of(2018, 5, 20), Animal.Status.DISPONIVEL);

                assertDoesNotThrow(() -> animalService.saveAnimal(animal));
        }

        @Test
        public void testInvalidAnimal() {
                Animal animal = new Animal("", "Um cachorro amigável e brincalhão.",
                                "https://example.com/rex.jpg", "cachorro",
                                LocalDate.of(2018, 5, 20), Animal.Status.DISPONIVEL);

                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        animalService.saveAnimal(animal);
                });

                assertEquals("Animal name must not be blank.", exception.getMessage());
        }

        @Test
        public void testTrocarStatus_ShouldUpdateStatus() throws Exception {
                // Arrange
                Status newStatus = Status.ADOTADO;
                Animal expectedAnimal = new Animal("Rex", "...", "...", "...", LocalDate.now(),
                                Status.DISPONIVEL);

                AnimalRepository mockRepository = mock(AnimalRepository.class);

                when(mockRepository.findById(expectedAnimal.getId()))
                                .thenReturn(Optional.of(expectedAnimal));

                doAnswer(invocation -> {
                        Animal savedAnimal = invocation.getArgument(0);
                        savedAnimal.setStatus(newStatus);
                        return savedAnimal;
                }).when(mockRepository).save(any(Animal.class));

                AnimalService animalService = new AnimalService(mockRepository);

                // Act
                Animal updatedAnimal =
                                animalService.trocarStatus(expectedAnimal.getId(), newStatus);

                // Assert
                assertThat(updatedAnimal).isNotNull();
                assertThat(updatedAnimal.getId()).isEqualTo(expectedAnimal.getId());
                assertThat(updatedAnimal.getStatus()).isEqualTo(newStatus);
                verify(mockRepository).findById(expectedAnimal.getId());
                verify(mockRepository).save(expectedAnimal);
        }

        @Test
        public void testTrocarStatus_ShouldThrowNotFoundException_WhenAnimalNotFound()
                        throws Exception {
                // Arrange
                Status newStatus = Status.ADOTADO;
                AnimalRepository mockRepository = mock(AnimalRepository.class);

                when(mockRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
                AnimalService animalService = new AnimalService(mockRepository);

                // Act & Assert
                String expectedErrorMessage = "Animal not found";
                RuntimeException exception = assertThrows(ResourceNotFoundException.class,
                                () -> animalService.trocarStatus(UUID.randomUUID(), newStatus));
                assertEquals(expectedErrorMessage, exception.getMessage());
                verify(mockRepository).findById(any(UUID.class));
        }

        @Test
        public void testUploadAnimal() throws IOException {
                // Arrange
                String name = "Test Animal";
                String birthdate = "2024-07-29";
                String description = "A cute test animal";
                Status status = Status.ADOTADO;
                String category = "Test Category";

                MockMultipartFile foto = new MockMultipartFile("foto", "test.jpg", "image/jpeg",
                                "test data".getBytes());

                // Act
                String result = animalService.uploadAnimal(foto, name, birthdate, description,
                                status, category);

                // Assert
                assertNotNull(result);
                assertTrue(result.contains(name));
                assertTrue(result.contains("Animal \"Test Animal\" criado com sucesso"));
                assertTrue(result.contains("foto:"));
                assertTrue(result.contains("_test.jpg"));
        }

}
