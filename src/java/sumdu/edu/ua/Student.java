
package sumdu.edu.ua;

/**
 * Describe Student 
 * @author HP
 */

public class Student {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String group;
    private String faculty;

    public Student(String name, String surname, int age, String email, String group, String faculty) {
        if(name == null){
            throw new IllegalArgumentException("Name can't be null");
        }
        if(surname == null){
            throw new IllegalArgumentException("Surname can't be null");
        }
        if(age < 15 || age > 50){
            throw new IllegalArgumentException("Age out of range [0;150]");
        }
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.group = group;
        this.faculty = faculty;
    }
    

    /**
     * @return the firstname
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the faculty
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * @param faculty the faculty to set
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    
    
}
