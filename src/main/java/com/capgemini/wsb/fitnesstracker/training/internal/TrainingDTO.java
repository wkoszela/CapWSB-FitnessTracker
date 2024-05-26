package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDTO {

    private ActivityType activityType;

    private Date startTime;

    private Date endTime;

    private UserDto user;

    private Long id;

    private double distance;

    private double avarangeSpeed;

}
