package ru.netology.javaqa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSimpleTask() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new SimpleTask(2, "Купить хлеб"));

        Task[] result = todos.search("молоко");

        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals("Купить молоко", ((SimpleTask) result[0]).getTitle());
    }

    @Test
    public void shouldSearchEpic() {
        Todos todos = new Todos();
        todos.add(new Epic(1, new String[]{"Молоко", "Яйца"}));
        todos.add(new Epic(2, new String[]{"Хлеб", "Вода"}));

        Task[] result = todos.search("Яйца");

        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals(1, result[0].getId());
    }

    @Test
    public void shouldSearchMeeting() {
        Todos todos = new Todos();
        todos.add(new Meeting(1, "Встреча", "Проект А", "10:00"));
        todos.add(new Meeting(2, "Звонок", "Проект Б", "11:00"));

        Task[] result = todos.search("Проект Б");

        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals(2, result[0].getId());
    }

    @Test
    public void shouldSearchReturnMultipleTasks() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Важная задача"));
        todos.add(new Epic(2, new String[]{"Срочно", "Важно"}));
        todos.add(new Meeting(3, "Обсуждение", "Важный проект", "12:00"));

        Task[] result = todos.search("Важн"); // Подходит всем трём

        Assertions.assertEquals(3, result.length);
    }

    @Test
    public void shouldSearchReturnEmptyArrayWhenNoMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Хлеб"}));

        Task[] result = todos.search("Несуществующий запрос");

        Assertions.assertEquals(0, result.length);
    }

    @Test
    public void shouldSearchInEmptyTodos() {
        Todos todos = new Todos();
        Task[] result = todos.search("любой запрос");

        Assertions.assertEquals(0, result.length);
    }

    @Test
    public void shouldAddFirstTaskToEmptyTodos() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Первая задача");
        todos.add(task);

        Task[] result = todos.findAll();
        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals(task, result[0]);
    }

    @Test
    public void shouldAddMultipleTasksOfSameType() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Задача 1"));
        todos.add(new SimpleTask(2, "Задача 2"));
        todos.add(new SimpleTask(3, "Задача 3"));

        Task[] result = todos.findAll();
        Assertions.assertEquals(3, result.length);
    }

}