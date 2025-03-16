

```markdown
# Registration System with Docker

Проект, который демонстрирует, как можно организовать регистрацию с использованием трех Docker-контейнеров:
1. **Frontend** — форма регистрации.
2. **Backend** — сервер, который принимает данные, проверяет их и записывает в базу данных.
3. **Database** — PostgreSQL/MySQL для хранения данных.

## Стек технологий
- **Frontend:** HTML + JavaScript (форма регистрации).
- **Backend:** Java Spring Boot.
- **Database:** PostgreSQL.
- **Docker:** Для контейнеризации приложения.

## Требования
Перед запуском убедитесь, что на вашем компьютере установлены следующие инструменты:
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Установка и запуск

1. Клонируйте репозиторий на свой компьютер:
   ```bash
   git clone https://github.com/your-username/registration-system.git
   cd registration-system
   ```

2. Сборка и запуск контейнеров с помощью Docker Compose:
   ```bash
   docker-compose up --build
   ```

3. Доступ к приложению:
    - **Frontend:** откройте браузер и перейдите по адресу `http://localhost:3000` 
    - **Backend:** он будет доступен на `http://localhost:8080`.

## Структура проекта
```
.
├── backend/
│   └── Dockerfile
│   └── ...
├── frontend/
│   └── Dockerfile
│   └── ...
├── db/
│   └── Dockerfile
│   └── ...
├── docker-compose.yml
├── README.md
└── ...
```

## Как использовать

1. Откройте форму на frontend.
2. Введите данные для регистрации.
3. Нажмите "Отправить". Данные будут переданы на backend и записаны в базу данных.
4. Перейдите по адресу http://localhost:8080/users, чтобы увидеть список всех пользователей.
5. Если все данные корректны, вам будет возвращен ответ "Пользователь успешно сохранен", если нет — ошибка.
```
