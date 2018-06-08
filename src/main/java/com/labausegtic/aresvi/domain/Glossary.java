package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Glossary.
 */
@Entity
@Table(name = "glossary")
public class Glossary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "concept", nullable = false)
    private String concept;

    @NotNull
    @Lob
    @Column(name = "definition", nullable = false)
    private String definition;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public Glossary concept(String concept) {
        this.concept = concept;
        return this;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getDefinition() {
        return definition;
    }

    public Glossary definition(String definition) {
        this.definition = definition;
        return this;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Glossary glossary = (Glossary) o;
        if (glossary.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), glossary.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Glossary{" +
            "id=" + getId() +
            ", concept='" + getConcept() + "'" +
            ", definition='" + getDefinition() + "'" +
            "}";
    }
}
