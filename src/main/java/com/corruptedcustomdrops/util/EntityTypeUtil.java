package com.corruptedcustomdrops.util;

import org.bukkit.entity.EntityType;

public class EntityTypeUtil {
    public static EntityType fromString(String name) {
        try {
            return EntityType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static boolean isValidEntity(String name) {
        return fromString(name) != null;
    }
}
