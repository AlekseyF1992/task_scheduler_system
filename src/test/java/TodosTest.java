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
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        SimpleTask task2 = new SimpleTask(2, "Купить хлеб");
        todos.add(task1);
        todos.add(task2);

        Task[] result = todos.search("молоко");
        Task[] expected = {task1};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldSearchEpic() {
        Todos todos = new Todos();
        Epic epic1 = new Epic(1, new String[]{"Молоко", "Яйца"});
        Epic epic2 = new Epic(2, new String[]{"Хлеб", "Вода"});
        todos.add(epic1);
        todos.add(epic2);

        Task[] result = todos.search("Яйца");
        Task[] expected = {epic1};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldSearchMeeting() {
        Todos todos = new Todos();
        Meeting meeting1 = new Meeting(1, "Встреча", "Проект А", "10:00");
        Meeting meeting2 = new Meeting(2, "Звонок", "Проект Б", "11:00");
        todos.add(meeting1);
        todos.add(meeting2);

        Task[] result = todos.search("Проект Б");
        Task[] expected = {meeting2};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldSearchReturnMultipleTasks() {
        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Важная задача");
        Epic epic = new Epic(2, new String[]{"Срочно", "Важно"});
        Meeting meeting = new Meeting(3, "Обсуждение", "Важный проект", "12:00");
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("Важн");
        Task[] expected = {simpleTask, epic, meeting};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldSearchReturnEmptyArrayWhenNoMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Хлеб"}));

        Task[] result = todos.search("Несуществующий запрос");
        Task[] expected = new Task[0];
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldSearchInEmptyTodos() {
        Todos todos = new Todos();
        Task[] result = todos.search("любой запрос");
        Task[] expected = new Task[0];
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldAddFirstTaskToEmptyTodos() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Первая задача");
        todos.add(task);

        Task[] result = todos.findAll();
        Task[] expected = {task};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldAddMultipleTasksOfSameType() {
        Todos todos = new Todos();
        SimpleTask task1 = new SimpleTask(1, "Задача 1");
        SimpleTask task2 = new SimpleTask(2, "Задача 2");
        SimpleTask task3 = new SimpleTask(3, "Задача 3");
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] result = todos.findAll();
        Task[] expected = {task1, task2, task3};
        Assertions.assertArrayEquals(expected, result);
    }
}