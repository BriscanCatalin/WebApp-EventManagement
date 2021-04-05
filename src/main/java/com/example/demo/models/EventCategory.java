package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity{

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 4 and 50 characters.")
    private String name;

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> events = new ArrayList<>();

    public EventCategory() { }

    public EventCategory(@Size(min = 3, message = "Name must be between 4 and 50 characters.") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Event> getEvents() {
        return events;
    }
}
