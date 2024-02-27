package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс Note представляет собой модель заметки с полями:
 * id (идентификатор), title (заголовок), body (текст заметки) и creation (дата создания).
 * Объекты этого класса используются для хранения информации о заметках.
 * Класс содержит конструкторы по умолчанию, обеспечивая доступ к полям.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    private Long id;                          // Идентификационный номер.
    private String title;                     // Заголовок.
    private String body;                      // Содержание заметки.
    private LocalDateTime creation;           // Дата и время создания заметки.

    public Note(String title, String body) {  // Конструктор класса.
        this.title = title;
        this.body = body;
    }
}
