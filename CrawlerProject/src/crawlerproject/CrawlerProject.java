/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlerproject;

import danhltn.crawlers.OliverCrawler;
import danhltn.crawlers.RealfoodCrawler;
import danhltn.dtos.OliverFood;
import danhltn.dtos.RealFood;
import danhltn.parsers.OliverParser;
import danhltn.parsers.RealFoodParser;
import danhltn.utils.CrawlerUtil;
import danhltn.xmlchecker.XmlSyntaxChecker;
import java.io.File;
import java.util.List;

/**
 *
 * @author leagu
 */
public class CrawlerProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //-------------------------------------OliverPage-------------------------------------
//            String navPatternOliver = "<a tabindex=\"0\" class=\"menu-link \" href=\"(.*?)\" role=\"menuitem\">";
//            String navigationURLOliver = OliverCrawler.getASpecificURL("https://www.olivemagazine.com/", navPatternOliver, "Recipes");
//            String navProduct = "<a class=\"standard-button standard-button--fluid standard-button--constrained standard-button--horizontal-centered\" href=\"(.*?)\" role=\"button\">";
//            String subNavigationRecipes = OliverCrawler.getASpecificURL(navigationURLOliver, navProduct, null);
//            String hrefPage = "\"limit\":(.*?),\"offset\":0,\"total\":(.*?),\"returned\":(.*?)";
//            double maxPage = OliverCrawler.getMaxPageCount(CrawlerUtil.crawlHTML(subNavigationRecipes), hrefPage);
//            String regex = "<h4 class=\"standard-card-new__display-title heading-4\"> <a class=\"standard-card-new__article-title\" href=\"(.*?)\"> (.*?) </a> </h4>";
//            List test = OliverCrawler.getURLData("https://www.olivemagazine.com/search/recipes/", "?category=family", regex, 1);
//            XmlSyntaxChecker checker = new XmlSyntaxChecker();
//            String result = OliverCrawler.getContentSpecificData(test);
//            String checkedResult = checker.check(result);
//            File file = new File("oliverData.txt");
//            file.createNewFile();
//            OliverCrawler.writeHTMLFile("oliverData.txt", checkedResult);
//            File fileXML = new File("oliverDataXML.xml");
//            fileXML.createNewFile();
//            OliverCrawler.parseHTMLToXMLWithXSL(file.getAbsolutePath(), fileXML.getAbsolutePath(), "D:\\FPT_Semester\\Semester_7\\PRX301\\Project\\CrawlerProject\\src\\danhltn\\xml\\oliverxsl.xsl");
//            boolean check = OliverCrawler.validateSchema("D:\\FPT_Semester\\Semester_7\\PRX301\\Project\\CrawlerProject\\src\\danhltn\\xml\\oliverschema.xsd", fileXML.getAbsolutePath());
//            List<OliverFood> oliverFoodContainer = OliverParser.parserWithStax(fileXML.getAbsolutePath(), test);
//            System.out.println("pass");
            //-----------------------------------END 1st-------------------------------------------------
            //----------------------------------happyhealthymama-----------------------------------------
