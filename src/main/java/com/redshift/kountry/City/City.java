package com.redshift.kountry.City;

import com.redshift.kountry.State.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE)
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(nullable = false, length = 50)
    @Schema(example="Tunis",pattern = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$" , maxLength = 50, minLength = 2 ,required = true)
    @Length(min = 2, max = 50, message = "City name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Country name must be alphabetic")
    String name;


    @PositiveOrZero(message = "Population must be positive")
    Long population;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cities")
    private State state;

    @OneToOne(mappedBy = "capitalCity", cascade = CascadeType.ALL, orphanRemoval = true)
    private State capitalOf;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        City city = (City) o;
        return getId() != null && Objects.equals(getId(), city.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
