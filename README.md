Для запуска тестов необходимо установить:

Среда разработки (Лучше IDEA: https://www.jetbrains.com/idea/#chooseYourEdition)

Java SDK: https://www.azul.com/products/zulu-community/

Сборщик Gradle: https://gradle.org/

Веб-сервер XAMPP: https://selenium2.ru/articles/136-installing-xampp.html

После того, как веб-сервер установлен, нужно на него установить учебное приложение: https://stru-webinars.stqa.ru/addressbook.zip 

-- Приложение нужно распаковать в каталог xampp/htdocs (Появится после установки XAMPP)

-- Далее нужно запустить веб-сервер и через него открыть базу данных phpmyabmin (MySQL - "admin") Там нужно создать новую базу данных, и в нее загрузить файл "addressbook.SQL"

--После установки для проверки правильности попробуйте залогиниться в приложение и создать там группу или контакт. (http://localhost/addressbook/ login:"admin" pass:"secret") Если создать получилось -- всё в порядке. Если группа или контакт не создалась -- откройте http://localhost/phpmyadmin и проверьте название базы данных, она должна называться addressbook. Там же можно её переименовать: выбрать базу данных слева, вверху вкладка Операции, там среди прочего есть операция переименования.\

Git: https://git-scm.com/downloads (После запуска проекта в среде разрабоки, необходимо дождаться загрузки библиотек)

Веб-драйвер Selenium: https://www.selenium.dev/documentation/webdriver/getting_started/install_drivers/


Для запуска тестов каталога "mantis-tests" необходимо скачать:
Mantis https://www.mantisbt.org/download.php
-- Распаковать нужно в каталог xampp/htdocs 


Для запуска тестов с cервера непрерывной интеграции Jenkins необходимо скачать:
Jenkins https://www.jenkins.io/download/ (Команда запуска через консоль war файла: java -jar jenkins.war )
Selenium Server
