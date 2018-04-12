package engine;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL11.GL_FLOAT;

import java.nio.FloatBuffer;
import org.lwjgl.Version;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import shaders.BasicShaderProgram;

public class Main {
	
	private Window window;

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();

		Window.cleanUp();
	}

	private void init() {
		window = new Window(1280, 720, "Hello World!");
	}

	private void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		// Set the clear color
		glClearColor(0.6f, 0.8f, 1.0f, 0.0f);

		// Gets the current time
		double ct = glfwGetTime();
		// Seconds per update
		double dt = 1d/60d;
		
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		
		Renderer rd = new Renderer();
		
		while ( !glfwWindowShouldClose(Window.window) ) {
			if(glfwGetTime() > ct){
				glfwSetWindowTitle(Window.window,"Testing 3D Engine - FPS: " + (""+1/((glfwGetTime()+dt)-ct)).substring(0,5));
				ct = glfwGetTime()+dt;
				
				glClearColor(0.0f, 0.5f, 1.0f, 0.0f);
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
				glfwSwapBuffers(Window.window); // swap the color buffers
				
				
				
				// Poll for window events. The key callback above will only be
				// invoked during this call.
				glfwPollEvents();
				
			}else{
				try{
					Thread.sleep(1);
				}catch(InterruptedException ei){}
			}
		}
		
		rd.cleanUp();
		
	}

	public static void main(String[] args) {
		new Main().run();
	}

}