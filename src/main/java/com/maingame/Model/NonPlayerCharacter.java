package com.maingame.Model;

import static com.maingame.Helper.ActionChance.rollInt;

public class NonPlayerCharacter extends Entity {

  public enum RelationshipStates {
      STRANGER,
      FRIEND,
      PARENT,
      SIBLING
   }
   private int relationshipLevel;
   private RelationshipStates relationshipStatus;

    public NonPlayerCharacter(String firstName, String lastName, int age) {
        super(firstName, lastName, age, 0, 0);
        this.relationshipStatus = RelationshipStates.STRANGER;
        this.relationshipLevel = 50;
   }

   public NonPlayerCharacter(String firstName, String lastName, int age, RelationshipStates relationshipStatus) {
        super(firstName, lastName, age, 0, 0);
        this.relationshipStatus = relationshipStatus;
   }

   public void updateRelationshipStatus(RelationshipStates relationshipState) {
        this.relationshipStatus = relationshipState;
   }

   public RelationshipStates getRelationshipStatus() {
        return this.relationshipStatus;
    }

   public void updateRelationshipLevel(int modifier){
        this.relationshipLevel += modifier;
   }

   public void updateRelationshipLevel() {
        this.relationshipLevel += rollInt(-25, 25);
   }

   public int getRelationshipLevel() {
        return this.relationshipLevel;
   }

}
