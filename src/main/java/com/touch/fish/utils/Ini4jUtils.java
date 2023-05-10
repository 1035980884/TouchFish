package com.touch.fish.utils;

import com.touch.fish.entity.bookSetting.Ini4jFileVo;
import com.touch.fish.entity.bookSetting.IniFileEntity;
import org.ini4j.Ini;
import org.ini4j.Profile;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class Ini4jUtils {
    public static boolean creatIniFile(String dirPath, String filePath, List<IniFileEntity> filecontent) throws IOException {
        File file = new File(filePath);
        File dir=new File(dirPath);
        //如果文件夹不存在则创建
        if  (!dir .exists()  && !dir .isDirectory()){
            dir .mkdir();
        }

        if(file.exists()){
            updateIniFile(file,filecontent);
            return false;
        }
        file.createNewFile();
        Ini ini = new Ini();
        ini.load(file);

        //将文件内容保存到ini对象中
        filecontent.stream().forEach((entity)->{
            ini.add(entity.getSection(),entity.getKey(),entity.getValue()== null ? "": entity.getValue());
        });
        //将文件内容保存到文件中
        ini.store(file);
        return true;
    }


    /**
     * 读取ini文件的内容
     * @param iniFile ini文件
     * @param fileContent ini文件中的key对应文件中的section，value对应i你文件section下的一个或多个key值
     * @return
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Ini4jFileVo readIniFile(File iniFile, Map<String,List<String>> fileContent) throws IOException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Ini4jFileVo fileVo = new Ini4jFileVo();
        Ini ini = new Ini();
        ini.load(iniFile);
        Profile.Section section = null;
        Field field = null;
        for(String key : fileContent.keySet()){
            section = ini.get(key);
//            System.out.println(section);
            fileVo=JSONObject.parseObject(JSONObject.toJSONString(section),Ini4jFileVo.class);

        }

        /**
         * 这个是简略版的
         *    Section section = ini.get("ldap");
         *  fileVo.setIp(section.get("ip"));
         *   fileVo.setIpPort(section.get("port" ));
         *
         *  section = ini.get("test");
         *   fileVo.setIsUsed(section.get("isUsed"));
         */
        return fileVo;
    }

    /**
     * 修改文件内容
     * @param iniFile ini文件
//     * @param updateData 更新的数据  Map<String,Map<String,String>> updateData
     * @throws IOException
     */
    public static void updateIniFile(File iniFile,List<IniFileEntity> fileEntityList) throws IOException {
        Ini ini = new Ini();
        ini.load(iniFile);
        Profile.Section section = null;
        Map<String,String> dataMap = null;
        for (IniFileEntity fileEntity: fileEntityList){
            section = ini.get(fileEntity.getSection());
            section.put(fileEntity.getKey(), fileEntity.getValue()== null ? "" :fileEntity.getValue());
        }
//        for (String sect : updateData.keySet()){
//            section = ini.get(sect);
//            dataMap = updateData.get(sect);
//            for (String key : dataMap.keySet()){
//                section.put(key,dataMap.get(key) == null ? "" :
//                        dataMap.get(key));
//            }
//        }
        ini.store(iniFile);
    }
}
