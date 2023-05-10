package com.touch.fish.factory;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

@State(
        name = "com.touch.fish.factory.AppSettingsState",
        storages = @Storage("TouchFish.xml")
)
public class AppSettingsState implements PersistentStateComponent<AppSettingsState> {


    public String winDirPath="C:\\Users\\Administrator\\AppData\\Local\\JetBrains\\touchFish";
    public String iniPath = "C:\\Users\\Administrator\\AppData\\Local\\JetBrains\\touchFish\\touchFish.ini";
    public String filePath;
    public String bookName="";
    public String currentPage="";
    public String bookMark="";
    public boolean ideaStatus = false;

    public static AppSettingsState getInstance() {
        return ApplicationManager.getApplication().getService(AppSettingsState.class);
    }

    public String getSystemType(){
        String os = System.getProperties().getProperty("os.name");
        String version = System.getProperty("os.version");


        //Windows操作系统
        if (os != null && os.toLowerCase().startsWith("windows")) {
            System.out.println(String.format("当前系统版本是:%s", os));
        } else if (os != null && os.toLowerCase().startsWith("linux")) {//Linux操作系统
            System.out.println(String.format("当前系统版本是:%s", os));
        } else { //其它操作系统
            System.out.println(String.format("当前系统版本是:%s", os));
        }
        return os;
    }


    @Override
    public @Nullable AppSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull AppSettingsState state) {
        getSystemType();
        XmlSerializerUtil.copyBean(state, this);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
