/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.xmlchecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author leagu
 */
public class TextUtils {

    public static String refinehtml(String src) {
        src = getBody(src);
        src = removeMiscellaneousTags(src);

        XmlSyntaxChecker xmlsyntaxchecker = new XmlSyntaxChecker();
        src = xmlsyntaxchecker.check(src);

        src = getBody(src);
        return src;
    }

    private static String getBody(String src) {
        String result = src;
        String expression = "<body.*?</body >";
        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            result = matcher.group(0);
        }
        return result;
    }

    public static String removeMiscellaneousTags(String src) {
        String result = src;
        String expression = "<script.*?</script >";
        result = result.replaceAll(expression, "");

        expression = "<!--.*?-->";
        result = result.replaceAll(expression, "");
        
        expression = "&nbsp;?";
        result = result.replaceAll(expression, "");
        return result;
    }

}
