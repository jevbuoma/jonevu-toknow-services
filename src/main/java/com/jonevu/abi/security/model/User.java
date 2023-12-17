package com.jonevu.abi.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.jonevu.abi.security.validation.PasswordMatches;

@PasswordMatches
@Transactional
@Entity
@Table(name = "APP_USER")
@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQUENCE")
public class User {

    @Id
	@Column(name = "USER_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,  generator = "SeqGen")
	@SequenceGenerator(name = "SeqGen", sequenceName = "LOG_SERV_SEQUENCE", allocationSize = 1)
    private Long id;

    @Email
    @NotEmpty(message = "Email is required.")
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    @NotEmpty(message = "Password is required.")
    private String password;

    @Transient
    @NotEmpty(message = "Password confirmation is required.")
    private String passwordConfirmation;

    @Column(name = "ENABLED")
    private Boolean enabled;
    
    @Column(name = "CREATEDDT")
    private Timestamp created;

    public User() {
        super();
        this.enabled = false;
    }

    public User(final String email, final String password) {
        this.email = email;
        this.password = password;
        this.passwordConfirmation = password;
    }
    
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the created
	 */
	public Timestamp getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(final String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", passwordConfirmation="
				+ passwordConfirmation + ", enabled=" + enabled + ", created=" + created + "]";
	}
}
