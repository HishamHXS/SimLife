package com.maingame.Data;

import net.datafaker.providers.base.BaseFaker;

public class CareerFaker extends BaseFaker {
        public CareerProvider careerProvider() {
            return getProvider(CareerProvider.class, CareerProvider::new);
        }
    }
