package ru.netology.javaqa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TasksTest {

    @Test
    public void shouldSimpleTaskMatchQuery() {
        SimpleTask task = new SimpleTask(1, "Купить молоко");
        Assertions.assertTrue(task.matches("молоко"));
    }

    @Test
    public void shouldSimpleTaskNotMatchQuery() {
        SimpleTask task = new SimpleTask(1, "Купить молоко");
        Assertions.assertFalse(task.matches("хлеб"));
    }

    @Test
    public void shouldEpicMatchQueryInFirstSubtask() {
        Epic epic = new Epic(2, new String[]{"Молоко", "Яйца", "Хлеб"});
        Assertions.assertTrue(epic.matches("Молоко"));
    }

    @Test
    public void shouldEpicMatchQueryInLastSubtask() {
        Epic epic = new Epic(2, new String[]{"Молоко", "Яйца", "Хлеб"});
        Assertions.assertTrue(epic.matches("Хлеб"));
    }

    @Test
    public void shouldEpicNotMatchQuery() {
        Epic epic = new Epic(2, new String[]{"Молоко", "Яйца", "Хлеб"});
        Assertions.assertFalse(epic.matches("Вода"));
    }

    @Test
    public void shouldMeetingMatchQueryInTopic() {
        Meeting meeting = new Meeting(3, "Выкатка 3й версии", "Приложение НетоБанка", "Во вторник");
        Assertions.assertTrue(meeting.matches("Выкатка"));
    }

    @Test
    public void shouldMeetingMatchQueryInProject() {
        Meeting meeting = new Meeting(3, "Выкатка 3й версии", "Приложение НетоБанка", "Во вторник");
        Assertions.assertTrue(meeting.matches("НетоБанка"));
    }

    @Test
    public void shouldMeetingNotMatchQuery() {
        Meeting meeting = new Meeting(3, "Выкатка 3й версии", "Приложение НетоБанка", "Во вторник");
        Assertions.assertFalse(meeting.matches("Сбербанк"));
    }

    @Test
    public void shouldTaskEqualsToItself() {
        SimpleTask task = new SimpleTask(1, "Тест");
        Assertions.assertTrue(task.equals(task));
    }

    @Test
    public void shouldTaskNotEqualToNull() {
        SimpleTask task = new SimpleTask(1, "Тест");
        Assertions.assertFalse(task.equals(null));
    }

    @Test
    public void shouldTaskNotEqualDifferentClass() {
        SimpleTask task = new SimpleTask(1, "Тест");
        Epic epic = new Epic(2, new String[]{"Подзадача"});
        Assertions.assertFalse(task.equals(epic));
    }

    @Test
    public void shouldTwoSimpleTasksWithSameIdBeEqual() {
        SimpleTask task1 = new SimpleTask(1, "Тест1");
        SimpleTask task2 = new SimpleTask(1, "Тест2");
        Assertions.assertTrue(task1.equals(task2));
    }

    @Test
    public void shouldTwoSimpleTasksWithDifferentIdNotBeEqual() {
        SimpleTask task1 = new SimpleTask(1, "Тест");
        SimpleTask task2 = new SimpleTask(2, "Тест");
        Assertions.assertFalse(task1.equals(task2));
    }

    @Test
    public void shouldTwoEpicsWithSameIdBeEqual() {
        Epic epic1 = new Epic(1, new String[]{"Подзадача1"});
        Epic epic2 = new Epic(1, new String[]{"Подзадача2"});
        Assertions.assertTrue(epic1.equals(epic2));
    }

    @Test
    public void shouldTwoEpicsWithDifferentIdNotBeEqual() {
        Epic epic1 = new Epic(1, new String[]{"Подзадача"});
        Epic epic2 = new Epic(2, new String[]{"Подзадача"});
        Assertions.assertFalse(epic1.equals(epic2));
    }

    @Test
    public void shouldTwoMeetingsWithSameIdBeEqual() {
        Meeting meeting1 = new Meeting(1, "Тема", "Проект", "10:00");
        Meeting meeting2 = new Meeting(1, "Другая тема", "Другой проект", "11:00");
        Assertions.assertTrue(meeting1.equals(meeting2));
    }

    @Test
    public void shouldTwoMeetingsWithDifferentIdNotBeEqual() {
        Meeting meeting1 = new Meeting(1, "Тема", "Проект", "10:00");
        Meeting meeting2 = new Meeting(2, "Тема", "Проект", "10:00");
        Assertions.assertFalse(meeting1.equals(meeting2));
    }

    @Test
    public void shouldSimpleTasksWithSameIdHaveSameHashCode() {
        SimpleTask task1 = new SimpleTask(42, "Тест1");
        SimpleTask task2 = new SimpleTask(42, "Тест2");
        Assertions.assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    public void shouldEpicsWithSameIdHaveSameHashCode() {
        Epic epic1 = new Epic(42, new String[]{"А"});
        Epic epic2 = new Epic(42, new String[]{"Б"});
        Assertions.assertEquals(epic1.hashCode(), epic2.hashCode());
    }

    @Test
    public void shouldMeetingsWithSameIdHaveSameHashCode() {
        Meeting meeting1 = new Meeting(42, "Тема1", "Проект1", "10:00");
        Meeting meeting2 = new Meeting(42, "Тема2", "Проект2", "11:00");
        Assertions.assertEquals(meeting1.hashCode(), meeting2.hashCode());
    }

    @Test
    public void shouldMeetingMatchQueryInBothTopicAndProject() {
        Meeting meeting = new Meeting(1, "Важная встреча", "Важный проект", "10:00");
        Assertions.assertTrue(meeting.matches("Важн"));
    }

    @Test
    public void shouldMeetingNotMatchWhenQueryInStartOnly() {
        Meeting meeting = new Meeting(1, "Встреча", "Проект", "10:00");
        Assertions.assertFalse(meeting.matches("10:00"));
    }

    @Test
    public void shouldEpicWithEmptySubtasksNotMatch() {
        Epic epic = new Epic(1, new String[]{});
        Assertions.assertFalse(epic.matches("любой запрос"));
    }

    @Test
    public void shouldEpicMatchOnlyInMiddleSubtask() {
        Epic epic = new Epic(1, new String[]{"Первая", "Вторая", "Третья"});
        Assertions.assertTrue(epic.matches("Вторая"));
    }

    @Test
    public void shouldMeetingGettersReturnCorrectValues() {
        Meeting meeting = new Meeting(10, "Тема встречи", "Название проекта", "2024-01-01 15:00");
        Assertions.assertEquals("Тема встречи", meeting.getTopic());
        Assertions.assertEquals("Название проекта", meeting.getProject());
        Assertions.assertEquals("2024-01-01 15:00", meeting.getStart());
    }

    @Test
    public void shouldEpicGetSubtasksReturnCorrectArray() {
        String[] subtasks = {"Задача 1", "Задача 2", "Задача 3"};
        Epic epic = new Epic(5, subtasks);
        String[] result = epic.getSubtasks();
        Assertions.assertArrayEquals(subtasks, result);
    }
}