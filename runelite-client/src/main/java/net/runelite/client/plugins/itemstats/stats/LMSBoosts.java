package net.runelite.client.plugins.itemstats.stats;

import net.runelite.api.Client;

public class LMSBoosts {

    static final int ATTACK = 99;
    static final int STRENGTH = 99;
    static final int DEFENSE = 75;
    static final int HITPOINTS = 99;
    static final int RANGED = 99;
    static final int MAGIC = 99;
    static final int PRAYER = 99;
    static final int AGILITY = 99;

    public static int skillMaximum(Stat stat, Client client) {

        switch(stat.getName()) {
            case "Attack":
                return ATTACK;
            case "Strength":
                return STRENGTH;
            case "Defence":
                return DEFENSE;
            case "Hitpoints":
                return HITPOINTS;
            case "Ranged":
                return RANGED;
            case "Magic":
                return MAGIC;
            case "Prayer":
                return PRAYER;
            case "Agility":
                return AGILITY;
            default:
                return stat.getMaximum(client);
        }
    }
}
