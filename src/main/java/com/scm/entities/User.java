package com.scm.entities;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user")
@Table(name ="users")
public class User {

    @Id
    private String userId;
    @Column(name = "user_name" , nullable=false)
    private String name;
    @Column(unique=true , nullable=false)
    private String email;
    private String password;
    @Column(length=5000)
    private String about;
    @Column(length=5000)
    private String profilePic;
    
    private String phoneNumber;
    
    //Information
    private boolean  enabled = false;
    private boolean  emailVerified = false;
    private boolean  phoneVerified = false;

   
    private String providerUserId;
    @Enumerated
    private Providers provider = Providers.SELF;

    

    @OneToMany(mappedBy="users" , cascade=CascadeType.ALL , fetch=FetchType.LAZY, orphanRemoval=true)
    private List<Contact> contacts = new ArrayList<>();

   

}
