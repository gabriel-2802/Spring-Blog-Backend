package com.app.demo.entities;

import java.util.List;

public enum Category {
    LOVE_POEMS, STATEMENTS, QUOTES, POEMS;

    public static List<String> getCategories() {
        return List.of(Category.POEMS.name(), Category.STATEMENTS.name(), Category.QUOTES.name(), Category.LOVE_POEMS.name());
    }
}
