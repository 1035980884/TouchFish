package com.touch.fish.view;

import com.touch.fish.entity.book.Ebook;
import com.touch.fish.entity.bookSetting.Ini4jFileVo;
import com.touch.fish.entity.bookSetting.IniFileEntity;
import com.touch.fish.factory.AppSettingsState;
import com.touch.fish.utils.BookInfoUtil;
import com.touch.fish.utils.Ini4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

public class EBookBody  {
    private static final Logger LOG =  LoggerFactory.getLogger(EBookBody.class);

    private JPanel mainPanel;
    private JList<String> pages;
    private JTextArea currentContent;
    private JLabel currentPage;
    private JLabel nextPage;
    private JLabel prePage;
    private JTabbedPane winCard;
    private JLabel reload;
    private JScrollPane scrollbar;
    private List<Ebook> books;
    private List<String> pageList=new ArrayList<>();
    private int curPage=1;
    private int unitIncrement = 25; // 滚动的像素数
    private JViewport viewport;


    public EBookBody() {
        viewport=scrollbar.getViewport();
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        currentContent.setLineWrap(true);

        AppSettingsState settings = AppSettingsState.getInstance();
        if (books != null) {
//            currentContent.setText(books.get(0).getCurrentPage()+"\n"+books.get(0).getContent());
//            currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 9));
//            currentPage.setText(books.get(0).getCurrentPage());
//            pages.setModel(new DefaultComboBoxModel<>(this.pageList.toArray(new String[0])));
            Map<String, List<String>> map = new HashMap<>();
            List<String> list = new ArrayList<>();
            list.add("currentPage");
            map.put("Ebook", list);
            try {
                Ini4jFileVo iniFile=Ini4jUtils.readIniFile(new File(settings.iniPath),map);
                System.out.println(iniFile.toString());
                if (!StringUtils.isEmpty(iniFile.getCurrentPage())) {
                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).getCurrentPage().equals(iniFile.getCurrentPage())) {
                            currentContent.setText(books.get(i).getCurrentPage()+"\n"+books.get(i).getContent());
                            currentPage.setText(books.get(i).getCurrentPage());
                            this.curPage = i+1;
                        }
                    }
                }else {
                    currentContent.setText(books.get(0).getCurrentPage()+"\n"+books.get(0).getContent());
                    currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 9));
                    currentPage.setText(books.get(0).getCurrentPage());
                    pages.setModel(new DefaultComboBoxModel<>(this.pageList.toArray(new String[0])));
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
        }else {
            Map<String, List<String>> map = new HashMap<>();
            List<String> list = new ArrayList<>();
            list.add("filePath");
            map.put("Ebook", list);
            try {
                Ini4jFileVo iniFile= Ini4jUtils.readIniFile(new File(settings.iniPath),map);
                this.books= BookInfoUtil.parse(1,iniFile.getFilePath());
                for (Ebook b: books) {
                    this.pageList.add(b.getCurrentPage());
                }
                if (!StringUtils.isEmpty(iniFile.getCurrentPage())) {
                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).getCurrentPage().equals(iniFile.getCurrentPage())) {
                            currentContent.setText(books.get(i).getCurrentPage()+"\n"+books.get(i).getContent());
                            currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 9));
                            currentPage.setText(books.get(i).getCurrentPage());
                            pages.setModel(new DefaultComboBoxModel<>(this.pageList.toArray(new String[0])));
                            this.curPage = i+1;
                        }
                    }
                }else {
                    currentContent.setText(books.get(0).getContent());
                    currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 9));
                    currentPage.setText(books.get(0).getCurrentPage());
                    pages.setModel(new DefaultComboBoxModel<>(this.pageList.toArray(new String[0])));
                }
                currentContent.setSelectionStart(0);
                currentContent.setSelectionEnd(0);

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
        // 下一章
        nextPage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nextPage();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                nextPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                nextPage.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
//        上一章
        prePage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                prevPage();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                prePage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                prePage.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
//        刷新
        reload.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                reload();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                reload.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                reload.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
