package com.sh.common.utils;

import com.sh.common.ddd.ValueObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address implements ValueObject {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String province;
    private String city;
    private String detail;

    private Address() {
    }

    private Address(String province, String city, String detail) {
        this.province = province;
        this.city = city;
        this.detail = detail;
    }

    public static Address of(String province, String city, String detail) {
        return new Address(province, city, detail);
    }

    public Address changeDetailTo(String detail) {
        return new Address(this.province, this.city, detail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return province.equals(address.province) &&
                city.equals(address.city) &&
                detail.equals(address.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(province, city, detail);
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDetail() {
        return detail;
    }
}
