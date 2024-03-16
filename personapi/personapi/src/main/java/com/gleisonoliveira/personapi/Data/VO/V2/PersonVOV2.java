package com.gleisonoliveira.personapi.Data.VO.V2;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Relation(collectionRelation = "persons")
@Schema(name = "Person (V2)")
public class PersonVOV2 extends RepresentationModel<PersonVOV2> implements Serializable {
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

    @NotNull
    @NotBlank
    @Schema(example = "2000-03-25")
    private Date birthDay;

    public PersonVOV2() {
    }

    public long getId() {
        return id;
    }

    public PersonVOV2 setId(long id) {
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
        result = prime * result + ((birthDay == null) ? 0 : birthDay.hashCode());
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
        PersonVOV2 other = (PersonVOV2) obj;
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
        if (birthDay == null) {
            if (other.birthDay != null)
                return false;
        } else if (!birthDay.equals(other.birthDay))
            return false;
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonVOV2 setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonVOV2 setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String getAddress() {
        return address;
    }

    public PersonVOV2 setAddress(String address) {
        this.address = address;

        return this;
    }

    public String getGender() {
        return gender;
    }

    public PersonVOV2 setGender(String gender) {
        this.gender = gender;

        return this;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public PersonVOV2 setBirthDay(Date birthDay) {
        this.birthDay = birthDay;

        return this;
    }
}
