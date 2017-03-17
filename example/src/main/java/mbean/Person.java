package mbean;

/**
 * Created by lzz on 17/3/17.
 */
public class Person {
    private String name;
    private int age;
    private String gender;
    private int sendCount = 0;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void printName(){
        System.out.println(this.name);
    }
    @Override
    public String toString() {
        return this.name;
    }
    public void say(String message){
        System.out.println(name+"说:"+message);
    }
}