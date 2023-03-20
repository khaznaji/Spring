package com.example.intermove.Entities.User;

import com.example.intermove.Entities.CandidatesAndCourses.Candidacy;
import com.example.intermove.Entities.EventsAndComplaints.Claim;
import com.example.intermove.Entities.EventsAndComplaints.Events;
import com.example.intermove.Entities.Offer.Offer;
import com.example.intermove.Entities.SkillsAndQuizz.Response;
import com.example.intermove.Entities.forum.Comment;
import com.example.intermove.Entities.forum.Message;
import com.example.intermove.Entities.forum.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private int cin;
    private String firstname;
    private String lastname;
    private String email;

    public User(int userid) {
        this.userid = userid;
    }

    private String password;
    private String phone;
    private String address;
    private String profilepicture;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonIgnore
    public List<Claim> complaints ;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL )
    @JsonIgnore
   List<Candidacy> candidacies;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @ManyToMany
    public List<Events> events;
    @ManyToOne
    @JsonIgnore
    private Offer offers;

    public void setEvents(List<Events> events) {
        this.events = events;
    }
    public List<Events> getEvents() {
        return events;
    }

    //test

    //test
    private double score;
    @Enumerated(EnumType.STRING)
    private Badge badge;
    @OneToMany (mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Response> responseList;
    @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roles;
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;
    @OneToMany (mappedBy = "sender", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> sentMessages;
    @OneToMany (mappedBy = "receiver", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> receivedMessages;
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> Comments;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles!=null) {
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoletype().toString()));
            }
        }
        return authorities;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean getId() {return true;
    }
}
