package com.connect.circle.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "ext_id", length = 36, unique = true, nullable = false, updatable = false)
    private String extId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "profile_pic", length = 255)
    private String profilePic;

    @Column(name = "country", length = 2)
    private String country;

    @Column(name = "date_email_verified")
    private LocalDateTime dateEmailVerified;

    @Column(name = "date_joined", nullable = false)
    private LocalDateTime dateJoined;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    // Default constructor
    public UserModel() {
        // Fields initialization is now handled by @PrePersist
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getExtId() {
        return extId;
    }

    // Removed setExtId to make it immutable

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Set password - Note: Password should be hashed before calling this method.
     * Use a password encoder like BCryptPasswordEncoder from Spring Security.
     * 
     * @param password The hashed password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getDateEmailVerified() {
        return dateEmailVerified;
    }

    public void setDateEmailVerified(LocalDateTime dateEmailVerified) {
        this.dateEmailVerified = dateEmailVerified;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
        
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.dateModified = LocalDateTime.now();
    }
    
    @PrePersist
    protected void onCreate() {
        if (this.extId == null) {
            this.extId = UUID.randomUUID().toString();
        }
        if (this.dateJoined == null) {
            this.dateJoined = LocalDateTime.now();
        }
        if (this.dateModified == null) {
            this.dateModified = LocalDateTime.now();
        }
    }
}
