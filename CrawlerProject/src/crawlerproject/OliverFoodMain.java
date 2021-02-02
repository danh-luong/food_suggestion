/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlerproject;

import danhltn.crawlers.OliverCrawler;
import danhltn.daos.NormalDAO;
import danhltn.daos.OliverFoodDAO;
import danhltn.dtos.OliverFood;
import danhltn.parsers.OliverParser;
import danhltn.utils.CrawlerUtil;
import danhltn.xmlchecker.XmlSyntaxChecker;
import java.io.File;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author leagu
 */
public class OliverFoodMain {

    public static void main(String[] args) {
        try {
            String navPatternOliver = "<a tabindex=\"0\" class=\"menu-link \" href=\"(.*?)\" role=\"menuitem\">";
            String navigationURLOliver = OliverCrawler.getASpecificURL("https://www.olivemagazine.com/", navPatternOliver, "Recipes");
            String navProduct = "<a class=\"standard-button standard-button--fluid standard-button--constrained standard-button--horizontal-centered\" href=\"(.*?)\" role=\"button\">";
            String subNavigationRecipes = OliverCrawler.getASpecificURL(navigationURLOliver, navProduct, null);
            String hrefPage = "\"limit\":(.*?),\"offset\":0,\"total\":(.*?),\"returned\":(.*?)";
            double maxPage = OliverCrawler.getMaxPageCount(CrawlerUtil.crawlHTML(subNavigationRecipes), hrefPage);
            String regex = "<h4 class=\"standard-card-new__display-title heading-4\"> <a class=\"standard-card-new__article-title\" href=\"(.*?)\"> (.*?) </a> </h4>";
            List test = OliverCrawler.getURLData("https://www.olivemagazine.com/search/recipes/", "?category=family", regex, 1);
            XmlSyntaxChecker checker = new XmlSyntaxChecker();
            String result = OliverCrawler.getContentSpecificData(test);
            String checkedResult = checker.check(result);
            File file = new File("oliverData.txt");
            file.createNewFile();
            OliverCrawler.writeHTMLFile("oliverData.txt", checkedResult);
            File fileXML = new File("oliverDataXML.xml");
            fileXML.createNewFile();
            OliverCrawler.parseHTMLToXMLWithXSL(file.getAbsolutePath(), fileXML.getAbsolutePath(), "D:\\FPT_Semester\\Semester_7\\PRX301\\Project\\CrawlerProject\\src\\danhltn\\xml\\oliverxsl.xsl");
            boolean check = OliverCrawler.validateSchema("D:\\FPT_Semester\\Semester_7\\PRX301\\Project\\CrawlerProject\\src\\danhltn\\xml\\oliverschema.xsd", fileXML.getAbsolutePath());
            List<OliverFood> oliverFoodContainer = null;
            if (check) {
                oliverFoodContainer = OliverParser.parserWithStax(fileXML.getAbsolutePath(), test, "family");
            }
            HashSet<String> containerCategory = new HashSet<>();
            for (OliverFood ele : oliverFoodContainer) {
                for (int i = 0; i < ele.getCategory().size(); i++) {
                    containerCategory.add(ele.getCategory().get(i));
                }
            }
            for (String ele : containerCategory) {
                NormalDAO.insertCategory(ele);
            }
            int currentFoodId = -1;
            int idOfCategoryOfElement = -1;
            for (OliverFood ele : oliverFoodContainer) {
                OliverFoodDAO.insertOliverFood(ele);
                currentFoodId = NormalDAO.getIdOfLastItemInFood();
                for (int i = 0; i < ele.getCategory().size(); i++) {
                    idOfCategoryOfElement = NormalDAO.getIdCategory(ele.getCategory().get(i));
                    NormalDAO.insertIntoCategoryOfFood(currentFoodId, idOfCategoryOfElement);
                }
            }
            System.out.println("pass");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
