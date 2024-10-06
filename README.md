Простое http-приложение с эндпоинтом, который принимает список http-url и возвращает текст из тэга "Title".

Пример запроса: "localhost:8082/crawler?url=https://www.twich.tv/,https://translate.google.com/"

Для того, чтобы запустить приложение - нужно стартовать Application (com/crawler/Application.scala)

Host и Port - можно указать в application.conf (src/main/resources/application.conf)

Тестировать можно через Postman

