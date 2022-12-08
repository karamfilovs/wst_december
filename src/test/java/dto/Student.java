package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class Student {
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getActive() {
        return isActive;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
    private Boolean isActive;
    private List<String> subjects;

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Student ivan = new Student();
        ivan.setFirstName("Ivan");
        ivan.setLastName("Ivanov");
        ivan.setAge(20);
        System.out.println(gson.toJson(ivan));

    }
}
