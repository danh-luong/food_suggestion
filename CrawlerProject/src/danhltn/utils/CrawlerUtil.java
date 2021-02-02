/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author leagu
 */
public class CrawlerUtil implements Serializable {

    private static final String[] IGNORE_TAGS = new String[]{"head", "noscript", "style", "iframe", "script"};

    public static String crawlHTML(String webUrl) throws Exception {
        URL url = new URL(webUrl);
        Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
        scanner.useDelimiter("\\Z");
        String content = scanner.next();
        scanner.close();
        content = content.replaceAll("\\R", "").replaceAll("\\s{2,}", " ").trim();;
        return content;
    }

    public static String getMainContent(String src) {
        String result = src;
        Matcher matcher = Pattern.compile("<body.*?</body>").matcher(src);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return result;
    }

    public static String removeNeedlessTags(String src) {
        String result = src;
        String expression = "<!--.*?--!>";
        result = result.replaceAll(expression, "");
        expression = "&nbsp;?";
        result = result.replaceAll(expression, "");
        for (String exp : IGNORE_TAGS) {
            expression = String.format("<%s.*?</%s>", exp, exp);
            result = result.replaceAll(expression, "");
        }
        return result;
    }
}
