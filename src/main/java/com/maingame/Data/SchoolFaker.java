package com.maingame.Data;

import net.datafaker.providers.base.BaseFaker;

public class SchoolFaker extends BaseFaker {

        public SchoolProvider schoolProvider() {
            return getProvider(SchoolProvider.class, SchoolProvider::new);
        }
    }
