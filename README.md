# suspicous-mango

Тестовый проект.
Использовал:
* Java EE 7
* JSF 2.2
* Primefaces 6
* EJB
* Hibernate 5
* PostgreSQL 9.6

Успешно задеплоено на TomEE WebProfile 7.0.3

#Overview

Модель предметной области - упрощенная сеть связи, основанная на стандарте G.805.
С клиентской стороны включает в себя двухстраничное представление:
1. Таблица узлов сети связи. Чтобы посмотреть детали узла, нужно его выделить и нажать кнопку "Показать связи".
2. Детали по соединениям заданного узла с другими. Чтобы вернуться обратно к п. 1, нужно нажать кнопку "Назад.
Пока что кроме отображения этих самых связей ничего приложение не умеет. 
Есть небольшой набор функциональных текстов.
В планах изменить представление связей с табличного вида на PF_Diagram или PF_Horizontal_Tree/

##### Чтобы подготовить приложение к деплою:
```
git clone https://github.com/alsem/suspicous-mango.git
1. файл src/main/resources/hibernate.cfg.xml. настраиваем источник данных.
2. src/main/resources/META-INF/persistence.xml. Подбираем подходящий hibernate.dialect из списка:
https://docs.jboss.org/hibernate/orm/5.2/javadocs/org/hibernate/dialect/package-summary.html
3. выполняем команды
mvn clean
mvn compile war:exploded
```
Далее запускаем сервер приложений, либо определяем конфигурацию в IDE.
Деплоим приложение.

При успешном деплое и запуске дефолтная схема наполнится данными из скрипта data.sql и откроется страница 1.
