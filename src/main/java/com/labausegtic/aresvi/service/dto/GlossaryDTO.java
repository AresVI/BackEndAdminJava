package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Glossary entity.
 */
public class GlossaryDTO implements Serializable {

    private Long id;

    @NotNull
    private String concept;

    @NotNull
    @Lob
    private String definition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GlossaryDTO glossaryDTO = (GlossaryDTO) o;
        if(glossaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), glossaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GlossaryDTO{" +
            "id=" + getId() +
            ", concept='" + getConcept() + "'" +
            ", definition='" + getDefinition() + "'" +
            "}";
    }
}
