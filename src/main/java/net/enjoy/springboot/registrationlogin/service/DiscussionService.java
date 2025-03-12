package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.entity.Discussion;
import net.enjoy.springboot.registrationlogin.entity.Question;
import net.enjoy.springboot.registrationlogin.repository.DiscussionRepository;
import net.enjoy.springboot.registrationlogin.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public DiscussionService(DiscussionRepository discussionRepository, QuestionRepository questionRepository) {
        this.discussionRepository = discussionRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public Discussion saveDiscussion(Discussion discussion) {
        // Ensure uniqueCode is generated (if not set already)
        if (discussion.getUniqueCode() == null || discussion.getUniqueCode().isEmpty()) {
            String uniqueCode = discussion.generateUniqueCode();
            while (discussionRepository.existsByUniqueCode(uniqueCode)) {
                uniqueCode = discussion.generateUniqueCode();
            }
            discussion.setUniqueCode(uniqueCode);
        }

        // Associate each question with the discussion before saving
        for (Question question : discussion.getQuestions()) {
            question.setDiscussionCode(discussion.getUniqueCode());
        }

        // Save the discussion along with questions (due to cascading)
        return discussionRepository.save(discussion);
    }

    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }
}