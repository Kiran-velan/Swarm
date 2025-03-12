package net.enjoy.springboot.registrationlogin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "discussions")
@Getter
@Setter
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discussionId;

    @Column(unique = true, nullable = false)
    private String uniqueCode;

    private String discussionName;
    private int numberOfParticipants;
    private int numberOfQuestions;
    private String description;

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public Discussion() {
        this.uniqueCode = generateUniqueCode();
    }

    public String generateUniqueCode() {
        return UUID.randomUUID().toString().replaceAll("[^A-Za-z0-9]", "").substring(0, 6);
    }
}