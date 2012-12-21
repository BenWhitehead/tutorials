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
@Table(name = "address")
@NamedQueries({
        @NamedQuery(name = "address.find.all", query = "select a from Address a order by a.id")
})
@Getter
@Setter
@XmlRootElement
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "address_id_seq")
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "description", nullable = false)
    @NotNull
    private String description;

    @Column(name = "line_one", nullable = false)
    @NotNull
    private String lineOne;

    @Column(name = "line_two", nullable = true)
    private String lineTwo;

    @Column(name = "city", nullable = false)
    @NotNull
    private String city;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    @NotNull
    private Province province;

    @Column(name = "post_code", nullable = false)
    @NotNull
    private String postCode;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @NotNull
    private Country country;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        final Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) {
            return false;
        }
        if (country != null ? !country.equals(address.country) : address.country != null) {
            return false;
        }
        if (id != null ? !id.equals(address.id) : address.id != null) {
            return false;
        }
        if (lineOne != null ? !lineOne.equals(address.lineOne) : address.lineOne != null) {
            return false;
        }
        if (lineTwo != null ? !lineTwo.equals(address.lineTwo) : address.lineTwo != null) {
            return false;
        }
        if (postCode != null ? !postCode.equals(address.postCode) : address.postCode != null) {
            return false;
        }
        if (province != null ? !province.equals(address.province) : address.province != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lineOne != null ? lineOne.hashCode() : 0);
        result = 31 * result + (lineTwo != null ? lineTwo.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (postCode != null ? postCode.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Address");
        sb.append("{id=").append(id);
        sb.append(", lineOne='").append(lineOne).append('\'');
        sb.append(", lineTwo='").append(lineTwo).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country=").append(country);
        sb.append(", postCode='").append(postCode).append('\'');
        sb.append(", state=").append(province);
        sb.append('}');
        return sb.toString();
    }
}
