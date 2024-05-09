/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package spyra.lukasz.usernewsapi.dto.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class AvroNewsModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5317886217531371815L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AvroNewsModel\",\"namespace\":\"spyra.lukasz.usernewsapi.dto.avro\",\"fields\":[{\"name\":\"content\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<AvroNewsModel> ENCODER =
      new BinaryMessageEncoder<AvroNewsModel>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<AvroNewsModel> DECODER =
      new BinaryMessageDecoder<AvroNewsModel>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<AvroNewsModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<AvroNewsModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<AvroNewsModel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<AvroNewsModel>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this AvroNewsModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a AvroNewsModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a AvroNewsModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static AvroNewsModel fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.CharSequence content;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public AvroNewsModel() {}

  /**
   * All-args constructor.
   * @param content The new value for content
   */
  public AvroNewsModel(java.lang.CharSequence content) {
    this.content = content;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return content;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: content = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'content' field.
   * @return The value of the 'content' field.
   */
  public java.lang.CharSequence getContent() {
    return content;
  }


  /**
   * Sets the value of the 'content' field.
   * @param value the value to set.
   */
  public void setContent(java.lang.CharSequence value) {
    this.content = value;
  }

  /**
   * Creates a new AvroNewsModel RecordBuilder.
   * @return A new AvroNewsModel RecordBuilder
   */
  public static spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder newBuilder() {
    return new spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder();
  }

  /**
   * Creates a new AvroNewsModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new AvroNewsModel RecordBuilder
   */
  public static spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder newBuilder(spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder other) {
    if (other == null) {
      return new spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder();
    } else {
      return new spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder(other);
    }
  }

  /**
   * Creates a new AvroNewsModel RecordBuilder by copying an existing AvroNewsModel instance.
   * @param other The existing instance to copy.
   * @return A new AvroNewsModel RecordBuilder
   */
  public static spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder newBuilder(spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel other) {
    if (other == null) {
      return new spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder();
    } else {
      return new spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder(other);
    }
  }

  /**
   * RecordBuilder for AvroNewsModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AvroNewsModel>
    implements org.apache.avro.data.RecordBuilder<AvroNewsModel> {

    private java.lang.CharSequence content;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.content)) {
        this.content = data().deepCopy(fields()[0].schema(), other.content);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
    }

    /**
     * Creates a Builder by copying an existing AvroNewsModel instance
     * @param other The existing instance to copy.
     */
    private Builder(spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.content)) {
        this.content = data().deepCopy(fields()[0].schema(), other.content);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'content' field.
      * @return The value.
      */
    public java.lang.CharSequence getContent() {
      return content;
    }


    /**
      * Sets the value of the 'content' field.
      * @param value The value of 'content'.
      * @return This builder.
      */
    public spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder setContent(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.content = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'content' field has been set.
      * @return True if the 'content' field has been set, false otherwise.
      */
    public boolean hasContent() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'content' field.
      * @return This builder.
      */
    public spyra.lukasz.usernewsapi.dto.avro.AvroNewsModel.Builder clearContent() {
      content = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AvroNewsModel build() {
      try {
        AvroNewsModel record = new AvroNewsModel();
        record.content = fieldSetFlags()[0] ? this.content : (java.lang.CharSequence) defaultValue(fields()[0]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<AvroNewsModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<AvroNewsModel>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<AvroNewsModel>
    READER$ = (org.apache.avro.io.DatumReader<AvroNewsModel>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.content);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.content = in.readString(this.content instanceof Utf8 ? (Utf8)this.content : null);

    } else {
      for (int i = 0; i < 1; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.content = in.readString(this.content instanceof Utf8 ? (Utf8)this.content : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










