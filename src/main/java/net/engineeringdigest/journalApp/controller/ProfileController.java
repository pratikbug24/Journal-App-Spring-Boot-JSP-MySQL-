package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ProfileController {

    @Autowired
    private UserService userService;

    // GET /api/user/profile - Get user profile
    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Not authenticated"));
        }

        // Get fresh user from database
        User freshUser = userService.findById(user.getId());

        Map<String, Object> profile = new HashMap<>();
        profile.put("id", freshUser.getId());
        profile.put("username", freshUser.getUsername());
        profile.put("fullName", freshUser.getFullName());
        profile.put("email", freshUser.getEmail());
        profile.put("bio", freshUser.getBio());
        profile.put("darkTheme", freshUser.isDarkTheme());
        profile.put("language", freshUser.getLanguage());

        return ResponseEntity.ok(profile);
    }

    // PUT /api/user/profile - Update user profile
    @PutMapping("/profile")
    public ResponseEntity<Map<String, Object>> updateProfile(
            @RequestBody Map<String, String> profileData,
            HttpSession session) {

        User user = (User) session.getAttribute("user");
        Map<String, Object> response = new HashMap<>();

        if (user == null) {
            response.put("success", false);
            response.put("message", "Not authenticated");
            return ResponseEntity.status(401).body(response);
        }

        try {
            user.setFullName(profileData.get("fullName"));
            user.setEmail(profileData.get("email"));
            user.setBio(profileData.get("bio"));

            userService.updateUser(user);

            response.put("success", true);
            response.put("message", "Profile updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to update profile: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // PUT /api/user/password - Change password
    @PutMapping("/password")
    public ResponseEntity<Map<String, Object>> changePassword(
            @RequestBody Map<String, String> passwordData,
            HttpSession session) {

        User user = (User) session.getAttribute("user");
        Map<String, Object> response = new HashMap<>();

        if (user == null) {
            response.put("success", false);
            response.put("message", "Not authenticated");
            return ResponseEntity.status(401).body(response);
        }

        String currentPassword = passwordData.get("currentPassword");
        String newPassword = passwordData.get("newPassword");

        // Verify current password
        User freshUser = userService.findById(user.getId());
        if (!freshUser.getPassword().equals(currentPassword)) {
            response.put("success", false);
            response.put("message", "Current password is incorrect");
            return ResponseEntity.status(400).body(response);
        }

        try {
            freshUser.setPassword(newPassword);
            userService.updateUser(freshUser);

            response.put("success", true);
            response.put("message", "Password updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to update password");
            return ResponseEntity.status(500).body(response);
        }
    }

    // PUT /api/user/preferences - Update preferences
    @PutMapping("/preferences")
    public ResponseEntity<Map<String, Object>> updatePreferences(
            @RequestBody Map<String, Object> prefs,
            HttpSession session) {

        User user = (User) session.getAttribute("user");
        Map<String, Object> response = new HashMap<>();

        if (user == null) {
            response.put("success", false);
            response.put("message", "Not authenticated");
            return ResponseEntity.status(401).body(response);
        }

        try {
            User freshUser = userService.findById(user.getId());

            if (prefs.containsKey("darkTheme")) {
                freshUser.setDarkTheme((Boolean) prefs.get("darkTheme"));
            }
            if (prefs.containsKey("language")) {
                freshUser.setLanguage((String) prefs.get("language"));
            }

            userService.updateUser(freshUser);

            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to update preferences");
            return ResponseEntity.status(500).body(response);
        }
    }

    // DELETE /api/user - Delete account
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteAccount(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Map<String, Object> response = new HashMap<>();

        if (user == null) {
            response.put("success", false);
            response.put("message", "Not authenticated");
            return ResponseEntity.status(401).body(response);
        }

        try {
            userService.deleteUser(user.getId());
            session.invalidate();

            response.put("success", true);
            response.put("message", "Account deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to delete account");
            return ResponseEntity.status(500).body(response);
        }
    }
}