package com.touch.fish.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.touch.fish.entity.book.Ebook;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookInfoUtil {
    public static ArrayList<Ebook> parse(int BookId, String path) {

        ArrayList<Ebook> list = null;
        try {
            File file = new File(path);
            if (!file.isFile() || !file.exists()) {
                return null;
            }
            String encoding=getFileEncoding(path);
            if (encoding == "UNKNOWN") {
                return null;
            }
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            list = new ArrayList<>();
            List<String> pages=new ArrayList<>();
            List<String> content=new ArrayList<>();
            String newStr="";
            while ((lineTxt = bufferedReader.readLine()) != null) {

                lineTxt = lineTxt.trim();
//				正则表达式：我的小说章节名是以 ###开头，###结尾 ，所以用第二个更好
                Pattern p = Pattern.compile("(第\\S*)[章节卷集部篇回](\\s)(\\S*)[^#]");
//				Pattern p = Pattern.compile("(###)(.*)(###)");
                Matcher matcher = p.matcher(lineTxt);
                Matcher matcher2 = p.matcher(lineTxt);
                newStr=newStr+lineTxt+"\n";
                if (matcher.find()) {
                    pages.add(matcher.group());
                    newStr = newStr.replaceAll(matcher.group(), "");
                    content.add(newStr);
                    newStr="";
                }
            }
//			以防最后一个章节无法读取
            if (StringUtils.isNotEmpty(newStr)) {
                content.add(newStr);
            }
            bufferedReader.close();
            pages.add(0,"序章/前言/契子");
            for (int i = 0; i < pages.size(); i++) {
                Ebook ebook = new Ebook();
                ebook.setBookName(file.getName());
                ebook.setContent(content.get(i));
                ebook.setCurrentPage(pages.get(i));
                ebook.setCurrentNumber(i);
                list.add(ebook);
            }
        } catch (Exception e) {
            System.out.println("文件读取失败");
            e.printStackTrace();
        } finally {
            return list;
        }

    }


    public static String getFileEncoding(String fileName) throws IOException {
        String charset = "UNKNOWN";

        try (InputStream inputStream = new FileInputStream(fileName)) {
            byte[] buffer = new byte[4096];
            UniversalDetector detector = new UniversalDetector(null);

            int nread;
            while ((nread = inputStream.read(buffer)) > 0 && !detector.isDone()) {
                detector.handleData(buffer, 0, nread);
            }

            detector.dataEnd();
            charset = detector.getDetectedCharset();
            detector.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return StringUtils.defaultIfBlank(charset, "UNKNOWN");

    }
}
