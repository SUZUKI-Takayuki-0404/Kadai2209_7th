## 概要
空港コード、空港名、所在国のテーブルを題材に、以下を実装する。

1.本プロジェクトに使用可能な食うコード一覧を取得（GET）
![airports_airports](https://user-images.githubusercontent.com/113277395/197316886-11ec3ad4-c14f-4d8b-8238-517f3ed115b1.PNG)
--

2.空港コードから空港名と所在国を呼び出し（GET）
![airports_search](https://user-images.githubusercontent.com/113277395/197316856-3db33946-80c2-43da-99ca-ac90800a02f3.PNG)
--

3.空港コード、空港名、所在国をテーブルに新規登録（POST）
![airports_create](https://user-images.githubusercontent.com/113277395/197316892-dbdae23e-1402-4e1b-98d8-4d96b6eddac3.PNG)
--

4.登録済みの空港コード、空港名、所在国に対し、空港名、所在国を更新（PATCH）
![airports_update](https://user-images.githubusercontent.com/113277395/197316881-281a4cb5-2b8c-44e3-821f-f1e2c9f409f4.PNG)
--

5.登録済みの空港コード、空港名、所在国を削除（DELETE）
![airports_delete](https://user-images.githubusercontent.com/113277395/197316889-60791893-c3f3-4bb7-948e-e80561683e44.PNG)
--


## 主なソースファイルの説明

＜パッケージ構成＞
 
 ![image](https://user-images.githubusercontent.com/113277395/205440416-915cc6d1-d22e-4e07-92cc-44bc84fb1f04.png)

・AirportController.java
　RESTControllerを実装。

・AirportService.java/AirportServiceImpl.java
　AirportController.javaに空港コード、空港名、所在国のMapを返せるようにする。

・AirportMapper.java
　data.sqlに登録されている空港コード、空港名、所在国のデータベースをSQLを使って取得する。

・AirportEntitiy.java
　個々の空港のデータ構成を定義する。

