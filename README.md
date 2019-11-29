# springboot-ddd-jooq

# run 
```
mvn clean compile
```

## 使用限制(CQRS)
### jpa + querydsl-jpa: Command 领域层仓储使用

querydsl-jpa 将它用于Specifications定义的实现 即：定义规格<br/>
[querydsl-jpa参考手册](https://github.com/querydsl/querydsl/tree/master/querydsl-jpa)

### jooq :只做对外的查询
[jooq参考手册](https://www.jooq.org/doc/3.12/manual-single-page/)


## 关键词
Specification pattern  

logger 使用MDC 生成唯一requestId<br/>
其中，如果是未授权请求，requestId是不正确的，因为还没有进入到当前请求！

## 妥协
Command 使用RestResult 返回结果，而不是无返回，根据httpStatus代表成功

比如登录接口，登录完成后不光要返回状态，还要返回用户信息，用户权限等，这些应该通过controller层再次调用获取。
