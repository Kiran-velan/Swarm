package net.enjoy.springboot.registrationlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        return "home";  // This loads home.html as the default page
    }

    @GetMapping("/organizer")
    public String showOrganizerPage() {
        return "index";  // Organizer button takes user to index.html (Login page)
    }

    @GetMapping("/user")
    public String showUserPage() {
        return "user";  // User button takes user to user.html (Discussion Name & Unique Code)
    }

    @GetMapping("/discussion")
    public String showDiscussionPage() {
        return "discussion";  // ✅ This serves discussion.html
    }
}
