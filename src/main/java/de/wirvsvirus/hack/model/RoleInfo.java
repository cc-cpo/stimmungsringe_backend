package de.wirvsvirus.hack.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoleInfo {

    private Role role;
    private String expectation;
    private String selfRecommendation;
    private String forMe;
    private String thirdPartyRecommendation;
    private String forOthers;

}
