# Share-It – сервис для шеринга любых вещей

```Spring Boot``` ```Spring Data Jpa``` ```PostgreSQL``` ```H2``` ```HttpClient``` ```Swagger``` ```JUnit``` ```Mockito``` ```Jacoco``` ```Gson``` ```Postman``` ```Docker```

Сервис позволяет пользователям, во-первых, рассказывать, какими вещами они готовы поделиться, а во-вторых, находить нужную вещь и брать её в аренду на какое-то время.

Сервис дает возможность не только бронировать вещь на определённые даты, но и закрывать к ней доступ на время бронирования от других желающих. На случай, если нужной вещи на сервисе нет, у пользователей есть возможность оставлять запросы. По запросу можно будет добавлять новые вещи для шеринга.

Сервис позволяет пользователям оставлять комментарии к вещам, аренда которых успешно завершена.

# Архитектура

Сервис поделен на 3 модуля:
1. __Gateway__ – Отдельный микросервис, производящий валидацию dto и перенаправляющий исключительно верные запросы дальше (Server'у) по Rest API.
2. __Server__ – Отдельный микросервис, содержащий основной функционал.
3. __Common__ – Модуль, содержащий общие dto, исключения и константы.

# Запуск
```shell
mvn clean package
docker-compose build
docker-compose up
```

# Тесты
Помимо интеграционных и юнит-тестов (JUnit и Mockito), также написаны [postman-тесты](postman/Share-It-postman-collection.json).

# Rest API
После запуска приложения можно получить более подробную информацию об эндпоинтах по [этой ссылке](http://localhost:8080/swagger-ui/index.html).

![img](/images/rest-api-1.png)
![img](/images/rest-api-2.png)
![img](/images/rest-api-schema.png)
