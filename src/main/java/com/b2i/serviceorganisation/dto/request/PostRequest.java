package com.b2i.serviceorganisation.dto.request;

import com.b2i.serviceorganisation.constant.OrganisationLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostRequest {
    private String name;
    private Integer maxNumberOfUser;
    private String description;
    private OrganisationLevelEnum organisationLevelEnum;

}
