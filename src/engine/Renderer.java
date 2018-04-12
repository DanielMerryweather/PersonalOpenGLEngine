package engine;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;

public class Renderer {

	int vaoID;
	int vboID;
	
	public Renderer() {
		this.init();
	}
	
	public void init() {
		float[] vertices = new float[]{
			     0.0f,  0.5f, 0.0f,
			    -0.5f, -0.5f, 0.0f,
			     0.5f, -0.5f, 0.0f
			};
		
		FloatBuffer verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
		verticesBuffer.put(vertices).flip();
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
		MemoryUtil.memFree(verticesBuffer);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
	}
	
	public void cleanUp() {
		glDisableVertexAttribArray(0);

	    // Delete the VBO
	    glBindBuffer(GL_ARRAY_BUFFER, 0);
	    glDeleteBuffers(vboID);

	    // Delete the VAO
	    glBindVertexArray(0);
	    glDeleteVertexArrays(vaoID);
	}
	
}
