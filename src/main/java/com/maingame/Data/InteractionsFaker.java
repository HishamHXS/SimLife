package com.maingame.Data;

import net.datafaker.providers.base.BaseFaker;

public class InteractionsFaker extends BaseFaker {
    public InteractionsProvider InteractionsProvider() {
        return getProvider(InteractionsProvider.class, InteractionsProvider::new);
    }
}
