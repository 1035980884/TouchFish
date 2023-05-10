package com.touch.fish.factory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.touch.fish.entity.book.Ebook;
import com.touch.fish.entity.bookSetting.Ini4jFileVo;
import com.touch.fish.test.EBookBody;
import com.touch.fish.utils.BookInfoUtil;
import com.touch.fish.utils.Ini4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class EbookToolWindowFactory  implements ToolWindowFactory {

    private EBookBody ebody=new EBookBody();
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(ebody.getComponent(), "", false);
        toolWindow.getContentManager().addContent(content);

    }


}
