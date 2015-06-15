package com.springapp.mvc.model.neo4j;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by Hasitha on 6/5/2015.
 * Customer details –
 * NIC/Passport, Name, Title, DoB, Profession, Gender, Address, VIP status, Loyalty Category, Primary Number
 */

@NodeEntity
public class Customer {

    @GraphId private Long id;
    @Indexed private String id_number;
    private String id_type;
    private String title;
    private String first_name;
    private String last_name;
    private String dob;
    private String profession;
    private String gender;
    private String status;
    private String loyalty;
    private String primary_number;
    private String account_numbers;
    private String address_line1;
    private String address_line2;
    private String address_line3;

    public Customer(){}

    public long getId() {
        return id;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }

    public String getPrimary_number() {
        return primary_number;
    }

    public void setPrimary_number(String primary_number) {
        this.primary_number = primary_number;
    }

    public String getAccount_numbers() {
        return account_numbers;
    }

    public void setAccount_numbers(String account_numbers) {
        this.account_numbers = account_numbers;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getAddress_line3() {
        return address_line3;
    }

    public void setAddress_line3(String address_line3) {
        this.address_line3 = address_line3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", id_number='" + id_number + '\'' +
                ", id_type='" + id_type + '\'' +
                ", title='" + title + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob='" + dob + '\'' +
                ", profession='" + profession + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", loyalty='" + loyalty + '\'' +
                ", primary_number='" + primary_number + '\'' +
                ", account_numbers='" + account_numbers + '\'' +
                ", address_line1='" + address_line1 + '\'' +
                ", address_line2='" + address_line2 + '\'' +
                ", address_line3='" + address_line3 + '\'' +
                '}';
    }
}