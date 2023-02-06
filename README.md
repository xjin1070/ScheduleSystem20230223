# 前端同学注意

~~你们写的网页文件按都放在src/main/resources/static目录下面，后面我们的接口会不断的更新到这个文档里面。~~

明确一下确定要求：

1. 像css/js/img/font这些文件放在static目录下面

   ![image-20230205025034567](C:\Users\10663.LAPTOP-7C3DBSRP\AppData\Roaming\Typora\typora-user-images\image-20230205025034567.png)

2. html文件放在tmplates目录下面

3. 登录首页面叫index.html

4. 访问地址：localhost：8080



# 后端接口

| 名称     | 接口                      | 返回内容 | 描述         |
| -------- | ------------------------- | -------- | ------------ |
| 登录功能 | post:localhost:8080/login | 无：     | 直接进入首页 |
|          |                           |          |              |
|          |                           |          |              |

## 1.员工的增删改查

### 1.1 相关接口

| 名称                     | 接口                                          | 返回内容 | 描述                                       |
| ------------------------ | --------------------------------------------- | -------- | ------------------------------------------ |
| 获取员工                 | get:localhost:8080/emp/110                    | R对象    | 得到账号110的员工所有信息                  |
| 添加员工                 | post:localhost:8080/emp                       | R对象    | 使用json数据添加员工，但是账号是通过推荐的 |
| 获取推荐ID               | get:localhost:8080/emp/recommend              | R对象    | 获取可以使用的id                           |
| 更新员工                 | put:localhost:8080/emp                        | R对象    | 通过json数据更新员工                       |
| 普通分页查询             | get:localhost:8080/emp/1/2                    | R对象    | 1代表页数，2代表每页的条数                 |
| 分页查询和多条件模糊查询 | get:localhost:8080/emp/1/2?name=0&position=员 | R对象    | 获取名字中有0和职位中有0的所有的员工       |
| 删除员工                 | delete:localhost:8080/emp/110                 | R对象    | 删除账号110的员工                          |



### 1.2 返回内容描述

1. R对象

   - flag：代表执行的操作是否成功
   - msg：代表后端返回的消息
   - data：后端返回的数据

2. 获取员工:(flag:true，表示执行成功，msg消息未开工，data是查询到的员工数据)

   ```json
   {
       "flag": true,
       "msg": null,
       "data": {
           "name": "员工0",
           "password": "5f93f983524def3dca464469d2cf9f3e",
           "position": "普通员工",
           "shopId": 1,
           "email": "2220@qq.com",
           "ID": 110
       }
   }
   ```

3. 插入员工

   ```json
   {
       "flag": true,
       "msg": null,
       "data": null
   }
   ```

4. 获取推荐的账号(这里的data就不表示生成的可用的账号)

   ```json
   {
     "flag": true,
     "msg": null,
     "data": 121
   }
   ```

5. 更新员工(flag：true更新成功)

   ```json
   {
       "flag": true,
       "msg": null,
       "data": null
   }
   ```

6. 普通分页查询查到的数据**records中**

   ```json
   {
       "flag": true,
       "msg": null,
       "data": {
           "records": [
               {
                   "name": "员工77777",
                   "password": "5f93f983524def3dca464469d2cf9f3e",
                   "position": "普通员工",
                   "shopId": 1,
                   "email": "2220@qq.com",
                   "ID": 110
               },
               {
                   "name": "员工1",
                   "password": "698d51a19d8a121ce581499d7b701668",
                   "position": "普通员工",
                   "shopId": 1,
                   "email": "2221@qq.com",
                   "ID": 111
               }
           ],
           "total": 11, 
           "size": 2,
           "current": 1,
           "orders": [],
           "optimizeCountSql": true,
           "hitCount": false,
           "countId": null,
           "maxLimit": null,
           "searchCount": true,
           "pages": 6
       }
   }
   ```

7. 多条件分页查询

   ```json
   {
       "flag": true,
       "msg": null,
       "data": {
           "records": [
               {
                   "name": "员工0",
                   "password": "5f93f983524def3dca464469d2cf9f3e",
                   "position": "普通员工",
                   "shopId": 1,
                   "email": "22222@qq.com",
                   "ID": 120
               }
           ],
           "total": 1,
           "size": 2,
           "current": 1,
           "orders": [],
           "optimizeCountSql": true,
           "hitCount": false,
           "countId": null,
           "maxLimit": null,
           "searchCount": true,
           "pages": 1
       }
   }
   ```

8. 删除员工

   ```json
   {
       "flag": true,
       "msg": null,
       "data": null
   }
   ```

## 2.门店增删改查

### 2.1 相关接口信息

| 名称               | 接口                               | 返回内容 | 描述                                      |
| ------------------ | ---------------------------------- | -------- | ----------------------------------------- |
| 推荐门店id         | get：localhost:8080/shop/recommend | R对象    | 返回系统选中原先不存在的id                |
| 查询单个门店       | get：localhost:8080/shop/1         | R对象    | 查询id为1的门店的信息                     |
| 增加门店信息       | post：localhost:8080/shop          | R对象    | 里面传递的是json数据                      |
| 删除门店信息       | delete：localhost:8080/shop/2      | R对象    | 删除id为2的门店，同时会删除对应门店的员工 |
| 得到所有门店的信息 | get：localhost:8080/shop           | R对象    | 得到所有门店的信息就是json列表            |
| 更新门店信息       | put：localhost:8080/shop           | R对象    | 传递的也是json数据                        |

### 2.2 相关内容描述

1. 传递和更新shop数据库 都是传递的json数据

   ```json
   {
       "name": "南京公司测试111",
       "address": "南京",
       "size": 33.0,
       "ID": 1
   }
   ```

2. 得到所有门店信息返回的json数据是

   ```json
   {
       "flag": true,
       "msg": null,
       "data": [
           {
               "name": "南京公司",
               "address": "南京",
               "size": 33.0,
               "ID": 1
           },
           {
               "name": "测试数据2",
               "address": "测试数据2",
               "size": 50.0,
               "ID": 2
           },
           {
               "name": "测试数据3",
               "address": "测试数据3",
               "size": 50.0,
               "ID": 3
           },
           {
               "name": "测试数据4",
               "address": "测试数据4",
               "size": 50.0,
               "ID": 4
           },
           {
               "name": "测试数据5",
               "address": "测试数据5",
               "size": 50.0,
               "ID": 5
           },
           {
               "name": "测试数据6",
               "address": "测试数据6",
               "size": 50.0,
               "ID": 6
           },
           {
               "name": "测试数据7",
               "address": "测试数据7",
               "size": 50.0,
               "ID": 7
           },
           {
               "name": "测试数据8",
               "address": "测试数据8",
               "size": 50.0,
               "ID": 8
           },
           {
               "name": "测试数据9",
               "address": "测试数据9",
               "size": 50.0,
               "ID": 9
           }
       ]
   }
   ```

   

   

