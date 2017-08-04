package org.leach.dbdoc.generator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.leach.dbdoc.entity.DbTable;

import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * @author Leach
 * @date 2017/2/27
 * <p>
 * HTML文档生成器
 */
public class HtmlGenerator {

    public void generateDoc(String dbName, List<DbTable> tables) {
        //初始化参数
        Properties properties = new Properties();
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //实例化一个VelocityEngine对象
        VelocityEngine velocityEngine = new VelocityEngine(properties);

        VelocityContext context = new VelocityContext();

        context.put("dbName", dbName);
        context.put("tables", tables);

        FileOutputStream fos = null;
        BufferedWriter writer = null;//设置写入的文件编码,解决中文问题
        //String realPath = getRealPath();

        try {
            fos = new FileOutputStream(dbName + ".html");
            writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));

            velocityEngine.mergeTemplate("vm/doc.vm", "utf-8", context, writer);


        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
