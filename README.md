# springboot-ddd-jooq


# run 
```
mvn clean compile
```

## 使用限制
jpa: 领域层仓储使用
jooq :只做对外的查询


## 关键词
Specification pattern  
querydsl-jpa 将它用于Specifications定义的实现
即：定义规格

logger 使用MDC 生成唯一requestId

## 妥协
Command 使用RestResult 返回结果，而不是无返回，根据httpStatus代表成功