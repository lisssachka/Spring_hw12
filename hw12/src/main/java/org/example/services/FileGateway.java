package org.example.services;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Интерфейс FileGateway представляет собой шлюз для работы с файлами, содержащий метод writeToFile
 * для записи данных в файл.
 * Аннотация @MessagingGateway указывает на использование данного интерфейса в качестве шлюза для отправки сообщений.
 * Параметр defaultRequestChannel определяет канал, через который будут отправляться сообщения.
 * Метод writeToFile принимает параметры filename (имя файла) и data (данные для записи)
 * и записывает данные в указанный файл.
 * Аннотация @Header указывает на использование параметра filename в качестве заголовка сообщения с ключом FILENAME.
 */
@MessagingGateway(defaultRequestChannel = "textInputChanel")
public interface FileGateway {
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, String data);
}
