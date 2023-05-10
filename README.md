# TouchFish

一款摸鱼插件

宗旨：当你摸鱼的时候，你才是赚钱!

仅支持Windows系统，

自动识别本地TXT小说字符格式基本上不会出现乱码情况,支持目录章节跳转

```text
 gradle v8.0.2
 jdk11
```
结构目录：

```html
--TouchFish
  |--src
     |--main
        |--java
           |--com.touch.fish
              |--entity
                 |--book
                    |--Ebook.class
                 |--bookSetting
                    |--Ini4jFileVo.class
                    |--IniFileEntity.class
              |--factory
                 |--AppSettingsState.class
                 |--EbookToolWindowFactory.class
                 |--TouchFishSettingFactory.class
              |--utils
                 |--BookInfoUtil.class
                 |--Ini4jUtils.class
              |--view
                 |--EBookBody
                    |--EBookBody.class
                    |--EBookBody.form
              |--TouchFish
                    |--TouchFish.class
                    |--TouchFish.form
        |--resource
  |--build.gradle
  |--settings.gradle
```

```html
    用到的依赖：
// https://mvnrepository.com/artifact/com.alibaba/fastjson
implementation group: 'com.alibaba', name: 'fastjson', version: '1.2.83'
// https://mvnrepository.com/artifact/commons-beanutils/commons-beanutils
implementation group: 'commons-beanutils', name: 'commons-beanutils', version: '1.9.4'
implementation 'org.apache.commons:commons-lang3:3.12.0'
// https://mvnrepository.com/artifact/com.googlecode.juniversalchardet/juniversalchardet
implementation group: 'com.googlecode.juniversalchardet', name: 'juniversalchardet', version: '1.0.3'
```