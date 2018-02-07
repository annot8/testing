package io.annot8.test;

import io.annot8.core.annotations.Group;
import io.annot8.core.references.GroupReference;
import java.util.Objects;
import java.util.Optional;

public class TestGroupReference implements GroupReference {

  private Group group;

  public TestGroupReference(Group group) {
    assert group != null;
    this.group = group;
  }

  @Override
  public String getGroupId() {
    return group.getId();
  }

  @Override
  public Optional<Group> toGroup() {
    return Optional.of(group);
  }

  public void setGroup(Group group) {
    assert group != null;
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestGroupReference that = (TestGroupReference) o;

    return Objects.equals(group.getId(), that.group.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(group.getId());
  }
}
