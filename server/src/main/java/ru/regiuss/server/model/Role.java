package ru.regiuss.server.model;

public enum Role {
    STAFF("Сотрудник"),
    ADMIN("Администратор");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role get(String name){
        if(name == null)return null;
        try {
            return valueOf(name);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
