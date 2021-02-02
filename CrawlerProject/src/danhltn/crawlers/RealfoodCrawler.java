/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.crawlers;

import danhltn.utils.CrawlerUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author leagu
 */
public class RealfoodCrawler {

    private static final String urlDefault = "https://realfood.tesco.com";
    
    public static String getASpecificURL(String pageCrawl, String reg, String navName) {
        String resultURL = "";
        try {
            String content = CrawlerUtil.crawlHTML(pageCrawl);
            Pattern pattern = null;
            if (navName != null) {
                pattern = Pattern.compile(reg + navName);
            } else {
                pattern = Pattern.compile(reg);
            }
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) {
                resultURL = matcher.group(1);
            }
        } catch (IndexOutOfBoundsException in) {
            System.out.println("Can't find with following pattern");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultURL;
    }

    public static double getMaxPage(String content, String regex) throws Exception {
        double maxPage = 0;
        int limit = 0;
        int maxProduct = 0;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        while (m.find()) {
            limit = Integer.parseInt(m.group(2));
            maxProduct = Integer.parseInt(m.group(3));
        }
        maxPage = Math.ceil((double) maxProduct / limit);
        return maxPage;
    }
    
    public static List getURLData(String pageCrawl, String queryParam, String regex, int maxPage) {
        List containerData = new ArrayList<String>();
        try {
            String content = "";
            Pattern pattern = Pattern.compile(regex);
            for (int i = 1; i <= maxPage; i++) {
                if (queryParam != null) {
                    String[] queryParamElement = queryParam.split("\\?");
                    content = CrawlerUtil.crawlHTML(pageCrawl + queryParamElement[0] + "&page=" + i + "&" + queryParamElement[1]);
                } else {
                    content = CrawlerUtil.crawlHTML(pageCrawl);
                }
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()) {
                    containerData.add(matcher.group(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return containerData;
    }
    
        public static String getContentSpecificData(List container) {
        List<String> containerURLItems = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        try {
            for (int i = 0; i < container.size(); i++) {
                containerURLItems.add(urlDefault + container.get(i).toString());
            }
            for (int i = 0; i < containerURLItems.size(); i++) {
                String content = CrawlerUtil.crawlHTML(containerURLItems.get(i).toString());
                result.append(CrawlerUtil.removeNeedlessTags(CrawlerUtil.getMainContent(content)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
