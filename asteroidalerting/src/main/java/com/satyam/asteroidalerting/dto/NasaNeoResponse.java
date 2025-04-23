package com.satyam.asteroidalerting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NasaNeoResponse {
@JsonProperty("near_earth_objects") // This set the name of the field in the JSON response
    private Map<String, List<Asteroid>> nearEarthObjects;

@JsonProperty("element_count")
    private Long totalAsteroids;
}
