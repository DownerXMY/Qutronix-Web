package com.qutronix.cloud.feynmanserver.dto;

import lombok.Data;

@Data
public class MPQW_dataForm {

    private String uuid;

    private String stat_method;
    private String distance;
    private String iniState;
    private String probStates;

    private String perspectives;
    private String colorbar;
    private String photon_number;
    private String mode;
}
