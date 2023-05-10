package com.touch.fish.factory;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.touch.fish.entity.bookSetting.Ini4jFileVo;
import com.touch.fish.entity.bookSetting.IniFileEntity;
import com.touch.fish.test.TouchFish;
import com.touch.fish.utils.Ini4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TouchFishSettingFactory implements Configurable {
    private static final Logger LOG =  LoggerFactory.getLogger(TouchFishSettingFactory.class);

    private TouchFish touchFish;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public  String getDisplayName() {
        return "Touch Fish";
    }

    @Override
    public JComponent  getPreferredFocusedComponent(){
        return touchFish.getPreferredFocusedComponent();
    }

    @Override
    public @Nullable JComponent createComponent() {
        touchFish = new TouchFish();
        return touchFish.getComponent();
    }

    @Override
    public boolean isModified() {
        AppSettingsState settings = AppSettingsState.getInstance();
//        System.out.println(touchFish.getUrlTextField().getText().equals(settings.filePath));
//        if (touchFish.getUrlTextField().getText().equals(settings.filePath)){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("filePath");
        map.put("Ebook", list);
        try {
            Ini4jFileVo iniFile=Ini4jUtils.readIniFile(new File(settings.iniPath),map);
            if (StringUtils.isEmpty(touchFish.getUrlTextField().getText())) {
                this.touchFish.getUrlTextField().setText(iniFile.getFilePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        }
        boolean modified = !touchFish.getUrlTextField().getText().equals(settings.filePath);
        return modified;
    }

    @Override
    public void apply() throws ConfigurationException {
        ArrayList<IniFileEntity> iniFileEntities = new ArrayList<>();
        AppSettingsState settings = AppSettingsState.getInstance();
        File file =new File(touchFish.getUrlTextField().getText());
        IniFileEntity iniFileEntity = new IniFileEntity();
        iniFileEntity.setSection("Ebook");
        iniFileEntity.setKey("filePath");
        iniFileEntity.setValue(touchFish.getUrlTextField().getText());
        iniFileEntities.add(iniFileEntity);
        iniFileEntity = new IniFileEntity();
        iniFileEntity.setSection("Ebook");
        iniFileEntity.setKey("bookName");
        iniFileEntity.setValue(file.getName());
        iniFileEntities.add(iniFileEntity);

        try {
            Ini4jUtils.creatIniFile(settings.winDirPath,settings.iniPath, iniFileEntities);
        } catch (IOException e) {
            LOG.error("创建TouchFish.ini文件时发生错误：", e);
        }
        settings.filePath = touchFish.getUrlTextField().getText();
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settings.filePath = touchFish.getUrlTextField().getText();

    }

    @Override
    public void disposeUIResources() {
        touchFish = null;
    }

}
