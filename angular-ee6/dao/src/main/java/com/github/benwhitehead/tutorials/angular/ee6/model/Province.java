package com.github.benwhitehead.tutorials.angular.ee6.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ben Whitehead
 */
@Entity
@Table(name="province")
@NamedQueries({
    @NamedQuery(name = "province.find.all", query = "select p from Province as p order by lower(p.name)")
})
@Getter
@Setter
@XmlRootElement
public class Province {

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "province_id_seq")
    @SequenceGenerator(name = "province_id_seq", sequenceName = "province_id_seq", allocationSize = 1)
    @NotNull
    @org.jetbrains.annotations.NotNull
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull
    @org.jetbrains.annotations.NotNull
    private String name;

    @Column(name = "abbreviation", nullable = false, unique = true)
    @NotNull
    @org.jetbrains.annotations.NotNull
    private String abbreviation;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Province)) {
            return false;
        }

        final Province province = (Province) o;

        if (abbreviation != null ? !abbreviation.equals(province.abbreviation) : province.abbreviation != null) {
            return false;
        }
        if (id != null ? !id.equals(province.id) : province.id != null) {
            return false;
        }
        if (name != null ? !name.equals(province.name) : province.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("State");
        sb.append("{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", abbreviation='").append(abbreviation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
