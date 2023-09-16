# 介绍
提供 API 接口供开发者调用的平台，基于 Spring Boot 后端 + React 前端的全栈微服务项目。管理员可以接入并发布接口、统计分析各接口调用情况；用户可以注册登绿并开通接口调用权限、浏览接口、在线调试，还能使用客户端 SDK 轻松在代码中调用接口。
# 使用

```java
private final Gson gson = new Gson();

// 创建 SDK 客户端
OpenApiClient openApiClient = new OpenApiClient(accessKey, secretKey);

// POST 请求
// requestParams 为 JSON 字符串格式的请求体
// url 即为地址
String result = openApiClient.post(requestParams, url);

// GET 请求
// 请求参数为包含 Param (包含参数名和参数值字段) 参数对象的列表
List<Param> paramList = gson.fromJson(requestParams, new TypeToken<List<Param>>() {
}.getType());
// url 即为地址
String result = openApiClient.getByList(paramList, url);
```

