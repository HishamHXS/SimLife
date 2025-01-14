package com.maingame.Data;

import com.maingame.Model.Decision.PlayerDecision;
import net.datafaker.providers.base.AbstractProvider;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.service.WeightedRandomSelector;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class InteractionsProvider extends AbstractProvider<BaseProviders> {
    protected InteractionsProvider(BaseProviders faker) {
        super(faker);
    }

    private static final WeightedRandomSelector selector = new WeightedRandomSelector(new Random());

    private static final List<Map<String, Object>> socialInteractions = List.of(
            Map.of("value", new String[]{"wants to be friends with me. Should I accept?", "Friend Request",
                    "I became friends with {npc}."}, "weight", 0.7),
            Map.of("value", new String[]{"gave me a compliment on my outfit. Should I compliment back?",
                    "Positive", "I returned the compliment to {npc}."}, "weight", 0.5),
            Map.of("value", new String[]{"invited me to their birthday party. Should I go?",
                    "Positive", "I attended {npc}'s birthday party."}, "weight", 0.6),
            Map.of("value", new String[]{"defended me in an argument. Should I thank them?",
                    "Positive", "I thanked {npc} for their support."}, "weight", 0.3),
            Map.of("value", new String[]{"helped me with homework. Should I offer to help them next time?",
                    "Positive", "I helped {npc} with their homework."}, "weight", 0.7),
            Map.of("value", new String[]{"gave me a gift. Should I return the favor?", "Positive",
                    "I gave a gift to {npc} in return."}, "weight", 0.6),
            Map.of("value", new String[]{"asked if I wanted to join their group. Should I accept?",
                    "Positive", "I joined {npc}'s group."}, "weight", 0.5),

            // Negative interactions
            Map.of("value", new String[]{"called me a meanie pants. I want to slap him, should I?", "Insult",
                    "I got into a fight with {npc}."}, "weight", 0.2),
            Map.of("value", new String[]{"spread rumors about me. Should I spread rumors back?", "Rumor",
                    "I spread rumors about {npc}."}, "weight", 0.1),
            Map.of("value", new String[]{"pushed me in the hallway. Should I push back?", "Physical Aggression",
                    "I pushed {npc} back."}, "weight", 0.3),
            Map.of("value", new String[]{"stole my lunch. Should I confront them?", "Theft",
                    "I confronted {npc} about it."}, "weight", 0.4),
            Map.of("value", new String[]{"ignored me when I tried to talk to them. Should I ignore them back?",
                    "Ignoring", "I ignored {npc} too."}, "weight", 0.3),
            Map.of("value", new String[]{"laughed at me in front of others. Should I embarrass them in return?",
                    "Mockery", "I embarrassed {npc} in front of others."}, "weight", 0.2),
            Map.of("value", new String[]{"tried to take credit for my work. Should I call them out?",
                    "Plagiarism", "I called out {npc} for taking credit for my work."}, "weight", 0.4),

            // Neutral/Miscellaneous interactions
            Map.of("value", new String[]{"asked me for help with homework. Should I help?", "Request for Help",
                    "I helped {npc} with their homework."}, "weight", 0.6),
            Map.of("value", new String[]{"wants to sit with me at lunch. Should I let them?", "Lunch Invitation",
                    "I let {npc} sit with me."}, "weight", 0.5),
            Map.of("value", new String[]{"borrowed my pencil but didn’t return it. Should I ask for it back?",
                    "Loan", "I asked {npc} for my pencil back."}, "weight", 0.7),
            Map.of("value", new String[]{"asked me for my opinion on their new haircut. Should I be honest?",
                    "Opinion Request", "I gave {npc} my honest opinion."}, "weight", 0.5),
            Map.of("value", new String[]{"accidentally bumped into me and didn't apologize. Should I say something?",
                    "Accidental Contact", "I told {npc} they bumped into me."}, "weight", 0.4),
            Map.of("value", new String[]{"challenged me to a friendly competition. Should I accept?", "Challenge",
                    "I accepted {npc}'s challenge."}, "weight", 0.6)
    );

    private static final Map<PlayerDecision.PlayerChoicesType, List<Map<String, Object>>> playerInteractions = Map.of(
            PlayerDecision.PlayerChoicesType.HEALTH, List.of(
                    Map.of("value", new String[]{
                            "I watched a nature documentary and now I'm thinking if I should go on a plant-based diet?",
                            "I feel healthier and more energetic after switching my diet.",
                            "It didn’t really help; I miss my usual meals."
                    }, "weight", 0.1),

                    Map.of("value", new String[]{
                            "I hear that yoga is really good for your body. Should I join a yoga class?",
                            "I feel more relaxed and flexible after starting yoga.",
                            "I didn’t enjoy it much; it was harder than I expected."
                    }, "weight", 0.1),

                    Map.of("value", new String[]{
                            "I saw an online ad about taking medicine, now I'm thinking: should I take antidepressants?",
                            "I feel a lot more stable and in control of my emotions.",
                            "I don’t notice much of a difference; maybe I should try other methods."
                    }, "weight", 0.1),

                    Map.of("value", new String[]{
                            "I’ve been feeling sick lately, should I skip my workout?",
                            "I feel better after resting; skipping the workout helped.",
                            "I regret skipping the workout, I feel even worse now."
                    }, "weight", 0.15),

                    Map.of("value", new String[]{
                            "I’ve been smoking for years, maybe it’s time to quit.",
                            "I can already feel my lungs are cleaner; quitting is the best choice.",
                            "I miss the cigarettes; it’s harder than I thought."
                    }, "weight", 0.2),

                    Map.of("value", new String[]{
                            "I haven’t been sleeping well, should I take sleeping pills?",
                            "I feel well-rested after a full night’s sleep.",
                            "The pills didn’t help; I still feel groggy in the morning."
                    }, "weight", 0.05)
            ),

            PlayerDecision.PlayerChoicesType.HAPPINESS, List.of(
                    Map.of("value", new String[]{
                            "I found an old journal from years ago. Should I start writing in it again?",
                            "Writing has helped me process my thoughts and emotions.",
                            "I forgot how hard it is to keep up with journaling."
                    }, "weight", 0.1),

                    Map.of("value", new String[]{
                            "A close friend invited me to hang out, but I was going to stay home. Should I go?",
                            "I had a great time and feel closer to them now.",
                            "I kind of wish I had stayed home instead."
                    }, "weight", 0.1),

                    Map.of("value", new String[]{
                            "I saw an old dream journal entry about something I wanted to do. Should I finally start?",
                            "Taking the first step feels amazing—I feel inspired!",
                            "I still feel stuck. Maybe I need to find a better way to start."
                    }, "weight", 0.1),

                    Map.of("value", new String[]{
                            "I've been wasting hours on social media every day. Should I delete my apps for a while?",
                            "I feel so much clearer and more present without social media.",
                            "I gave up and reinstalled them—I felt too disconnected."
                    }, "weight", 0.15),

                    Map.of("value", new String[]{
                            "I had a huge argument with someone close to me. Should I try reaching out to fix things?",
                            "We talked things through, and I feel relieved.",
                            "They didn’t seem interested in fixing things, and now I feel worse."
                    }, "weight", 0.2),

                    Map.of("value", new String[]{
                            "I've been feeling really lonely lately. Maybe I should text someone?",
                            "I reached out, and it actually helped me feel better.",
                            "I felt weird about texting them, and it didn’t really help."
                    }, "weight", 0.05)
            )
    );


    public String[] getRandomSocialInteraction() {
        return selector.select(socialInteractions);
    }

    public String[] getRandomPlayerInteraction(PlayerDecision.PlayerChoicesType playerChoicesType) {
        return selector.select(playerInteractions.get(playerChoicesType));
    }
}
