package net.virgodirk.wildfire.util;

/**
 * 人员信息-测试用
 *
 * @author 李晓勇 on 2017年8月26日 上午8:45:06
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class Person {
    
    private String id;
    private String name;
    private String gender;
    private String birthDate;

    
    public Person() {
        id = "";
        name = "";
        gender = "";
        birthDate = "";
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
}
