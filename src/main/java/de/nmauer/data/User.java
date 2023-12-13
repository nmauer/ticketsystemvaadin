package de.nmauer.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.server.StreamResource;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import javax.accessibility.AccessibleValue;
import java.io.ByteArrayInputStream;
import java.util.Set;

@Entity
@Table(name = "application_user")
public class User extends AbstractEntity {

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "hashed_password", nullable = false)
    @JsonIgnore
    private String hashedPassword;
    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @Lob
    @Column(length = 1000000)
    private byte[] profilePicture;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public byte[] getProfilePicture() {
        return profilePicture;
    }
    public Avatar getProfilePictureAsAvatar() {
        Avatar avatar = new Avatar(name);
        StreamResource resource = new StreamResource("profile-pic",
                () -> new ByteArrayInputStream(profilePicture));
        avatar.setImageResource(resource);
        avatar.setThemeName("xsmall");
        avatar.getElement().setAttribute("tabindex", "-1");
        return avatar;
    }
    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

}
