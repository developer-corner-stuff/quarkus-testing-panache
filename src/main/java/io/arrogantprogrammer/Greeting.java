package io.arrogantprogrammer;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Greeting extends PanacheEntity {

    String value;

    public Greeting(String value) {
        this.value = value;
    }

    public Greeting() {
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting = (Greeting) o;
        return Objects.equals(value, greeting.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
