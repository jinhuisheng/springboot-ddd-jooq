# CQRS应用
### 写模型 使用jpa + querydsl-jpa

写模型中，jpa Repository只使用两个方法：
```java
//1、repository.findById();
//2、repository.save(entity);

```

其他包含业务逻辑的查询，使用querydsl-jpa ,通过specification去查询:
```java

public class EmployeeSpecification {
    public static Predicate byAccountNumberAndWorkNumber(String accountNumber, String workNumber) {
        QEmployee employee = QEmployee.employee;
        BooleanExpression eqAccountNumber = employee.accountNumber.eq(accountNumber);
        BooleanExpression eqWorkNumber = employee.accountNumber.eq(workNumber);

        return new BooleanBuilder()
                .and(eqAccountNumber)
                .and(eqWorkNumber);
    }
}
// ...
public class EmployeeApplicationService{

public void xxxx(){
//....
//        Predicate specification = EmployeeSpecification.byAccountNumberAndWorkNumber(
//                loginCommand.getAccountNumber(), loginCommand.getWorkNumber());

 //       1、Optional<Employee> employee = employeeRepository.findOne(specification);
 //       2、Iterable<Employee> employees = employeeRepository.findAll(specification);

    }
}
```
querydsl-jpa 将它用于Specifications定义的实现 即：定义规格<br/>
[querydsl-jpa参考手册](https://github.com/querydsl/querydsl/tree/master/querydsl-jpa)

### 读模型 使用jooq :只做对外的查询
映射数据库dto 命名：以Representation结尾 ，比如：EmployeeRepresentation

举例：
基本查询：<br/>

```java
@Component
public class EmployeeRepresentationService {

    private final DSLContext dsl;

    @Autowired
    public EmployeeRepresentationService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public EmployeeRepresentation byId(String id) {
        return dsl.selectFrom(Tables.EMPLOYEE)
                .where(Tables.EMPLOYEE.ID.eq(id))
                .fetch()
                .map(record -> record.into(EmployeeRepresentation.class))
                .get(0);
    }
}

```

左联接：
```java
public class a{

    public void xxxx(){
        List<RolePermissionRepresentation> map = dsl.select()
                .from(SYS_ROLE_PERMISSION)
                .leftJoin(SYS_PERMISSION)
                .on(SYS_ROLE_PERMISSION.PERMISSION_ID.eq(SYS_PERMISSION.ID))
                .where(SYS_ROLE_PERMISSION.ROLE_ID.eq(roleId))
                .fetch()
                .map(record -> record.into(RolePermissionRepresentation.class));
    }
}

```

[jooq参考手册](https://www.jooq.org/doc/3.12/manual-single-page/)



## 关键词
logger 使用MDC 生成唯一requestId,每个请求对应的所有日志都有相同的requestId,方便排查问题<br/>
其中，如果是未授权请求，requestId是不正确的，因为还没有进入到当前请求！

## 妥协
Command 使用RestResult 返回结果，而不是无返回，根据httpStatus代表成功

比如登录接口，登录完成后不光要返回状态，还要返回用户信息，用户权限等，这些应该通过controller层再次调用获取。

## 注意事项：
新增/修改表字段时，需要重新编译代码，因为querydsl-jpa,jooq,mapstruct等编译的时候会生成对应的代码
