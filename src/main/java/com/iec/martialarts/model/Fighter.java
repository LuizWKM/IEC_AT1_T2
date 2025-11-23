package com.iec.martialarts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "fighters")
public class Fighter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "A nacionalidade não pode estar vazia")
    @Column(nullable = false)
    private String nationality;

    @NotNull(message = "A idade não pode ser nula")
    @Min(value = 18, message = "O lutador deve ter pelo menos 18 anos")
    @Column(nullable = false)
    private Integer age;

    @NotBlank(message = "O estilo de luta não pode estar vazio")
    @Column(name = "martial_art_style", nullable = false)
    private String martialArtStyle;

    @Column(name = "belt_rank")
    private String beltRank;

    @Min(value = 0, message = "O número de vitórias não pode ser negativo")
    @Column(nullable = false)
    private Integer wins = 0;

    @Min(value = 0, message = "O número de derrotas não pode ser negativo")
    @Column(nullable = false)
    private Integer losses = 0;

    @Column(name = "weight_class")
    private String weightClass;

    // Constructors
    public Fighter() {
    }

    public Fighter(Long id, String name, String nationality, Integer age, String martialArtStyle, 
                   String beltRank, Integer wins, Integer losses, String weightClass) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.martialArtStyle = martialArtStyle;
        this.beltRank = beltRank;
        this.wins = wins;
        this.losses = losses;
        this.weightClass = weightClass;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMartialArtStyle() {
        return martialArtStyle;
    }

    public void setMartialArtStyle(String martialArtStyle) {
        this.martialArtStyle = martialArtStyle;
    }

    public String getBeltRank() {
        return beltRank;
    }

    public void setBeltRank(String beltRank) {
        this.beltRank = beltRank;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public String getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(String weightClass) {
        this.weightClass = weightClass;
    }

    @PrePersist
    protected void onCreate() {
        if (wins == null) wins = 0;
        if (losses == null) losses = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fighter fighter = (Fighter) o;
        return Objects.equals(id, fighter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Fighter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                ", martialArtStyle='" + martialArtStyle + '\'' +
                ", beltRank='" + beltRank + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                ", weightClass='" + weightClass + '\'' +
                '}';
    }
}
