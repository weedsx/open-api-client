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

