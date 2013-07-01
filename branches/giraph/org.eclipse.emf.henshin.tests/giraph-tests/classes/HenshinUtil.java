/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.giraph.examples;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.giraph.edge.Edge;
import org.apache.giraph.edge.EdgeFactory;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.formats.TextVertexInputFormat;
import org.apache.giraph.io.formats.TextVertexOutputFormat;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.json.JSONArray;
import org.json.JSONException;

import com.google.common.collect.Lists;

/**
 * Henshin utility classes and methods.
 */
public class HenshinUtil {

  /**
   * Private constructor.
   */
  private HenshinUtil() {
    // Prevent instantiation
  }

  /**
   * Henshin data.
   */
  public abstract static class Bytes extends BytesWritable {

    /**
     * Default constructor.
     */
    public Bytes() {
      super();
    }

    /**
     * Extra constructor.
     * @param data The data.
     */
    public Bytes(byte[] data) {
      super(data);
    }

    /**
     * Set the size.
     * @param size The new size.
     */
    @Override
    public void setSize(int size) {
      if (size != getCapacity()) {
        setCapacity(size);
      }
      super.setSize(size);
    }

    /**
     * Pretty-print this bytes object.
     * @return The printed string.
     */
    @Override
    public String toString() {
      byte[] bytes = getBytes();
      StringBuffer result = new StringBuffer();
      for (int i = 0; i < bytes.length; i++) {
        result.append(bytes[i]);
        if (i < bytes.length - 1) {
          result.append(",");
        }
      }
      return "[" + result + "]";
    }

  }

  /**
   * Henshin match object.
   */
  public static class Match extends Bytes {

    /**
     * Empty match.
     */
    public static final Match EMPTY = new Match();

    /**
     * Default constructor.
     */
    public Match() {
      super();
    }

    /**
     * Extra constructor.
     * @param data The data.
     */
    public Match(byte[] data) {
      super(data);
    }

    /**
     * Get the vertex ID of a matched node.
     * @param vertexIndex Index of the next vertex.
     * @return The vertex ID.
     */
    public VertexId getVertexId(int vertexIndex) {
      byte[] bytes = getBytes();
      int d = 0;
      for (int i = 0; i < vertexIndex; i++) {
        d += bytes[d] + 1;
      }
      return new VertexId(
        Arrays.copyOfRange(bytes, d + 1, d + 1 + bytes[d]));
    }

    /**
     * Create an extended version of this (partial) match.
     * @param vertexId The ID of the next matched vertex.
     * @return The extended match object.
     */
    public Match extend(VertexId vertexId) {
      byte[] bytes = getBytes();
      byte[] id = vertexId.getBytes();
      byte[] result = Arrays.copyOf(bytes, bytes.length + 1 + id.length);
      result[bytes.length] = (byte) id.length;
      System.arraycopy(id, 0, result, bytes.length + 1, id.length);
      return new Match(result);
    }

    /**
     * Pretty-print this match.
     * @return The printed string.
     */
    @Override
    public String toString() {
      byte[] bytes = getBytes();
      StringBuffer result = new StringBuffer();
      int i = 0;
      while (i < bytes.length) {
        int len = bytes[i++];
        result.append("[");
        for (int j = 0; j < len; j++) {
          result.append(bytes[i + j]);
          if (j < len - 1) {
            result.append(",");
          }
        }
        result.append("]");
        i += len;
        if (i < bytes.length - 1) {
          result.append(",");
        }
      }
      return "[" + result + "]";
    }

  }

  /**
   * Henshin vertex ID.
   */
  public static class VertexId extends Bytes {

    /**
     * Default constructor.
     */
    public VertexId() {
      super();
    }

    /**
     * Extra constructor.
     * @param data The data.
     */
    public VertexId(byte[] data) {
      super(data);
    }

    /**
     * Create an extended version of this vertex ID.
     * @param value The value to be appended to this vertex ID.
     * @return The extended version of this vertex ID.
     */
    public VertexId extend(byte value) {
      byte[] bytes = getBytes();
      bytes = Arrays.copyOf(bytes, bytes.length + 1);
      bytes[bytes.length - 1] = value;
      return new VertexId(bytes);
    }

  }

