package com.touch.fish.factory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.touch.fish.view.EBookBody;
import org.jetbrains.annotations.NotNull;

public class EbookToolWindowFactory  implements ToolWindowFactory {

    private EBookBody ebody=new EBookBody();
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(ebody.getComponent(), "", false);
        toolWindow.getContentManager().addContent(content);

    }


}
