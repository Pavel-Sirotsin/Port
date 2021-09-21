package com.epam.port.repository.model.container;

public class Container {
    private int theNumber;
    private String company;
    private String type;
    private int height;

    public Container() {
    }

    public Container(int theNumber, String company, String type, int height) {
        this.theNumber = theNumber;
        this.company = company;
        this.type = type;
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxWeight() {
        return height;
    }

    public void setMaxWeight(int height) {
        this.height = height;
    }

    public int getTheNumber() {
        return theNumber;
    }

    public void setTheNumber(int theNumber) {
        this.theNumber = theNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Container container = (Container) o;

        if (theNumber != container.theNumber) return false;
        if (height != container.height) return false;
        if (company != null) {
            if (!company.equals(container.company)) return false;
        } else {
            if (container.company != null) return false;
        }
        if (type != null) return type.equals(container.type);
        return container.type == null;
    }

    @Override
    public int hashCode() {
        int result = theNumber;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "Container{" +
                "theNumber=" + theNumber +
                ", company='" + company + '\'' +
                ", type='" + type + '\'' +
                ", height=" + height +
                '}';
    }
}
