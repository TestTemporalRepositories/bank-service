# bank-service

Сервис, эмулирующий работу банка. Добавляет записи в БД.

init.sql создающий БД:

```SQL
CREATE DATABASE temporal_bank_ops;
CREATE USER ubank PASSWORD 'ubank';
GRANT ALL PRIVILEGES ON DATABASE "temporal_bank_ops" TO ubank;
```
Таблица будет создана миграцией.
