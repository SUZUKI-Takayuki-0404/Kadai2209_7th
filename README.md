## 概要
空港コード、空港名、所在国のテーブルを題材に、以下を実装する。

1.空港コードから空港名と所在国を呼び出し（GET）
![image](https://user-images.githubusercontent.com/113277395/205448819-b681a1a0-6e75-447d-9135-fcaff057c71c.png)
--

2.空港コード、空港名、所在国をテーブルに新規登録（POST）
![image](https://user-images.githubusercontent.com/113277395/205448623-bde0f915-e440-4ec6-9741-51ed2c8ad241.png)
--

3.登録済みの空港コード、空港名、所在国に対し、空港名、所在国を更新（PATCH）
![image](https://user-images.githubusercontent.com/113277395/205448585-d8f3f594-eb96-4b99-b104-4a26a5ddfcb7.png)
--

4.登録済みの空港コード、空港名、所在国を削除（DELETE）
![image](https://user-images.githubusercontent.com/113277395/205448751-a17f6e26-b52c-4e23-bfa9-d2e40918be07.png)
--

5.初回送信時に/airports/airportsで取得要（GET）
![image](https://user-images.githubusercontent.com/113277395/205448670-8d43bd92-2247-4580-a2f4-db415a169906.png)
--

## 主なソースファイルの説明
・AirportController.java
　RESTControllerを実装。

・AirportService.java/AirportServiceImpl.java
　AirportController.javaに空港コード、空港名、所在国のMapを返せるようにする。

・AirportMapper.java
　data.sqlに登録されている空港コード、空港名、所在国のデータベースをSQLを使って取得する。

・AirportEntitiy.java
　個々の空港のデータ構成を定義する。
 
＜パッケージ構成＞
 
![image](https://user-images.githubusercontent.com/113277395/205440416-915cc6d1-d22e-4e07-92cc-44bc84fb1f04.png)
