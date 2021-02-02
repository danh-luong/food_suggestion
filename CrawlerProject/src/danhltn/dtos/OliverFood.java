/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author leagu
 */
public class OliverFood implements Serializable {

    private String imageUrl, name, time, serves, level, description, ingredients, steps;
    private double kcals;
    private List<String> category;

    public OliverFood() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getServes() {
        return serves;
    }

    public void setServes(String serves) {
        this.serves = serves;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public double getKcals() {
        return kcals;
    }

    public void setKcals(double kcals) {
        this.kcals = kcals;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public void printDetail() {
        System.out.println(name + "-" + time + "-" + description + "-" + imageUrl
                + "-" + kcals + "-" + steps + "-" + ingredients + "-" + serves + "-" + level);
    }

}
