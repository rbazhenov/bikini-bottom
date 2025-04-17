package org.example.model.resident;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResidentCheckAnswer {
    private String checkId;
    private CheckResult result;
}