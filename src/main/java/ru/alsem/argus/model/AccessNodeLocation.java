package ru.alsem.argus.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Физическое расположение узла. Встраиваемый класс.
 */
@Embeddable
public class AccessNodeLocation {
    @NotNull
    @Column(name = "region", nullable = false)
    private String region;
    @NotNull
    @Column(name = "houseNumber", nullable = false)
    private String houseNumber;
    @NotNull
    @Column(name = "street", nullable = false)
    private String street;

    public AccessNodeLocation() {
    }

    public AccessNodeLocation(String region, String street, String houseNumber) {
        this.region = region;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
