package com.interview.takemehome;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.interview.takemehome.model.Animal;

public class AnimalModelTest {

    private LocalDate mockCurrentDate;

    @BeforeEach
    public void setUp() {
        // Set up a mock current date for consistent testing
        Date date = new Date();
        mockCurrentDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    @Test
    public void testConstructor() {
        Animal animal = new Animal("Test Name", "Test Description", "http://example.com/image.jpg",
                "Test Category", mockCurrentDate.minusYears(2), Animal.Status.DISPONIVEL);

        assertEquals("Test Name", animal.getName());
        assertEquals("Test Description", animal.getDescription());
        assertEquals("http://example.com/image.jpg", animal.getImageUrl());
        assertEquals("Test Category", animal.getCategory());
        assertEquals(mockCurrentDate.minusYears(2), animal.getBirthdate());
        assertEquals(Animal.Status.DISPONIVEL, animal.getStatus());
    }

    @Test
    public void testGetIdade() {
        Animal animal = new Animal("Test Name", "Test Description", "http://example.com/image.jpg",
                "Test Category", mockCurrentDate.minusYears(2), Animal.Status.DISPONIVEL);

        String idade = animal.getIdade();

        // Expecting the output to be "2 anos"
        assertEquals("2 anos", idade);
    }

    @Test
    public void testGetName() {
        // Arrange
        Animal animal = new Animal();
        animal.setName("Fluffy");

        // Act
        String name = animal.getName();

        // Assert
        assertEquals("Fluffy", name);
    }

    @Test
    void testGetNameWithMoreParams() {
        Animal animal = new Animal("Buddy", "A friendly dog", "https://example.com/dog.jpg", "Dog",
                LocalDate.of(2020, 1, 1), Animal.Status.DISPONIVEL);
        Assertions.assertEquals("Buddy", animal.getName());
    }

    @Test
    public void testSetName() {
        // Arrange
        Animal animal = new Animal();

        // Act
        animal.setName("Buddy");

        // Assert
        assertEquals("Buddy", animal.getName());
    }

    @Test
    void testSetters() {
        Animal animal = new Animal();
        animal.setName("Buddy");
        animal.setDescription("A friendly dog");
        animal.setImageUrl("https://example.com/dog.jpg");
        animal.setCategory("Dog");
        animal.setBirthdate(LocalDate.of(2020, 1, 1));
        animal.setStatus(Animal.Status.DISPONIVEL);

        Assertions.assertEquals("Buddy", animal.getName());
        Assertions.assertEquals("A friendly dog", animal.getDescription());
        Assertions.assertEquals("https://example.com/dog.jpg", animal.getImageUrl());
        Assertions.assertEquals("Dog", animal.getCategory());
        Assertions.assertEquals(LocalDate.of(2020, 1, 1), animal.getBirthdate());
        Assertions.assertEquals(Animal.Status.DISPONIVEL, animal.getStatus());
    }

    @Test
    void testGetName_InvalidName() {
        // Arrange: Create an animal with an incorrect name
        Animal animal = new Animal("IncorrectName", "A friendly dog", "https://example.com/dog.jpg",
                "Dog", LocalDate.of(2020, 1, 1), Animal.Status.DISPONIVEL);

        // Act and Assert: Expect an exception when retrieving the name
        Assertions.assertThrows(AssertionError.class, () -> {
            Assertions.assertEquals("Buddy", animal.getName());
        });
    }

    @Test
    void testGetDescription() {
        Animal animal = new Animal("Buddy", "A friendly dog", "https://example.com/dog.jpg", "Dog",
                LocalDate.of(2020, 1, 1), Animal.Status.DISPONIVEL);
        Assertions.assertEquals("A friendly dog", animal.getDescription());
    }

    @Test
    void testGetImageUrl() {
        Animal animal = new Animal("Buddy", "A friendly dog", "https://example.com/dog.jpg", "Dog",
                LocalDate.of(2020, 1, 1), Animal.Status.DISPONIVEL);
        Assertions.assertEquals("https://example.com/dog.jpg", animal.getImageUrl());
    }

    @Test
    void testGetCategory() {
        Animal animal = new Animal("Buddy", "A friendly dog", "https://example.com/dog.jpg", "Dog",
                LocalDate.of(2020, 1, 1), Animal.Status.DISPONIVEL);
        Assertions.assertEquals("Dog", animal.getCategory());
    }

    @Test
    void testGetBirthdate() {
        LocalDate birthdate = LocalDate.of(2020, 1, 1);
        Animal animal = new Animal("Buddy", "A friendly dog", "https://example.com/dog.jpg", "Dog",
                birthdate, Animal.Status.DISPONIVEL);
        Assertions.assertEquals(birthdate, animal.getBirthdate());
    }

    @Test
    void testGetStatus() {
        Animal animal = new Animal("Buddy", "A friendly dog", "https://example.com/dog.jpg", "Dog",
                LocalDate.of(2020, 1, 1), Animal.Status.DISPONIVEL);
        Assertions.assertEquals(Animal.Status.DISPONIVEL, animal.getStatus());
    }

}
