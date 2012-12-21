package com.github.benwhitehead.tutorials.angular.ee6.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Ben Whitehead
 */
@Entity
@Table(name = "country")
@NamedQueries({
    @NamedQuery(name = "country.find.all", query = "select c from Country as c order by lower(c.name)")
})
@Getter
@Setter
@XmlRootElement
public class Country {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "country_id_seq")
    @SequenceGenerator(name = "country_id_seq", sequenceName = "country_id_seq", allocationSize = 1)
    @NotNull
    @org.jetbrains.annotations.NotNull
    private Long id;

    @Column(name = "two_digit_code", unique = true, nullable = false)
    @NotNull
    @org.jetbrains.annotations.NotNull
    private String twoDigitCountryCode;

    @Column(name = "name", unique = true, nullable = false)
    @NotNull
    @org.jetbrains.annotations.NotNull
    private String name;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Country");
        sb.append("{id=").append(id);
        sb.append(", twoDigitCountryCode='").append(twoDigitCountryCode).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
