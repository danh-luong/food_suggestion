/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.dtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leagu
 */
@XmlRootElement
public class FoodDTO implements Serializable {

    private String imageUrl, name, time, serves, level, description, ingredients, steps, tip;
    private int cals;
    private int foodId;

    public FoodDTO() {
    }

    public FoodDTO(String imageUrl, String name, String time, String serves, String level, String description, String ingredients, String steps, int cals, int foodId, String tip) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.time = time;
        this.serves = serves;
        this.level = level;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
        this.cals = cals;
        this.foodId = foodId;
        this.tip = tip;
    }

    @XmlElement
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @XmlElement
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @XmlElement
    public String getServes() {
        return serves;
    }

    public void setServes(String serves) {
        this.serves = serves;
    }

    @XmlElement
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @XmlElement
    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    @XmlElement
    public double getCals() {
        return cals;
    }

    public void setCals(int cals) {
        this.cals = cals;
    }

    @XmlElement
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
