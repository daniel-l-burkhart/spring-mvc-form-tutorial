package com.daniel.form.model;

import java.util.List;

/**
 * Model class that contains our user object
 *
 * @author daniel
 * @version 1.0
 */
public class User {

    private Integer id;

    private String name;
    private String email;
    private String address;
    private String password;
    private String confirmPassword;
    private String sex;

    private Integer number;
    private String country;

    private boolean newsletter;
    private List<String> framework;

    private List<String> skill;

    /**
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the number
     */
    public Integer getNumber() {
        return this.number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the newsletter
     */
    public boolean isNewsletter() {
        return this.newsletter;
    }

    /**
     * @param newsletter the newsletter to set
     */
    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    /**
     * @return the framework
     */
    public List<String> getFramework() {
        return this.framework;
    }

    /**
     * @param framework the framework to set
     */
    public void setFramework(List<String> framework) {
        this.framework = framework;
    }

    /**
     * @return the skill
     */
    public List<String> getSkill() {
        return this.skill;
    }

    /**
     * @param skill the skill to set
     */
    public void setSkill(List<String> skill) {
        this.skill = skill;
    }

    /**
     * Tells us if this user is new or not
     *
     * @return
     */
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", password="
                + password + ", confirmPassword=" + confirmPassword + ", newsletter=" + newsletter + ", framework="
                + framework + ", sex=" + sex + ", number=" + number + ", country=" + country + ", skill=" + skill + "]"
                + isNew();
    }

}