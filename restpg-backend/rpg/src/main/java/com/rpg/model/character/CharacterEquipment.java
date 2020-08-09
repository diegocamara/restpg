package com.rpg.model.character;

import com.rpg.model.item.Accessory;
import com.rpg.model.item.Body;
import com.rpg.model.item.Head;
import com.rpg.model.item.Weapon;

public class CharacterEquipment {

  private Weapon weapon;
  private Head head;
  private Body body;
  private Accessory accessory;

  public Weapon weapon() {
    return weapon;
  }

  public void weapon(Weapon weapon) {
    this.weapon = weapon;
  }

  public Head head() {
    return head;
  }

  public void head(Head head) {
    this.head = head;
  }

  public Body body() {
    return body;
  }

  public void body(Body body) {
    this.body = body;
  }

  public Accessory accessory() {
    return accessory;
  }

  public void accessory(Accessory accessory) {
    this.accessory = accessory;
  }
}
