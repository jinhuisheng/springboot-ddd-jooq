package com.sh.employee;

import com.sh.common.logging.AutoNamingLoggerFactory;
import com.sh.common.utils.RestResult;
import com.sh.common.utils.RestResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee/")
public class LoginController {
    private Logger logger = AutoNamingLoggerFactory.getLogger();

    private EmployeeApplicationService employeeApplicationService;

    @Autowired
    public LoginController(EmployeeApplicationService employeeApplicationService) {
        this.employeeApplicationService = employeeApplicationService;
    }

    @PostMapping("/login")
    public RestResult login(@RequestBody @Valid LoginCommand loginCommand) {
        return employeeApplicationService.login(loginCommand);
    }

    @GetMapping("/byId/{id}")
    public RestResult getUser(@PathVariable(name = "id") String id) {
        return RestResult.success(employeeApplicationService.byId(id));
    }

    @GetMapping("/article")
    public RestResult article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return RestResult.success();
        } else {
            return RestResult.success();
        }
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.OR, value = {"article:add"})
    public RestResult requirePermission() {
        return RestResult.success();
    }

    @RequestMapping(path = "/login/401")
    public RestResult unauthorized() {
        logger.info("unauthorized log test");
        return RestResult.failure(RestResultCode.UNAUTHORIZED, "未授权");
    }
}
