package net.enjoy.springboot.registrationlogin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {

    @Column(name = "discussion_code")
    private String discussionCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText;

    // ✅ Use discussion_id instead of uniqueCode as FK
    @ManyToOne
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;

    public Question() {}

    public Question(String questionText, Discussion discussion) {
        this.questionText = questionText;
        this.discussion = discussion;
    }
}
