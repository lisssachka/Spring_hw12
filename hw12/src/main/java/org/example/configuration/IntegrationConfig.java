package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/**
 * Класс IntegrationConfig является конфигурационным классом Spring, который определяет бины для каналов сообщений.
 * Он содержит два бина для создания каналов: textInputChanel и fileWriterChanel.
 * textInputChanel использует DirectChannel для передачи текстовых сообщений.
 * fileWriterChanel также использует DirectChannel для передачи сообщений для записи в файл.
 */
@Configuration
public class IntegrationConfig {


    @Bean
    public MessageChannel textInputChanel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileWriterChanel() {
        return new DirectChannel();
    }

    /**
     * Данный метод mainTransformer() является бином Spring и представляет собой трансформер,
     * который принимает строку и возвращает строку.
     * Внутри метода содержится логика преобразования входящей строки.
     * Метод принимает входящие сообщения из канала "textInputChanel".
     *
     * @return - обработанные сообщения для канала "fileWriterChanel"
     */
    @Bean
    @Transformer(inputChannel = "textInputChanel", outputChannel = "fileWriterChanel") // Компонент Spring Integration.
    public GenericTransformer<String, String> mainTransformer() {
        return text -> {
            //какая-то логика
            return text;
        };
    }

    /**
     * Данный метод messageHandler() является бином Spring и представляет собой обработчик сообщений,
     * который принимает сообщения из канала "fileWriterChanel" и выполняет запись в файл.
     * Внутри метода создается экземпляр класса FileWritingMessageHandler,
     * который инициализируется файлом "C:/repo/Studies/Java/Pro/Spring/Seminars/Seminar12/files".
     *
     * @return - обработчик сообщений.
     */
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChanel")
    public FileWritingMessageHandler messageHandler() {
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File(
                        "C:/repo/Studies/Java/Pro/Spring/Seminars/Seminar12/files"));
        handler.setExpectReply(false);                                 // Ожидать ответ от обработчика не требуется.
        handler.setFileExistsMode(FileExistsMode.APPEND);              // Режим добавления сведений к сущ-му файлу.
        handler.setAppendNewLine(true);                                // После каждой записи добавляется новая строка.

        return handler;
    }
}