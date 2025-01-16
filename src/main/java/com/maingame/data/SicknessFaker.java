package com.maingame.data;

import net.datafaker.providers.base.BaseFaker;

public class SicknessFaker extends BaseFaker {
    public SicknessProvider sicknessProvider() {
        return getProvider(SicknessProvider.class, SicknessProvider::new);
    }
}
