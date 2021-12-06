package app.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;
    private String role;

    @Column(name = "is_active")
    private boolean isActive;
}
