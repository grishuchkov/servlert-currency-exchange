
<a name="readme-top"></a>
---

### Навигация:

[1. Описание](#descriptiption)  
[2. Технологии](#tech)  
[3. Быстрый старт](#start)   
[4. Взаимодействие с приложением](#use)  
[5. Автор проекта](#author)

---
<a name="tech"></a>
### Технологии:

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Java-Servlet](https://img.shields.io/badge/Java%20SERVLET-003545?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/MAVEN-%232E7EEA.svg?style=for-the-badge&logo=maven&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-D70A53?style=for-the-badge&logo=Lombok&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)


![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![FlyWay](https://img.shields.io/badge/flyway-%23DD0031.svg?style=for-the-badge&logo=flyway&logoColor=white)

<p align="right">(<a href="#readme-top">↑ Наверх</a>)</p>

---
<a name="descriptiption"></a>
### Описание:

Данное приложение -- это REST API сервис для описания валют и обменных курсов. Позволяет просматривать, редактировать списки валют и обменных курсов, совершать расчёт конвертации произвольных сумм из одной валюты в другую.

В основе приложения лежит технология Java Servlet, в качестве базы данных используется PostgreSQL, а для миграции - FlyWay.
Все запросы к базе написаны на чистом JDBC.  

Все исключения в приложении обрабатываются и выводятся в виде:
```json
{
    "message": "message",
    "statusCode": statusCode
}
```


Веб-интерфейс для проекта не подразумевается.

<p align="right">(<a href="#readme-top">↑ Наверх</a>)</p>

---
<a name="start"></a>
### Быстрый старт:

* Клонируйте репозиторий:  
  `git clone https://github.com/grishuchkov/servlert-currency-exchange.git`


* Для запуска проекта нужно скачать веб-сервер `Apache Tomcat`.  
  В проекте используется `jakarta.servler`, а не `javax.servler`.
  Этот нюанс стоит учитывать, при выборе версии `Apache Tomcat`. При разработке использовался `Tomcat 10.1.13`, [скачать с официального сайта](https://archive.apache.org/dist/tomcat/tomcat-10/v10.1.13/bin/apache-tomcat-10.1.13.zip).


* Для конфигурации нужно выполнить следующие шаги: `Edit Configuration` -> `Add New Configuration` -> `Tomcat Server (local)`.  
  В открывшемся окне нужно выбрать `Configure`, затем указать `Tomcat base directory`, после чего нажать `fix` -> `war-exploded`.


* Проинициализируйте базу данных. Для этого есть два пути: 
  * Используйте файл **docker-compose.yml**. С помощью команды `docker-compose up -d` произойдет создание контейнера с PostgreSQL внутри.
  * Вручную создайте необходимую вам базу данных.


* Сконфигурируйте файл [database.properties](src%2Fmain%2Fresources%2Fdatabase.properties) на основе тех данных, что вы использовали
при инициализации БД. 
  * Если БД была сконфигурирована с помощью docker-compose.yml и с дефолтными настройками, то файл [database.properties](src%2Fmain%2Fresources%2Fdatabase.properties) менять не следует.
  

* Приложение самостоятельно проинициализирует таблицы базы данных, а также вставит тестовые значения с помощью FlyWay миграции.

<p align="right">(<a href="#readme-top">↑ Наверх</a>)</p>

---
<a name="use"></a>
### Взаимодействие с приложением:

Приложение имеет набор команд:

`GET /currencies` -- Получение списка всех валют.

`GET /currency/KZT` -- Получение конкретной валюты по ее коду.

`POST /currencies` -- Сохранение новой валюты.  
Данные передаются в виде полей формы `x-www-form-urlencoded` в теле запроса.
```http request
Content-Type: application/x-www-form-urlencoded
name = Qazaqstan teñgesi &
code = KZT &
sign = ₸
```

`GET exchangeRates` -- Получение всех обменных курсов.


`POST /exchangeRates` -- Добавление нового обменного курса.  
Данные передаются в виде полей формы `x-www-form-urlencoded` в теле запроса.
```http request
Content-Type: application/x-www-form-urlencoded
baseCurrencyCode=RUB&
targetCurrencyCode=KZT&
rate=4.89
```

`GET /exchangeRate/RUBKZT` -- Получение обменного курса для пары валют.

`PATCH /exchangeRate/RUBKZT` -- Изменение валютного курса для пары валют.
Данные передаются в виде полей формы `x-www-form-urlencoded` в теле запроса.
```http request
Content-Type: application/x-www-form-urlencoded
rate=5
```

`GET /exchange?from=RUB&to=KZT&amount=10` -- Расчёт перевода для определенного количества средств из одной валюты в другую.

Получение курса для обмена может быть осуществлено несколькими способами.   
Например, перевод из валюты A в валюту B:
* Если существует валютная пара A-B, то берём её курс.
* Если А-B нет, но существует B-A, то берем её курс и считаем обратный, чтобы получить A-B.
* Если A-B и B-A нет, то проверяем валютные пары USD-A и USD-B и вычисляем из данных курсов курс для A-B
* Остальные возможные сценарии опущены для упрощения.

---
<a name="author"></a>
### Автор проекта:  [Grishuchkov Danila](https://github.com/grishuchkov)

#### Данный репозиторий является реализацией учебного проекта из курса [Java Backend Learning](https://zhukovsd.github.io/java-backend-learning-course/)


---
