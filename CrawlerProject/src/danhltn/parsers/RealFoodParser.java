/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.parsers;

import danhltn.dtos.RealFood;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author leagu
 */
public class RealFoodParser {

    private static final String urlDefault = "https://realfood.tesco.com";

    public static List<RealFood> parserWithStax(String filePath, String staticCategory) throws Exception {
        List<RealFood> realFoodContainer = new ArrayList<>();

        boolean imgUrlTag = false;
        boolean nameTag = false;
        boolean timeTag = false;
        boolean servesTag = false;
        boolean typeTag = false;
        boolean descriptionTag = false;
        boolean ingredientTag = false;
        boolean stepTag = false;
        boolean kcalsTag = false;
        boolean tipTag = false;

        String txtImgUrl = "";
        String txtName = "";
        String txtTime = "";
        String txtServes = "";
        String txtType = "";
        String txtDescription = "";
        StringBuilder txtIngredientsList = new StringBuilder();
        StringBuilder txtStepsList = new StringBuilder();
        String txtKcals = "";
        String txtTip = "";

        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_COALESCING, true);
        XMLEventReader eventReader
                = factory.createXMLEventReader(new FileReader(filePath));
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    String tagName = startElement.getName().getLocalPart();

                    if (tagName.equalsIgnoreCase("imageUrl")) {
                        imgUrlTag = true;
                    } else if (tagName.equalsIgnoreCase("name")) {
                        nameTag = true;
                    } else if (tagName.equalsIgnoreCase("time")) {
                        timeTag = true;
                    } else if (tagName.equalsIgnoreCase("serves")) {
                        servesTag = true;
                    } else if (tagName.equalsIgnoreCase("type")) {
                        typeTag = true;
                    } else if (tagName.equalsIgnoreCase("description")) {
                        descriptionTag = true;
                    } else if (tagName.equalsIgnoreCase("ingredient")) {
                        ingredientTag = true;
                    } else if (tagName.equalsIgnoreCase("step")) {
                        stepTag = true;
                    } else if (tagName.equalsIgnoreCase("cals")) {
                        kcalsTag = true;
                    } else if (tagName.equalsIgnoreCase("tip")) {
                        tipTag = true;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    Characters character = event.asCharacters();
                    if (imgUrlTag) {
                        txtImgUrl = character.getData();
                        imgUrlTag = false;
                    }
                    if (nameTag) {
                        txtName = character.getData();
                        nameTag = false;
                    }
                    if (timeTag) {
                        txtTime = character.getData();
                        timeTag = false;
                    }
                    if (servesTag) {
                        txtServes = character.getData();
                        servesTag = false;
                    }
                    if (typeTag) {
                        txtType = character.getData();
                        typeTag = false;
                    }
                    if (descriptionTag) {
                        txtDescription = character.getData();
                        descriptionTag = false;
                    }
                    if (ingredientTag) {
                        txtIngredientsList.append(character.getData() + "&");
                        ingredientTag = false;
                    }
                    if (stepTag) {
                        txtStepsList.append(character.getData() + "&");
                        stepTag = false;
                    }
                    if (kcalsTag) {
                        txtKcals = character.getData();
                        kcalsTag = false;
                    }
                    if (tipTag) {
                        txtTip = character.getData();
                        tipTag = false;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("product")) {
                        List<String> categoryOfFood = new ArrayList<>();
                        RealFood food = new RealFood();
                        food.setImageUrl(urlDefault + txtImgUrl);
                        food.setName(txtName);
                        food.setTime(txtTime);
                        food.setServes(txtServes);
                        food.setDescription(txtDescription);
                        food.setIngredients(txtIngredientsList.toString());
                        String step = txtStepsList.toString().split("&")[0].trim();
                        if (step.equals("") || step.equals("See more") || step == null) {
                            food.setSteps(null);
                        } else {
                            food.setSteps(txtStepsList.toString().trim());
                        }
                        food.setKcals(Double.parseDouble(txtKcals.trim().split(" ")[0]));
                        categoryOfFood.add(staticCategory.toLowerCase());
                        if (txtTip.trim().equalsIgnoreCase("See more") || txtTip.equals(txtKcals)) {
                            food.setTip(null);
                        } else {
                            food.setTip(txtTip);
                        }
                        if (!txtType.equals(txtDescription)) {
                            categoryOfFood.add(txtType.toLowerCase());
                        }
                        food.setCategory(categoryOfFood);
                        if (txtIngredientsList.toString().indexOf(txtDescription) != -1 || food.getSteps() == null) {
                            food.setDescription(null);
                        } else {
                            food.setDescription(txtDescription);
                            realFoodContainer.add(food);
                        }
                        txtIngredientsList.delete(0, txtIngredientsList.length());
                        txtStepsList.delete(0, txtStepsList.length());
                    }
                    break;
            }
        }
        return realFoodContainer;
    }
}
