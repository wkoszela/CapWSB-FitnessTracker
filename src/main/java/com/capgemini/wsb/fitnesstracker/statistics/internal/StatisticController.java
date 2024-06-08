package com.capgemini.wsb.fitnesstracker.statistics.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticServiceImpl statisticServiceImpl;

    @DeleteMapping("/{statisticId}")
    public void deleteStatistic(@PathVariable Long statisticId) {
        statisticServiceImpl.deleteStatistic(statisticId);
    }


}
