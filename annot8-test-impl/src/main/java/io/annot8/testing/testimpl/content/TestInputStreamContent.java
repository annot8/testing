package io.annot8.testing.testimpl.content;

import io.annot8.common.data.content.InputStreamContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.common.implementations.stores.AnnotationStoreFactory;
import io.annot8.common.implementations.stores.SaveCallback;
import io.annot8.core.data.Content;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;
import io.annot8.testing.testimpl.AbstractTestContent;
import io.annot8.testing.testimpl.TestAnnotationStoreFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class TestInputStreamContent extends AbstractTestContent<InputStream> implements
    InputStreamContent {

  public static final byte[] DEFAULT_DATA = "Test Data".getBytes(StandardCharsets.UTF_8);

  public TestInputStreamContent() {
      super(InputStream.class);
      // THis is not really useful in general, but its something non-null
      setData(() -> new ByteArrayInputStream(DEFAULT_DATA));
    }

  public TestInputStreamContent(String id, String name, ImmutableProperties properties, Supplier<InputStream> data) {
      super(InputStream.class, id, name, properties, data);
    }

  public TestInputStreamContent(AnnotationStore annotations, String id, String name, ImmutableProperties properties, Supplier<InputStream> data) {
      super(InputStream.class, c -> annotations, id, name, properties, data);
    }

  public TestInputStreamContent(AnnotationStoreFactory annotationStore, String id, String name, ImmutableProperties properties, Supplier<InputStream> data) {
      super(InputStream.class, annotationStore, id, name, properties, data);
    }

    @Override
    public Class<? extends Content<InputStream>> getContentClass() {
      return InputStreamContent.class;
    }


    public static class Builder extends AbstractContentBuilder<InputStream, TestInputStreamContent> {

      private final AnnotationStoreFactory annotationStoreFactory;

      public Builder(
          AnnotationStoreFactory annotationStoreFactory,
          SaveCallback<TestInputStreamContent, TestInputStreamContent> saver) {
        super( saver);
        this.annotationStoreFactory = annotationStoreFactory;
      }

      @Override
      protected TestInputStreamContent create(String id, String name,
          ImmutableProperties properties, Supplier<InputStream> data) throws IncompleteException {
        return new TestInputStreamContent(annotationStoreFactory, id, name, properties, data);
      }

    }

    public static class BuilderFactory extends
        AbstractContentBuilderFactory<InputStream, TestInputStreamContent> {

      private final AnnotationStoreFactory annotationStoreFactory;

      public BuilderFactory() {
        this(TestAnnotationStoreFactory.getInstance());
      }

      public BuilderFactory(AnnotationStoreFactory annotationStoreFactory) {
        super(InputStream.class, TestInputStreamContent.class);
        this.annotationStoreFactory = annotationStoreFactory;
      }

      @Override
      public TestInputStreamContent.Builder create(Item item,
          SaveCallback<TestInputStreamContent, TestInputStreamContent> saver) {
        return new TestInputStreamContent.Builder(annotationStoreFactory, saver);
      }
    }

}
