package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    public void checkAdd() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("1", "Owner");
        roleStore.add(role);
        assertThat(roleStore.findById("1")).isEqualTo(role);
    }

    @Test
    public void checkNoAdd() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("1", "Owner");
        Role role1 = new Role("1", "Developer");
        roleStore.add(role);
        roleStore.add(role1);
        assertThat(roleStore.findById("1")).isEqualTo(role);
    }

    @Test
    public void checkReplace() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("1", "Owner");
        Role role1 = new Role("2", "Developer");
        roleStore.add(role);
        assertThat(roleStore.replace("1", role1)).isTrue();
    }

    @Test
    public void checkNoReplace() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("3", "Owner");
        Role role1 = new Role("2", "Developer");
        roleStore.add(role);
        assertThat(roleStore.replace("1", role1)).isFalse();
    }

    @Test
    public void checkDelete() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("1", "Owner");
        roleStore.add(role);
        roleStore.delete("1");
        assertThat(roleStore.findById("1")).isNull();
    }

    @Test
    public void checkNotDelete() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("1", "Owner");
        roleStore.add(role);
        roleStore.delete("2");
        assertThat(roleStore.findById("1")).isNotNull();
    }

    @Test
    public void checkFindById() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("1", "Owner");
        roleStore.add(role);
        assertThat(roleStore.findById("1")).isEqualTo(role);
    }

    @Test
    public void checkNoFindById() {
        RoleStore roleStore = new RoleStore();
        Role role = new Role("1", "Owner");
        roleStore.add(role);
        assertThat(roleStore.findById("2")).isNull();
    }
}