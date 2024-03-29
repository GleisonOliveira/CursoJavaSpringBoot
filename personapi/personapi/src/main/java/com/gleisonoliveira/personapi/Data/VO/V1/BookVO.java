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

@Relation(collectionRelation = "books")
@JsonPropertyOrder({"id", "name"})
@Schema(name = "Book")
public class BookVO extends RepresentationModel<BookVO> implements Serializable {
    @Hidden
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    @Schema(example = "Name")
    private String name;

    public BookVO() {
    }

    public String getName() {
        return name;
    }

    public BookVO setName(String name) {
        this.name = name;

        return this;
    }


    public long getId() {
        return id;
    }

    public BookVO setId(long id) {
        this.id = id;

        return this;
    }
}
