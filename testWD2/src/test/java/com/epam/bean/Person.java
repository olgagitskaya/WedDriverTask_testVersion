package com.epam.bean;


public class  Person {
    //USER1 ("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");

    private  String name;
    private  String surname;
    private  String city;
    private  String phone;
    private  String email;
    private  String countryCode;
    private  String countryPhonePrefix;

     public Person (String name, String surname, String city, String phone, String email, String countryCode, String countryPhonePrefix)
     {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.countryCode = countryCode;
        this.countryPhonePrefix = countryPhonePrefix;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getCity()
    {
        return city;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getEmail()
    {
        return email;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public String getCountryPhonePrefix()
    {
        return countryPhonePrefix;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCountryPhonePrefix(String countryPhonePrefix) {
        this.countryPhonePrefix = countryPhonePrefix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (surname != null ? !surname.equals(person.surname) : person.surname != null) return false;
        if (city != null ? !city.equals(person.city) : person.city != null) return false;
        if (phone != null ? !phone.equals(person.phone) : person.phone != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (countryCode != null ? !countryCode.equals(person.countryCode) : person.countryCode != null) return false;
        return countryPhonePrefix != null ? countryPhonePrefix.equals(person.countryPhonePrefix) : person.countryPhonePrefix == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (countryPhonePrefix != null ? countryPhonePrefix.hashCode() : 0);
        return result;
    }


}

