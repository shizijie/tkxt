package org.pfw.framework.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * @author  xl 
 * @date 创建时间：2017年6月15日 上午10:53:49 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class TaotiToWord {
	private static Configuration configuration = null;  
    private static Map<String, Template> allTemplates = null;  
      
    static {  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
        configuration.setClassForTemplateLoading(WordGenerator.class, "/wjgl/");  
        allTemplates = new HashMap<String, Template>();   // Java 7 钻石语法  
        try {  
            allTemplates.put("temple", configuration.getTemplate("TaotiToWord.ftl"));  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        }  
    }  
  
    private TaotiToWord() {  
        throw new AssertionError();  
    }  
  
    public static File createDoc(Map<?, ?> dataMap, String type) {  
        String name = "temp" + (int) (Math.random() * 100000) + ".doc";  
        File f = new File(name);  
        Template t = allTemplates.get(type);  
        try {  
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");  
            t.process(dataMap, w);  
            w.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        }  
        return f;  
    }  
}
