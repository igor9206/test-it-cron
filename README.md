# Тестовое задание для IT-CRON

Главный экран:
1. Users (список всех Github пользователей). Использовать API 
https://developer.github.com/v3/users/#get-all-users
2. В элементе списка отрисовать avatar, login (title), id (subtitle)
3. По нажатию на элемент списка реализовать переход на UserDetails
4. Реализовать pagination и Pull-to-refresh

UserDetails (экран с информацией о пользователе):
1. Использовать API https://developer.github.com/v3/users/#get-a-single-user
2. Поля: Avatar, Name, Email, Organization (если есть), Following count, Followers count, Дата
создания аккаунта

#### Инструменты

+ MVVM
+ Android Studio.
+ Kotlin.
+ Navigation component.
+ Retrofit.
+ Dagger Hilt.
+ Room
+ Git + GitHub + Action.
