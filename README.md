# MyGithubAPIApp
Клиентское приложение для получения и просмотра пользователей Github,  а также подробных данных о пользователе с помощью github API

1) На первом экране приложения выводится список пользователей github.
2) динамическое добавление пользователей к списку.
3) При нажатии на элемент списка открывается на второй экран, на котором двыведена более подробная информация о пользователе 
(Количество фоловеров, биография, полное имя, когда профиль создан и т.д)


В приложении используются неаутентифицированные запросы, лимит — 60 запросов в час. 
В ветке master список пользователей, полученный в результате запроса, сохраняется в локальную базу данных.
В ветке develop - без использования базы данных.
 
Список подгружается динамически при скролле.