  /**
   * Henshin input format.
   */
  public static class InputFormat extends
    TextVertexInputFormat<VertexId, ByteWritable, ByteWritable> {

    @Override
    public TextVertexReader createVertexReader(InputSplit split,
      TaskAttemptContext context) {
      return new InputReader();
    }

    /**
     * Henshin input reader.
     */
    class InputReader extends
      TextVertexReaderFromEachLineProcessedHandlingExceptions<JSONArray,
        JSONException> {

      @Override
      protected JSONArray preprocessLine(Text line) throws JSONException {
        return new JSONArray(line.toString());
      }

      @Override
      protected VertexId getId(JSONArray jsonVertex)
        throws JSONException, IOException {
        return jsonArrayToVertexId(jsonVertex.getJSONArray(0));
      }

      /**
       * Convert a JSON array to a VertexId object.
       * @param jsonArray The JSON array to be converted.
       * @return The corresponding VertexId.
       */
      private VertexId jsonArrayToVertexId(JSONArray jsonArray)
        throws JSONException {
        byte[] bytes = new byte[jsonArray.length()];
        for (int i = 0; i < bytes.length; i++) {
          bytes[i] = (byte) jsonArray.getInt(i);
        }
        return new VertexId(bytes);
      }

      @Override
      protected ByteWritable getValue(JSONArray jsonVertex)
        throws JSONException, IOException {
        return new ByteWritable((byte) jsonVertex.getInt(1));
      }

      @Override
      protected Iterable<Edge<VertexId, ByteWritable>> getEdges(
        JSONArray jsonVertex) throws JSONException, IOException {
        JSONArray jsonEdgeArray = jsonVertex.getJSONArray(2);
        List<Edge<VertexId, ByteWritable>> edges =
          Lists.newArrayListWithCapacity(jsonEdgeArray.length());
        for (int i = 0; i < jsonEdgeArray.length(); ++i) {
          JSONArray jsonEdge = jsonEdgeArray.getJSONArray(i);
          edges.add(EdgeFactory.create(
            jsonArrayToVertexId(jsonEdge.getJSONArray(0)),
            new ByteWritable((byte) jsonEdge.getInt(1))));
        }
        return edges;
      }

      @Override
      protected Vertex<VertexId, ByteWritable, ByteWritable>
      handleException(Text line, JSONArray jsonVertex, JSONException e) {
        throw new IllegalArgumentException(
          "Couldn't get vertex from line " + line, e);
      }
    }
  }

  /**
   * Henshin output format.
   */
  public static class OutputFormat extends
    TextVertexOutputFormat<VertexId, ByteWritable, ByteWritable> {

    @Override
    public TextVertexWriter createVertexWriter(TaskAttemptContext context)
      throws IOException, InterruptedException {
      return new OutputWriter();
    }

    /**
     * Henshin output writer.
     */
    class OutputWriter extends TextVertexWriterToEachLine {

      @Override
      protected Text convertVertexToLine(
        Vertex<VertexId, ByteWritable, ByteWritable> vertex)
        throws IOException {

        JSONArray vertexArray = new JSONArray();
        JSONArray idArray = new JSONArray();
        byte[] id = vertex.getId().getBytes();
        for (int i = 0; i < id.length; i++) {
          idArray.put(id[i]);
        }
        vertexArray.put(idArray);
        vertexArray.put(vertex.getValue().get());
        JSONArray allEdgesArray = new JSONArray();
        for (Edge<VertexId, ByteWritable> edge : vertex.getEdges()) {
          JSONArray edgeArray = new JSONArray();
          JSONArray targetIdArray = new JSONArray();
          byte[] targetId = edge.getTargetVertexId().getBytes();
          for (int i = 0; i < targetId.length; i++) {
            targetIdArray.put(targetId[i]);
          }
          edgeArray.put(targetIdArray);
          edgeArray.put(edge.getValue().get());
          allEdgesArray.put(edgeArray);
        }
        vertexArray.put(allEdgesArray);
        return new Text(vertexArray.toString());
      }
    }
  }

}