# Neoflex (Neostudy) - тестовое задание
[![Java](https://img.shields.io/badge/-Java%2011-F29111?style=for-the-badge&logo=java&logoColor=e38873)](https://www.oracle.com/java/)
[![Spring](https://img.shields.io/badge/-Spring%20Boot%202.7-6AAD3D?style=for-the-badge&logo=spring-boot&logoColor=90fd87)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/-Maven-7D2675?style=for-the-badge&logo=apache&logoColor=e38873)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Postman](https://img.shields.io/badge/Postman%2011-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)
[![JUnit](https://img.shields.io/badge/JUnit%205-6CA315?style=for-the-badge&logo=JUnit&logoColor=white)](https://junit.org/junit5/docs/current/user-guide/)
[![Mockito](https://img.shields.io/badge/-mockito%205.7-6CA315?style=for-the-badge&logo=mockito&logoColor=90fd87)](https://site.mockito.org/)
[![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)](https://git-scm.com/)

## Описание
Создание эндпоинта для расчёта отпускных на основе следующих данных: 1. среднемесячной зарплаты в течение года, 2. списка отпускных дней.

Отпускные дни не начисляются за выходные (суббота, воскресенье), новогодние праздники (1-8 января), 
остальные государственные праздники (23 февраля, 8 марта, 1 и 9 мая, 12 июня, 4 ноября).

Неновогодние праздники переносятся на понедельник в случае, когда они выпадают на выходные дни (суббота, воскресенье).

## Архитектура
Приложение на микросервисной архитектуре в виде 1 контейнера neoflex-service.
API представлено в виде эндпоинта GET /calculacte для расчёта отпускных

## Как запустить и использовать
Для локального запуска бекенд-приложения установите и откройте программу
[Docker Desktop](https://www.docker.com/products/docker-desktop/).
<br>Затем в командной строке cmd выполните следующие команды

   ```
git clone git@github.com:OsipovKonstantin/neoflex.git
cd ~/neoflex   
mvn clean package
docker-compose up
   ```
Приложение готово к использованию! Сервис доступен по андресу [http://localhost:8080](http://localhost:8080).
<br>Со сценариями работы приложения можно ознакомится, запустив коллекции
[Postman-тестов](postman/neoflex.postman_collection.json).
Для этого необходимо открыть ПО Postman, нажать сочетание клавиш Ctrl+O (импорт), выбрать импорт по следующему пути Пользователи/имя_вашего_пользователя/neoflex/postman/neoflex.postman_collection.json
Заходим в импортированную коллекцию, выбираем пункт Run/Run neoflex
