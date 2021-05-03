# BuildComponentShop

## DesignPatterns
При реализации данного проекта были использованы паттерны проектирования. Ниже приводится объяснение некоторых из них:

### Factory Method
В проекте был использован шаблон проектирования Factory Method.
При работе с приложением пользователь выполняет различные операции, система наверняка не знает, какие данные необходимы. Данные для работы с текущей категорией товаров предоставляются по запросу.
Имеется класс Product, который хранит информацию о товарах. Класс NewThread асинхронно собирает информацию с определённого веб-ресурса. Реализация этого действия происходит в методе doInBackground, после чего записывается результат сбора информации.
![Product](https://github.com/VadimTagiev750504/BuildComponentShop/blob/master/Patterns/Images/Product.PNG) 

![NewThread](https://github.com/VadimTagiev750504/BuildComponentShop/blob/master/Patterns/Images/NewThread.PNG)

### Facade
Данный паттерн популярен при разработке ПО под Android. Применяется, если необходимо разделить ПО на логические уровни. Реализован шаблоном Navigation Drawer, а в свою очередь данный шаблон реализован классами GalleryFragment, HomeFragment и BasketActivity.

![NawDrawer](https://github.com/VadimTagiev750504/BuildComponentShop/blob/master/Patterns/Images/onCreate()%20(BasketActivity).PNG)
![NawDrawer](https://github.com/VadimTagiev750504/BuildComponentShop/blob/master/Patterns/Images/onCreateView().PNG)
![NawDrawer](https://github.com/VadimTagiev750504/BuildComponentShop/blob/master/Patterns/Images/HomeFragment.PNG)

### Controller 

Посредник — это поведенческий паттерн проектирования, который позволяет уменьшить связанность множества классов между собой, благодаря перемещению этих связей в один класс-посредник. 
В данном проекте он используется при создании корзины товаров. Реализован методом readThread().

![Controller](https://github.com/VadimTagiev750504/BuildComponentShop/blob/master/Patterns/Images/readThread.PNG)

### Adapter

Также в проекте был реализован паттерн проектирования Adapter. В рамках данного приложения была необходимость в получении данных формата HTML для получения информации о категориях товаров. Данный паттерн был применён для получения как и каталогов товаров, так и конкретной информации о товаре. Реализован классом Context.

![Adapter](https://github.com/VadimTagiev750504/BuildComponentShop/blob/master/Patterns/Images/Context.PNG)
