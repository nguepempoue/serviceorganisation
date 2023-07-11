package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.dto.request.UserCategoryRequest;
import com.b2i.serviceorganisation.service.UserCategoryServiceImplementation;
import org.springframework.stereotype.Component;

@Component
public class UserCategoryBootstrap {

    private final UserCategoryServiceImplementation userCategoryServiceImplementation;

    public UserCategoryBootstrap(UserCategoryServiceImplementation userCategoryServiceImplementation) {
        this.userCategoryServiceImplementation = userCategoryServiceImplementation;
    }

    public void seed() throws Exception {
        if(userCategoryServiceImplementation.countAllUserCategories() == 0L) {
            userCategoryServiceImplementation.createCategory(new UserCategoryRequest("VIP", "vip"));
        }
    }
}
