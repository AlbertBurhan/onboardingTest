package org.ait.project.onboardingtest.modules.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.ait.project.onboardingtest.config.exception.ModuleException;
import org.ait.project.onboardingtest.shared.constant.enums.ResponseEnum;

@Slf4j
public class UserRepositoryNotFound extends ModuleException {

    public UserRepositoryNotFound()
    {
        super(ResponseEnum.ORDER_NOT_FOUND);
        log.error("Order not found");
    }
}
