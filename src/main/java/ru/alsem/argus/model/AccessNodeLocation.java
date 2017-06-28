package ru.alsem.argus.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Created by SMertNIK on 27.06.2017.
 */
@Embeddable
@Access(AccessType.FIELD)
public class AccessNodeLocation {
    @NotNull
    private String region;
    @NotNull
    private String houseNumber;
    @NotNull
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
