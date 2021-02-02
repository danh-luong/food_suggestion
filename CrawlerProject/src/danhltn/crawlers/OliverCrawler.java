/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.crawlers;

import danhltn.utils.CrawlerUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 *
 * @author leagu
 */
public class OliverCrawler implements Serializable {

    private static final String urlDefault = "https://www.olivemagazine.com/";

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
                String tmp = matcher.group(1);
                if (tmp.contains(urlDefault)) {
                    resultURL = tmp;
                } else {
                    if (tmp.contains("/")) {
                        resultURL = pageCrawl + tmp.split("/")[1];
                    } else {
                        resultURL = pageCrawl + tmp;
                    }
                }
            }
        } catch (IndexOutOfBoundsException in) {
            System.out.println("Can't find with following pattern");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultURL;
    }

    public static double getMaxPageCount(String content, String regex) {
        double maxPage = 0;
        int limit = 0;
        int maxProduct = 0;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        while (m.find()) {
            limit = Integer.parseInt(m.group(1));
            maxProduct = Integer.parseInt(m.group(2));
            maxPage = Math.ceil((double) maxProduct / limit);
        }
        return maxPage;
    }

    public static List getURLData(String pageCrawl, String queryParam, String regex, int maxPage) {
        List containerData = new ArrayList<String>();
        try {
            String content = "";
            Pattern pattern = Pattern.compile(regex);
            for (int i = 1; i <= maxPage; i++) {
                if (queryParam != null) {
                    content = CrawlerUtil.crawlHTML(pageCrawl + "page/" + i + "?" + queryParam);
                } else {
                    content = CrawlerUtil.crawlHTML(pageCrawl);
                }
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()) {
                    containerData.add(matcher.group(1) + ":" + matcher.group(2));
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
        for (int i = 0; i < container.size(); i++) {
            try {
                containerURLItems.add(container.get(i).toString().split(":")[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < containerURLItems.size(); i++) {
            try {
                String content = CrawlerUtil.crawlHTML(urlDefault + containerURLItems.get(i).toString());
                result.append(CrawlerUtil.removeNeedlessTags(speciallyRemoveHeader(content)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    private static String speciallyRemoveHeader(String content) {
        String result = content;
        String remove = "";
        Matcher matcher = Pattern.compile("<head.*?</head>").matcher(content);
        if (matcher.find()) {
            remove = matcher.group(0);
        }
        result = result.replace(remove, "");
        matcher = Pattern.compile("<!doctype.*?>").matcher(content);
        if (matcher.find()) {
            remove = matcher.group(0);
        }
        matcher = Pattern.compile("<html.*?>").matcher(content);
        if (matcher.find()) {
            remove = matcher.group(0);
        }
        result = result.replace(remove, "");
        matcher = Pattern.compile("</html>").matcher(content);
        if (matcher.find()) {
            remove = matcher.group(0);
        }
        result = result.replace(remove, "");
        return result;
    }

    public static boolean validateSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void parseHTMLToXMLWithXSL(String inFilename, String outFilename, String xslFilename) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();

            Templates template = factory.newTemplates(new StreamSource(
                    new FileInputStream(xslFilename)));

            Transformer xformer = template.newTransformer();

            Source source = new StreamSource(new FileInputStream(inFilename));
            Result result = new StreamResult(new FileOutputStream(outFilename));

            xformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeHTMLFile(String fileName, String src) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(src);
        writer.close();
    }
}
