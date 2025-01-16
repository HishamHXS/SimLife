package com.maingame.model.relationships;

import com.maingame.model.NonPlayerCharacter;
import com.maingame.model.Player;
import java.util.ArrayList;
import java.util.List;

public class RelationGroup {
    protected List<NonPlayerCharacter> relations;
    protected Player player;

    public RelationGroup(Player player) {
        this.relations = new ArrayList<>();
        this.player = player;
    }

    public void addRelation(NonPlayerCharacter nonPlayerCharacter) {
        relations.add(nonPlayerCharacter);
    }

    public void removeRelation(NonPlayerCharacter nonPlayerCharacter) {
        relations.remove(nonPlayerCharacter);
    }

    public List<NonPlayerCharacter> listRelations() {
        return relations;
    }
}