//        目录
        pages.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                if(pages.getSelectedIndex() != -1) {

                    if(e.getClickCount() == 1){
                        oneClick(pages.getSelectedValue());
                    }

                    if(e.getClickCount() == 2){
                        twoClick(winCard.getSelectedIndex(),pages.getSelectedIndex(),pages.getSelectedValue());
                    }


                }

            }

            private void oneClick(Object value) {

                //单击处理

            }

            private void twoClick(int tableIndex,int index,Object value) {
                //双击处理
                currentContent.setText(books.get(index).getCurrentPage()+"\n"+books.get(index).getContent());
                currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 9));
                currentPage.setText(books.get(index).getCurrentPage());
                curPage = index+1;
                winCard.setSelectedIndex(0);
                currentContent.setSelectionStart(0);
                currentContent.setSelectionEnd(0);
            }
        });
        currentContent.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int newY=0;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                         newY = viewport.getViewPosition().y - unitIncrement;
                        if (newY < 0) {
                            newY = 0;
                        }
                        viewport.setViewPosition(new Point(0, newY));
                    break;
                    case KeyEvent.VK_DOWN:
                        int maxY = viewport.getViewSize().height - viewport.getExtentSize().height;
                         newY = viewport.getViewPosition().y + unitIncrement;
                        if (newY > maxY) {
                            newY = maxY;
                        }
                        viewport.setViewPosition(new Point(0, newY));
                    break;
                    case KeyEvent.VK_RIGHT:
                        nextPage();
                    break;
                    case KeyEvent.VK_LEFT:
                    prevPage();
                    break;
                    default:

                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
//阅读进度记录
    private void addReadProgressRecord() {
        ArrayList<IniFileEntity> iniFileEntities = new ArrayList<>();
        AppSettingsState settings = AppSettingsState.getInstance();
        IniFileEntity iniFileEntity = new IniFileEntity();
        iniFileEntity.setSection("Ebook");
        iniFileEntity.setKey("currentPage");
        iniFileEntity.setValue(currentPage.getText());
        iniFileEntities.add(iniFileEntity);
        try {
            Ini4jUtils.creatIniFile(settings.winDirPath,settings.iniPath, iniFileEntities);
        } catch (IOException ex) {
            LOG.error("记录阅读记录时发生错误：", ex);
        }
    }

//刷新小说
    public void reload(){
        AppSettingsState settings = AppSettingsState.getInstance();
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("filePath");
        map.put("Ebook", list);
        try {
            Ini4jFileVo iniFile= Ini4jUtils.readIniFile(new File(settings.iniPath),map);
            this.books= BookInfoUtil.parse(1,iniFile.getFilePath());
            pageList = new ArrayList<>();
            for (Ebook b: books) {
                pageList.add(b.getCurrentPage());
            }
            currentContent.setText(books.get(0).getCurrentPage()+"\n"+books.get(0).getContent());
            currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 9));
            currentPage.setText(books.get(0).getCurrentPage());
            curPage=1;
            pages.setModel(new DefaultComboBoxModel<>(this.pageList.toArray(new String[0])));
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    public void nextPage(){
        if (curPage == pageList.size()) {
            return;
        }
        currentContent.setText(books.get(curPage).getCurrentPage()+"\n"+books.get(curPage).getContent());
        currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 9));
        currentPage.setText(books.get(curPage).getCurrentPage());
        currentContent.setSelectionStart(0);
        currentContent.setSelectionEnd(0);
        curPage++;
        addReadProgressRecord();
    }

    public void prevPage() {
        if (curPage == 1) {
            currentContent.setText(books.get(0).getCurrentPage()+"\n"+books.get(0).getContent());
            currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 9));
            currentPage.setText(books.get(0).getCurrentPage());
            curPage=1;
        }else{
            curPage--;
            currentContent.setText(books.get(curPage-1).getCurrentPage()+"\n"+books.get(curPage-1).getContent());
            currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 9));
            currentPage.setText(books.get(curPage-1).getCurrentPage());
        }
        currentContent.setSelectionStart(0);
        currentContent.setSelectionEnd(0);
        addReadProgressRecord();
    }










    public JComponent getComponent() {
        return mainPanel;
    }

    public JTextArea getTextContent() {
        return currentContent;
    }


    public String getTextPane() {
        return currentContent.getText();
    }

    public void setTextPane(String content) {
        this.currentContent.setText(content);
    }

    public List<Ebook> getBooks() {
        return books;
    }

    public void setBooks(List<Ebook> books) {
        this.books = books;
    }

    public JList<String> getPages() {
        return pages;
    }

    public void setPages(JList<String> pages) {
        this.pages = pages;
    }

    public List<String> getPageList() {
        return pageList;
    }

    public void setPageList(List<String> pageList) {
        this.pageList = pageList;
    }


}
