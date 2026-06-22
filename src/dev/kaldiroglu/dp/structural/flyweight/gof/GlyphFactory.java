package dev.kaldiroglu.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * FlyweightFactory (GoF, p. 203).
 *
 * <p>Guarantees that {@link Character} flyweights are shared: a request for a
 * letter already in the pool returns the existing instance instead of
 * allocating a new one. {@link Row}s and {@link Column}s are unshared, so the
 * factory simply creates a fresh one each time.</p>
 */
public class GlyphFactory {

    private final Map<java.lang.Character, Character> characters = new HashMap<>();

    /** Returns the shared flyweight for {@code c}, creating it on first use. */
    public Character createCharacter(char c) {
        return characters.computeIfAbsent(c, Character::new);
    }

    public Row createRow() {
        return new Row();
    }

    public Column createColumn() {
        return new Column();
    }

    /** Number of distinct {@code Character} flyweights actually allocated. */
    public int createdCharacterCount() {
        return characters.size();
    }
}
