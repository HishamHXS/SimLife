package com.maingame.Model.Relationships;

import com.maingame.Model.NonPlayerCharacter;
import com.maingame.Model.Player;
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