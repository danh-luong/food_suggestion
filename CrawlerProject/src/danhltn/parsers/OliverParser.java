/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.parsers;

import danhltn.dtos.OliverFood;
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
public class OliverParser {

    public static List<OliverFood> parserWithStax(String filePath, List<String> category, String staticCategory) throws Exception {
        List<OliverFood> oliverFoodContainer = new ArrayList<>();

        boolean imgUrlTag = false;
        boolean nameTag = false;
        boolean timeTag = false;
        boolean servesTag = false;
        boolean levelTag = false;
        boolean descriptionTag = false;
        boolean ingredientTag = false;
        boolean stepTag = false;
        boolean kcalsTag = false;

        String txtImgUrl = "";
        String txtName = "";
        String txtTime = "";
        String txtServes = "";
        String txtLevel = "";
        String txtDescription = "";
        StringBuilder txtIngredientsList = new StringBuilder();
        StringBuilder txtStepsList = new StringBuilder();
        String txtKcals = "";

        int count = 0;

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
                    } else if (tagName.equalsIgnoreCase("level")) {
                        levelTag = true;
                    } else if (tagName.equalsIgnoreCase("description")) {
                        descriptionTag = true;
                    } else if (tagName.equalsIgnoreCase("ingredient")) {
                        ingredientTag = true;
                    } else if (tagName.equalsIgnoreCase("step")) {
                        stepTag = true;
                    } else if (tagName.equalsIgnoreCase("cals")) {
                        kcalsTag = true;
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
                    if (levelTag) {
                        txtLevel = character.getData();
                        levelTag = false;
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
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("product")) {
                        List<String> categoryOfFood = new ArrayList<>();
                        OliverFood food = new OliverFood();
                        food.setImageUrl(txtImgUrl);
                        food.setName(txtName);
                        food.setTime(txtTime);
                        food.setServes(txtServes);
                        food.setLevel(txtLevel);
                        food.setDescription(txtDescription);
                        food.setIngredients(txtIngredientsList.toString());
                        food.setSteps(txtStepsList.toString().trim());
                        categoryOfFood.add(category.get(count).split("/")[2].toLowerCase());
                        if (!staticCategory.toLowerCase().equalsIgnoreCase(category.get(count).split("/")[2].toLowerCase())) {
                            categoryOfFood.add(staticCategory.toLowerCase());
                        }
                        food.setCategory(categoryOfFood);
                        String tmpSteps = txtStepsList.toString().split("&")[0].trim();
                        if (tmpSteps.equals("Step 1:")) {
                            txtStepsList.delete(0, txtStepsList.length());
                            txtStepsList.append("");
                        }
                        if (txtStepsList.toString().split("&").length > 1) {
                            if (!txtStepsList.toString().split("&")[1].equalsIgnoreCase("") && txtStepsList.toString().split("&")[1].equalsIgnoreCase("Step 2:")) {
                                txtStepsList.delete(0, txtStepsList.length());
                                txtStepsList.append("");
                            }
                        }
                        if (txtStepsList.toString().split("-")[0].trim().length() != 7 && !txtStepsList.toString().equals("") && txtKcals.trim().split(":").length == 2) {
                            String tmpName = txtKcals.trim().split(":")[0].trim();
                            if (tmpName.equalsIgnoreCase("Kcals")) {
                                food.setKcals(Double.parseDouble(txtKcals.trim().split(":")[1].trim()));
                                oliverFoodContainer.add(food);
                                category.remove(count);
                            }
                        } else {
                            count++;
                        }
                        txtIngredientsList.delete(0, txtIngredientsList.length());
                        txtStepsList.delete(0, txtStepsList.length());
                    }
                    break;
            }
        }
        return oliverFoodContainer;
    }
}
