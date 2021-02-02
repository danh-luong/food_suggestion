/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlerproject;

import danhltn.crawlers.OliverCrawler;
import danhltn.crawlers.RealfoodCrawler;
import danhltn.daos.NormalDAO;
import danhltn.daos.RealFoodDAO;
import danhltn.dtos.RealFood;
import danhltn.parsers.RealFoodParser;
import danhltn.utils.CrawlerUtil;
import danhltn.xmlchecker.XmlSyntaxChecker;
import java.io.File;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author leagu
 */
public class RealFoodMain {

    public static void main(String[] args) {
        try {
            String navHappyRealFood = "<a href=\"/vegan-recipes.html\">Plant-based</a></li> <li class=\"\"><a href=\"(.*?)\">";
            String hhMamaURL = RealfoodCrawler.getASpecificURL("https://realfood.tesco.com/", navHappyRealFood, "Vegetarian");
            String specificURLGetAll = "<div class=\"grid-12 text-center\"> <a href=\"/(.*?)\" class=\"ddl-btn ddl-btn_margin-btm\">";
            String getAllURL = RealfoodCrawler.getASpecificURL("https://realfood.tesco.com/" + hhMamaURL, specificURLGetAll, null);
            String regexForPageRealFood = "<h2 class=\"ddl-search-pagination__heading\">Page navigation</h2><span class=\"ddl-search-pagination__text\">Showing <strong>(.*?)</strong>-<strong>(.*?)</strong> of <strong>(.*?)</strong>";
            double maxPage = RealfoodCrawler.getMaxPage(CrawlerUtil.crawlHTML("https://realfood.tesco.com/" + getAllURL), regexForPageRealFood);
            String regexURLDetail = "<a class=\"ddl-search-results__item-link\" href=\"(.*?)\">";
            List<String> urlDataContainer = RealfoodCrawler.getURLData("https://realfood.tesco.com/", getAllURL, regexURLDetail, 1);
            XmlSyntaxChecker checker = new XmlSyntaxChecker();
            String result = RealfoodCrawler.getContentSpecificData(urlDataContainer);
            String checkedResult = checker.check(result);
            File file = new File("realfood.txt");
            file.createNewFile();
            OliverCrawler.writeHTMLFile("realfood.txt", checkedResult.replaceAll("[^\\u0009\\u000a\\u000d\\u0020-\\uD7FF\\uE000-\\uFFFD]", "").replaceAll("[\\uD83D\\uFFFD\\uFE0F\\u203C\\u3010\\u3011\\u300A\\u166D\\u200C\\u202A\\u202C\\u2049\\u20E3\\u300B\\u300C\\u3030\\u065F\\u0099\\u0F3A\\u0F3B\\uF610\\uFFFC]", ""));
            File fileXML = new File("realfoodDataXML.xml");
            fileXML.createNewFile();
            OliverCrawler.parseHTMLToXMLWithXSL(file.getAbsolutePath(), fileXML.getAbsolutePath(), "D:\\FPT_Semester\\Semester_7\\PRX301\\Project\\CrawlerProject\\src\\danhltn\\xml\\realfoodxsl.xsl");
            boolean check = OliverCrawler.validateSchema("D:\\FPT_Semester\\Semester_7\\PRX301\\Project\\CrawlerProject\\src\\danhltn\\xml\\realfood.xsd", fileXML.getAbsolutePath());
            List<RealFood> containerRealFood = null;
            if (check) {
                containerRealFood = RealFoodParser.parserWithStax(fileXML.getAbsolutePath(), "vegetarian");
            }
            HashSet<String> containerCategory = new HashSet<>();
            for (RealFood ele : containerRealFood) {
                for (int i = 0; i < ele.getCategory().size(); i++) {
                    containerCategory.add(ele.getCategory().get(i));
                }
            }
            for (String ele : containerCategory) {
                NormalDAO.insertCategory(ele);
            }
            int currentFoodId = -1;
            int idOfCategoryOfElement = -1;
            for (RealFood ele : containerRealFood) {
                RealFoodDAO.insertOliverFood(ele);
                currentFoodId = NormalDAO.getIdOfLastItemInFood();
                for (int i = 0; i < ele.getCategory().size(); i++) {
                    idOfCategoryOfElement = NormalDAO.getIdCategory(ele.getCategory().get(i));
                    NormalDAO.insertIntoCategoryOfFood(currentFoodId, idOfCategoryOfElement);
                }
            }
            System.out.println("Pass");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
