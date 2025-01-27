#  Чек-лист проверок проекта sauceDemo.
1. Открыть главную страницу. Ввести логин и пароль. Нажать Авторизоваться. Заголовок страницы Products?
2. Нажать на кнопку с data-test=add-to-cart-sauce-labs-backpack. Кнопка стала с data-test=remove-sauce-labs-backpack?
3. Открыть странцу по кнопке корзины с data-test=shopping-cart-link. Заголовок страницы Your Cart?
4. На странице имеется кнопка Remove с data-test=remove-sauce-labs-backpack?
5. Нажать на ссылку с data-test=inventory-item-name. Открылась страница с товаром, у которого название Sauce Labs Backpack?

# В последнюю ветку добавлено новое дз:
1. Составление отчетов при помощи allure
2. Retry.class - класс для автоматической перепроверки теста в случае его падения
3. Добавлены теги в аннотации (testName, description и т.д.)
4. Реализован запуск тестов через *.xml файл
5. Реализовано кросс браузерное тестирование
6. К CrossBrowser.xml подключен Retry.class