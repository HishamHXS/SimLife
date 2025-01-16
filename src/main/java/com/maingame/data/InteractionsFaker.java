package com.maingame.data;

import net.datafaker.providers.base.BaseFaker;

public class InteractionsFaker extends BaseFaker {
    public InteractionsProvider InteractionsProvider() {
        return getProvider(InteractionsProvider.class, InteractionsProvider::new);
    }
}