//            String navHappyRealFood = "<a href=\"/vegan-recipes.html\">Plant-based</a></li> <li class=\"\"><a href=\"(.*?)\">";
//            String hhMamaURL = RealfoodCrawler.getASpecificURL("https://realfood.tesco.com/", navHappyRealFood, "Vegetarian");
//            String specificURLGetAll = "<div class=\"grid-12 text-center\"> <a href=\"/(.*?)\" class=\"ddl-btn ddl-btn_margin-btm\">";
//            String getAllURL = RealfoodCrawler.getASpecificURL("https://realfood.tesco.com/" + hhMamaURL, specificURLGetAll, null);
//            String regexForPageRealFood = "<h2 class=\"ddl-search-pagination__heading\">Page navigation</h2><span class=\"ddl-search-pagination__text\">Showing <strong>(.*?)</strong>-<strong>(.*?)</strong> of <strong>(.*?)</strong>";
//            double maxPage = RealfoodCrawler.getMaxPage(CrawlerUtil.crawlHTML("https://realfood.tesco.com/" + getAllURL), regexForPageRealFood);
//            String regexURLDetail = "<a class=\"ddl-search-results__item-link\" href=\"(.*?)\">";
//            List<String> urlDataContainer = RealfoodCrawler.getURLData("https://realfood.tesco.com/", getAllURL, regexURLDetail, 1);
//            XmlSyntaxChecker checker = new XmlSyntaxChecker();
//            String result = RealfoodCrawler.getContentSpecificData(urlDataContainer);
//            String checkedResult = checker.check(result);
//            File file = new File("realfood.txt");
//            file.createNewFile();
//            OliverCrawler.writeHTMLFile("realfood.txt", checkedResult);
//            File fileXML = new File("realfoodDataXML.xml");
//            fileXML.createNewFile();
//            OliverCrawler.parseHTMLToXMLWithXSL(file.getAbsolutePath(), fileXML.getAbsolutePath(), "D:\\FPT_Semester\\Semester_7\\PRX301\\Project\\CrawlerProject\\src\\danhltn\\xml\\realfoodxsl.xsl");
//            boolean check = OliverCrawler.validateSchema("D:\\FPT_Semester\\Semester_7\\PRX301\\Project\\CrawlerProject\\src\\danhltn\\xml\\realfood.xsd", fileXML.getAbsolutePath());
//            List<RealFood> containerRealFood = null;
//            if (check) {
//                containerRealFood = RealFoodParser.parserWithStax(fileXML.getAbsolutePath(), "Vegatarian");
//            }
//            System.out.println("Pass");
            //------------------------------------END-----------------------------------------------------
//            List<OliverFood> containerFood = OliverCrawler.getContentSpecificData(test,
//                    "<img class=\"wp-image-109735 align size-letterbox_image image-handler__image image-handler__image--aspect no-wrap js-lazyload\" data-src=\"(.*?)\" width=\"960\" height=\"408\" alt=\"(.*?)\" title=\"(.*?)\"/>",
//                    "<h1 class=\"heading-1 template-article__title \" itemprop=\"headline name\">(.*?)</h1>", "<span class=\"heading-6 recipe-key-data__text\" itemprop=\"recipeYield\"><i class=\"recipe-key-data__icon (.*?)\"></i>(.*?)</span>", 
//                    "<p class=\"body-copy-large\" itemprop=\"description\">(.*?)</p>", "<span class=\"body-copy\" itemprop=\"recipeIngredient\"> <span class=\"list-group__name\">(.*?)</span>(.*?)</span>",
//                    "<div class=\"editor-content\"> <body><p>(.*?)</p></body> </div>");
//            for (OliverFood oliverFood : containerFood) {
//                System.out.println(oliverFood.getName() + "|" + oliverFood.getCharacteristic() + "|" +
//                        oliverFood.getDescription() + "|" + oliverFood.getImageUrl() + "|" +
//                        oliverFood.getIngredients() + "|" + oliverFood.getSteps());
//            }
//            Map<String, String> map = new LinkedHashMap<String, String>();
//            map.put("Attribute không value", "<hl checked>YEAH</hl>");
//            map.put("Value không bọc trong cặp nháy", "<hl aa=  aa><img a=a />YEAH</hl>");
//            map.put("Attribute dính liền nhau", "<hl a=\"1\"b='2'c=3>YEAH</hl>");
//            map.put("Empty element", "<hl><img src=\"\"><br><hr/></hl>");
//            map.put("Lỗi đóng mở thẻ", "<li><a>Sach Moi</a></h3>");
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                System.out.println(entry.getKey());
//                System.out.println(entry.getValue());
//                System.out.println(checker.check(entry.getValue()));
//                System.out.println();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
