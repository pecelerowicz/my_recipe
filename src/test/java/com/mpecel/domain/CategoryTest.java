package com.mpecel.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() { // runs before each test methods is executed.
        category = new Category();
    }

    @Test
    public void getId() {

        Long idValue = 4l;

        category.setId(idValue);

        assertEquals(idValue, category.getId());

    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}