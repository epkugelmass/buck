/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.distributed.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-08-18")
public class FileInfo implements org.apache.thrift.TBase<FileInfo, FileInfo._Fields>, java.io.Serializable, Cloneable, Comparable<FileInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("FileInfo");

  private static final org.apache.thrift.protocol.TField CONTENT_HASH_FIELD_DESC = new org.apache.thrift.protocol.TField("contentHash", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CONTENT_FIELD_DESC = new org.apache.thrift.protocol.TField("content", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new FileInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new FileInfoTupleSchemeFactory();

  public java.lang.String contentHash; // optional
  public java.nio.ByteBuffer content; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CONTENT_HASH((short)1, "contentHash"),
    CONTENT((short)2, "content");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // CONTENT_HASH
          return CONTENT_HASH;
        case 2: // CONTENT
          return CONTENT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.CONTENT_HASH,_Fields.CONTENT};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CONTENT_HASH, new org.apache.thrift.meta_data.FieldMetaData("contentHash", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CONTENT, new org.apache.thrift.meta_data.FieldMetaData("content", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(FileInfo.class, metaDataMap);
  }

  public FileInfo() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public FileInfo(FileInfo other) {
    if (other.isSetContentHash()) {
      this.contentHash = other.contentHash;
    }
    if (other.isSetContent()) {
      this.content = org.apache.thrift.TBaseHelper.copyBinary(other.content);
    }
  }

  public FileInfo deepCopy() {
    return new FileInfo(this);
  }

  @Override
  public void clear() {
    this.contentHash = null;
    this.content = null;
  }

  public java.lang.String getContentHash() {
    return this.contentHash;
  }

  public FileInfo setContentHash(java.lang.String contentHash) {
    this.contentHash = contentHash;
    return this;
  }

  public void unsetContentHash() {
    this.contentHash = null;
  }

  /** Returns true if field contentHash is set (has been assigned a value) and false otherwise */
  public boolean isSetContentHash() {
    return this.contentHash != null;
  }

  public void setContentHashIsSet(boolean value) {
    if (!value) {
      this.contentHash = null;
    }
  }

  public byte[] getContent() {
    setContent(org.apache.thrift.TBaseHelper.rightSize(content));
    return content == null ? null : content.array();
  }

  public java.nio.ByteBuffer bufferForContent() {
    return org.apache.thrift.TBaseHelper.copyBinary(content);
  }

  public FileInfo setContent(byte[] content) {
    this.content = content == null ? (java.nio.ByteBuffer)null : java.nio.ByteBuffer.wrap(content.clone());
    return this;
  }

  public FileInfo setContent(java.nio.ByteBuffer content) {
    this.content = org.apache.thrift.TBaseHelper.copyBinary(content);
    return this;
  }

  public void unsetContent() {
    this.content = null;
  }

  /** Returns true if field content is set (has been assigned a value) and false otherwise */
  public boolean isSetContent() {
    return this.content != null;
  }

  public void setContentIsSet(boolean value) {
    if (!value) {
      this.content = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case CONTENT_HASH:
      if (value == null) {
        unsetContentHash();
      } else {
        setContentHash((java.lang.String)value);
      }
      break;

    case CONTENT:
      if (value == null) {
        unsetContent();
      } else {
        if (value instanceof byte[]) {
          setContent((byte[])value);
        } else {
          setContent((java.nio.ByteBuffer)value);
        }
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CONTENT_HASH:
      return getContentHash();

    case CONTENT:
      return getContent();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CONTENT_HASH:
      return isSetContentHash();
    case CONTENT:
      return isSetContent();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof FileInfo)
      return this.equals((FileInfo)that);
    return false;
  }

  public boolean equals(FileInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_contentHash = true && this.isSetContentHash();
    boolean that_present_contentHash = true && that.isSetContentHash();
    if (this_present_contentHash || that_present_contentHash) {
      if (!(this_present_contentHash && that_present_contentHash))
        return false;
      if (!this.contentHash.equals(that.contentHash))
        return false;
    }

    boolean this_present_content = true && this.isSetContent();
    boolean that_present_content = true && that.isSetContent();
    if (this_present_content || that_present_content) {
      if (!(this_present_content && that_present_content))
        return false;
      if (!this.content.equals(that.content))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetContentHash()) ? 131071 : 524287);
    if (isSetContentHash())
      hashCode = hashCode * 8191 + contentHash.hashCode();

    hashCode = hashCode * 8191 + ((isSetContent()) ? 131071 : 524287);
    if (isSetContent())
      hashCode = hashCode * 8191 + content.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(FileInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetContentHash()).compareTo(other.isSetContentHash());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContentHash()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.contentHash, other.contentHash);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetContent()).compareTo(other.isSetContent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.content, other.content);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("FileInfo(");
    boolean first = true;

    if (isSetContentHash()) {
      sb.append("contentHash:");
      if (this.contentHash == null) {
        sb.append("null");
      } else {
        sb.append(this.contentHash);
      }
      first = false;
    }
    if (isSetContent()) {
      if (!first) sb.append(", ");
      sb.append("content:");
      if (this.content == null) {
        sb.append("null");
      } else {
        org.apache.thrift.TBaseHelper.toString(this.content, sb);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class FileInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public FileInfoStandardScheme getScheme() {
      return new FileInfoStandardScheme();
    }
  }

  private static class FileInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<FileInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, FileInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CONTENT_HASH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.contentHash = iprot.readString();
              struct.setContentHashIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CONTENT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.content = iprot.readBinary();
              struct.setContentIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, FileInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.contentHash != null) {
        if (struct.isSetContentHash()) {
          oprot.writeFieldBegin(CONTENT_HASH_FIELD_DESC);
          oprot.writeString(struct.contentHash);
          oprot.writeFieldEnd();
        }
      }
      if (struct.content != null) {
        if (struct.isSetContent()) {
          oprot.writeFieldBegin(CONTENT_FIELD_DESC);
          oprot.writeBinary(struct.content);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class FileInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public FileInfoTupleScheme getScheme() {
      return new FileInfoTupleScheme();
    }
  }

  private static class FileInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<FileInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, FileInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetContentHash()) {
        optionals.set(0);
      }
      if (struct.isSetContent()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetContentHash()) {
        oprot.writeString(struct.contentHash);
      }
      if (struct.isSetContent()) {
        oprot.writeBinary(struct.content);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, FileInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.contentHash = iprot.readString();
        struct.setContentHashIsSet(true);
      }
      if (incoming.get(1)) {
        struct.content = iprot.readBinary();
        struct.setContentIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

