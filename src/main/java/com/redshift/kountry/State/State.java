package com.redshift.kountry.State;

import com.redshift.kountry.City.City;
import com.redshift.kountry.Country.Country;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class State implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Schema(example="Tunis")
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Country name must be alphabetic")
    @Length(min = 2, max = 50, message = "State name must be between 2 and 50 characters")
    @Column(nullable = false, length = 50)
    String name;

    @PositiveOrZero(message = "Population must be positive")
    @Column(nullable = false)
    Long population;


    @ToString.Exclude
    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<City> cities = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "capital_Of", referencedColumnName = "id", unique = true)
    private City capitalCity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        State state = (State) o;
        return getId() != null && Objects.equals(getId(), state.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
