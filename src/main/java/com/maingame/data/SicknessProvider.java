package com.maingame.data;

import net.datafaker.providers.base.AbstractProvider;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.service.WeightedRandomSelector;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class SicknessProvider extends AbstractProvider<BaseProviders> {

    private static final WeightedRandomSelector selector = new WeightedRandomSelector(new Random());
    private static final List<Map<String, Object>> sicknessChance = List.of(
            Map.of("value", "Common cold", "weight", 0.6),
            Map.of("value", "Flu", "weight", 0.15),
            Map.of("value", "Covid 19", "weight", 0.1),
            Map.of("value", "Pneumonia", "weight", 0.05),
            Map.of("value", "Strep throat", "weight", 0.05),
            Map.of("value", "Migraine", "weight", 0.03),
            Map.of("value", "Allergies", "weight", 0.02)
    );

    public SicknessProvider(BaseProviders faker) {
        super(faker);
    }

    public String sicknessChance() {
        return selector.select(sicknessChance);
    }

}