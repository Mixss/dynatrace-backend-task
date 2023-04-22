package me.mixss.dynatracebackendtask.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MinMaxAverageResult {
    // this class is to represent data returned by MinMaxAverageController
    private float min;
    private float max;
}
