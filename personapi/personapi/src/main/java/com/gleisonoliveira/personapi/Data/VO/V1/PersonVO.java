package com.gleisonoliveira.personapi.Data.VO.V1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Relation(collectionRelation = "persons")
@JsonPropertyOrder({"id", "firstName", "lastName", "gender", "address"})
@Schema(name = "Person")
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
    @Hidden
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    @Schema(example = "Name")
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    @Schema(example = "Last name")
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 250)
    @Schema(example = "Street A")
    private String address;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 1)
    @Schema(example = "M|F")
    private String gender;

    public PersonVO() {
    }

    public long getId() {
        return id;
    }

    public PersonVO setId(long id) {
        this.id = id;

        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonVO other = (PersonVO) obj;
        if (id != other.id)
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonVO setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonVO setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String getAddress() {
        return address;
    }

    public PersonVO setAddress(String address) {
        this.address = address;

        return this;
    }

    public String getGender() {
        return gender;
    }

    public PersonVO setGender(String gender) {
        this.gender = gender;

        return this;
    }
}
