package com.interview.takemehome.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

/**
 * The type Animal.
 */
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    private UUID id;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate birthdate;

    @NotBlank
    @Size(min = 2, max = 255)
    @Column(nullable = false)
    private String description;

    @NotBlank
    @Column(nullable = false)
    private String imageUrl;

    @NotBlank
    @Column(nullable = false)
    private String category;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Instantiates a new Animal.
     */
    public Animal() {
    }

    /**
     * Constructor to create an instance of Animal.
     *
     * @param name        the name of the animal
     * @param description the description of the animal
     * @param imageUrl    the URL of the animal's image
     * @param category    the category of the animal (e.g., dog, cat)
     * @param birthdate   the birthdate of the animal
     * @param status      the status of the animal (AVAILABLE, ADOPTED)
     */
    public Animal(String name, String description, String imageUrl, String category,
            LocalDate birthdate, Status status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.birthdate = birthdate;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getIdade() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(this.birthdate, currentDate);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        StringBuilder sb = new StringBuilder();
        if (years > 0) {
            sb.append(years).append(" ano");
            if (years > 1) {
                sb.append("s");
            }
        }
        if (months > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            if (months == 1) { 
                sb.append(months).append(" mês");
            } else {
                sb.append(months).append(" meses");
            }
        }
        if (days > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(days).append(" dia");
            if (days > 1) {
                sb.append("s");
            }
        }

        return sb.toString().trim();
    }

    public enum Status {
        DISPONIVEL, ADOTADO
    }
}
