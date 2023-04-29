package com.redshift.kountry.Country;

import com.redshift.kountry.State.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Pattern;
import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;


    @Column(unique = true, nullable = false, length = 25)
    @Schema(example="Tunisia")
    @Length(min = 2, max = 25, message = "Country name must be between 2 and 25 characters")
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Country name must be alphabetic")
    String name;

    @Column(nullable = false)
    @URL(message = "Please enter a valid URL")
    String flag;

    @ToString.Exclude
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<State> states = new LinkedHashSet<>();

}
